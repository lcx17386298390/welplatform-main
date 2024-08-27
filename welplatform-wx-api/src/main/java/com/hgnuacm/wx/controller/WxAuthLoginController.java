package com.hgnuacm.wx.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.alibaba.fastjson2.JSONObject;
import com.hgnuacm.common.constant.CommConsts;
import com.hgnuacm.common.constant.Constants;
import com.hgnuacm.common.type.UserTypeEnum;
import com.hgnuacm.common.utils.*;
import com.hgnuacm.common.utils.uuid.IdUtils;
import com.hgnuacm.wx.annotation.LoginUser;
import com.hgnuacm.wx.domain.*;
import com.hgnuacm.wx.notify.NotifyService;
import com.hgnuacm.wx.service.EmailService;
import com.hgnuacm.wx.service.IPointService;
import com.hgnuacm.wx.service.StudentService;
import com.hgnuacm.wx.service.UserService;
import com.hgnuacm.wx.service.impl.AdmissionAuthManager;
import com.hgnuacm.wx.service.impl.FaceAuthManager;
import com.hgnuacm.wx.service.impl.IdCardAuthManager;
import com.hgnuacm.wx.service.impl.UserTokenManager;
import com.hgnuacm.common.constant.UserConstants;
import com.hgnuacm.common.core.domain.R;
import com.hgnuacm.common.utils.ip.IpUtils;
import com.hgnuacm.wx.util.WxResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.hgnuacm.wx.service.impl.AdmissionAuthManager.getBytesFromFile;
import static com.hgnuacm.wx.util.WxResponseCode.*;

@RestController
@RequestMapping("/wx/auth")
@Validated
public class WxAuthLoginController {
    private static final Logger logger = LoggerFactory.getLogger(WxAuthLoginController.class);

    @Autowired
    private WxMaService wxService;

    @Autowired
    private NotifyService notifyService;

    @Autowired
    private UserService userService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private IPointService pointService;

    @Resource
    private EmailService emailService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Value("${hgnuacm.idCard.appcode}")
    private String appcode;

    @Value("${hgnuacm.imageRg.appKey}")
    private String app_key;

    @Value("${hgnuacm.imageRg.appSecret}")
    private String app_secret;

    @Value("${hgnuacm.imageRg.url}")
    private String appUrl;

    @Value("${hgnuacm.faceRg.url}")
    private String faceUrl;

    @Value("${hgnuacm.faceRg.appcode}")
    private String faceappcode;

    /**
     * 微信登录
     * @param wxLoginInfo
     * @param request
     * @return
     */
    @PostMapping("/login_by_weixin")
    public R loginByWeixin(@RequestBody WxLoginInfo wxLoginInfo, HttpServletRequest request) {
        logger.info("【请求开始】微信登录,请求参数,wxLoginInfo:{}", JSONObject.toJSONString(wxLoginInfo));

        String code = wxLoginInfo.getCode();
        UserInfoVo userInfo = wxLoginInfo.getUserInfo();
        if (code == null || userInfo == null) {
            return R.fail();
        }

        Integer shareUserId = wxLoginInfo.getShareUserId();
        String sessionKey = null;
        String openId = null;

        try {
            WxMaJscode2SessionResult result = this.wxService.getUserService().getSessionInfo(code);
            sessionKey = result.getSessionKey();
            openId = result.getOpenid();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (sessionKey == null || openId == null) {
            logger.error("微信登录,调用官方接口失败：{}", code);
            return R.fail();
        }

        User user = userService.selectUserByOpenId(openId);

        if (user == null) {
            //TODO 积分表里面新增user
            user = new User();
            user.setUid(IdUtils.fastUUID());
            user.setUserName(userInfo.getNickName());
            user.setPassword(openId);
            user.setWeixinOpenid(openId);
            user.setAvatar(userInfo.getAvatarUrl());
            user.setName(userInfo.getName());
            user.setGender(userInfo.getGender());
            user.setStatus(Integer.valueOf(UserConstants.NORMAL));
            user.setCreateDate(LocalDateTime.now());
            user.setIpSource(IpUtils.getIpAddr(request));
            user.setShareUserId(shareUserId);
            user.setAdmissionStatus(Integer.valueOf(UserConstants.ADMISSION_UNPASS));
            userService.insertUser(user);
        } else {
            user.setIpSource(IpUtils.getIpAddr(request));
            if (userService.updateUser(user) == 0) {
                return R.fail();
            }
        }

        // token
        UserToken userToken = null;
        try {
            userToken = UserTokenManager.generateToken(user.getUid().length());
        } catch (Exception e) {
            logger.error("微信登录失败,生成token失败：{}", user.getUid());
            e.printStackTrace();
            return R.fail();
        }
        userToken.setSessionKey(sessionKey);

        Map<Object, Object> result = new HashMap<Object, Object>();
        result.put("token", userToken.getToken());
        result.put("tokenExpire", userToken.getExpireTime().toString());
        userInfo.setUserId(user.getUid());
        userInfo.setAvatarUrl(user.getAvatar());
        userInfo.setGender(user.getGender());
        userInfo.setName(user.getName());
        userInfo.setNickName(user.getUserName());
        userInfo.setAdmissionStatus(user.getAdmissionStatus());
        userInfo.setIdCard(user.getIdCard());
        userInfo.setStudentId(user.getStudentId());
        userInfo.setPhoto(user.getPhoto());
        userInfo.setMajor(user.getMajor());
        //TODO 获取用户积分
        Point giftUid = pointService.selectPointByUserId(user.getUid());
        if(StringUtils.isNotNull(giftUid)){
            userInfo.setPoints(pointService.selectPointByUserId(user.getUid()).getPoints());
        }


        if (!StringUtils.isEmpty(user.getMobile())) {// 手机号存在则设置
            userInfo.setPhone(user.getMobile());
        }
        try {
            String registerDate = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                    .format(user.getCreateDate() != null ? user.getCreateDate() : LocalDateTime.now());
            userInfo.setStatus(user.getStatus());
        } catch (Exception e) {
            logger.error("微信登录：设置用户指定信息出错："+e.getMessage());
            e.printStackTrace();
        }
        result.put("userInfo", userInfo);

        logger.info("【请求结束】微信登录,响应结果:{}", JSONObject.toJSONString(result));
        return R.ok(result);
    }

    /**
     * 注销登录
     *
     * @param userId
     * @return
     */
    @PostMapping("/logout")
    public Object logout(@LoginUser Integer userId) {
        logger.info("【请求开始】注销登录,请求参数，userId:{}", userId);
        if (userId == null) {
            return R.fail();
        }
        try {
            UserTokenManager.removeToken(userId);
        } catch (Exception e) {
            logger.error("注销登录出错：userId:{}", userId);
            e.printStackTrace();
            return R.fail();
        }

        logger.info("【请求结束】注销登录成功!");
        return R.ok();
    }

    /**
     * 请求验证码
     *
     * @param body
     *            手机号码{mobile}
     * @return
     */
    @PostMapping("/regCaptcha")
    public Object registerCaptcha(@RequestBody String body) {
        logger.info("【请求开始】请求验证码,请求参数，body:{}", body);

        String email = JacksonUtil.parseString(body, "email");
        //System.out.println(email);
        //邮箱为空
        if (StringUtils.isEmpty(email)) {
            return R.fail();
        }
        //邮箱格式错误
        if (!RegexUtil.isEmail(email)) {
            return R.fail();
        }
        //获取验证码
        String code = redisTemplate.opsForValue().get(email);
        if(!StringUtils.isEmpty(code)){
            return R.ok(code,"发送成功");
        }
        code = CharUtil.getRandomNum(6);
        //System.out.println(code);
        //boolean isSuccess = emailService.sendEmail(email,"验证码","欢迎注册,您的验证码为:" + code);
        Set<String> set = new HashSet();
        set.add(email);
        String content = "您好，欢迎来到大学生迎新平台，您的验证码为:" + code + ",有效时间为1分钟!";
        String title = "大学生迎新平台";
        boolean isSuccess = MailUtils.sendEmail(set,title,content);
        //邮箱验证
        if(isSuccess){
            logger.info("*****邮箱发送->请求验证码，短信发送结果：{}",code);
            redisTemplate.opsForValue().set(email,code,1, TimeUnit.MINUTES);
            return R.ok(code,"发送成功");
        }
        logger.error("请求验证码出错:{}",AUTH_CAPTCHA_UNSUPPORT.desc());
        return R.fail();



//电话号码验证
//        if (!notifyService.isSmsEnable()) {
//            logger.error("请求验证码出错:{}", AUTH_CAPTCHA_UNSUPPORT.desc());
//            return WxResponseUtil.fail(AUTH_CAPTCHA_UNSUPPORT);
//        }
//        String code = CharUtil.getRandomNum(6);
//        SmsResult smsResult = notifyService.notifySmsTemplate(phoneNumber, NotifyType.CAPTCHA, new String[] { code, "1" });
//        if (smsResult != null) {
//            logger.info("*****腾讯云短信发送->请求验证码，短信发送结果：{}",JSONObject.toJSONString(smsResult));
//        }
//
//        boolean successful = CaptchaCodeManager.addToCache(phoneNumber, code,1);
//        if (!successful) {
//            logger.error("请求验证码出错:{}", AUTH_CAPTCHA_FREQUENCY.desc());
//            return WxResponseUtil.fail(AUTH_CAPTCHA_FREQUENCY);
//        }
//
//        logger.info("【请求结束】请求验证码成功");
//        return R.ok();
    }

    /**
     * 账号注册
     *
     * @param body
     *            请求内容 { username: xxx, password: xxx, mobile: xxx code: xxx }
     *            其中code是手机验证码，目前还不支持手机短信验证码
     * @param request
     *            请求对象
     * @return 登录结果 成功则 { errno: 0, errmsg: '成功', data: { token: xxx, tokenExpire:
     *         xxx, userInfo: xxx } } 失败则 { errno: XXX, errmsg: XXX }
     */
    @PostMapping("/register")
    public Object register(@RequestBody String body, HttpServletRequest request) {
        logger.info("【请求开始】账号注册,请求参数，body:{}", body);

        String username = JacksonUtil.parseString(body, "username");
        String password = JacksonUtil.parseString(body, "password");
        String email = JacksonUtil.parseString(body, "email");
        String code = JacksonUtil.parseString(body, "code");
        String wxCode = JacksonUtil.parseString(body, "wxCode");

        //账号信息出错
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password) || StringUtils.isEmpty(email)
                || StringUtils.isEmpty(wxCode) || StringUtils.isEmpty(code)) {
            return R.fail();
        }

        List<User> userList = userService.queryByUsername(username);
        if (userList.size() > 0) {
            logger.error("请求账号注册出错:{}", AUTH_NAME_REGISTERED.desc());
            return WxResponseUtil.fail(AUTH_NAME_REGISTERED);
        }

        userList = userService.queryByEmail(email);
        if (userList.size() > 0) {
            logger.error("请求账号注册出错:{}", AUTH_EMAIL_REGISTERED.desc());
            return WxResponseUtil.fail(AUTH_EMAIL_REGISTERED);
        }

        if (!RegexUtil.isEmail(email)) {
            logger.error("请求账号注册出错:{}", AUTH_INVALID_EMAIL.desc());
            return WxResponseUtil.fail(AUTH_INVALID_EMAIL);
        }
        // 判断验证码是否正确
        //String cacheCode = CaptchaCodeManager.getCachedCaptcha(mobile);
        String cacheCode = redisTemplate.opsForValue().get(email);
        if (cacheCode == null || cacheCode.isEmpty() || !cacheCode.equals(code)) {
            logger.error("请求账号注册出错:{}", AUTH_CAPTCHA_UNMATCH.desc());
            return WxResponseUtil.fail(AUTH_CAPTCHA_UNMATCH);
        }

        String openId = null;
        try {
            WxMaJscode2SessionResult result = this.wxService.getUserService().getSessionInfo(wxCode);
            openId = result.getOpenid();
        } catch (Exception e) {
            logger.error("请求账号注册出错:{}", AUTH_OPENID_UNACCESS.desc());
            e.printStackTrace();
            return WxResponseUtil.fail(AUTH_OPENID_UNACCESS);
        }
        //System.out.println(openId);
        userList = userService.queryByOpenid(openId);
        if (userList.size() > 1) {
            //目标账号数量大于1
            return R.fail();
        }
        if (userList.size() == 1) {
            User checkUser = userList.get(0);
            String checkUsername = checkUser.getUserName();
            String checkPassword = checkUser.getPassword();
            if (!checkUsername.equals(openId) || !checkPassword.equals(openId)) {
                logger.error("请求账号注册出错:{}", AUTH_OPENID_BINDED.desc());
                return WxResponseUtil.fail(AUTH_OPENID_BINDED);
            }
        }

        User user = null;
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(password);
        user = new User();
        user.setUid(IdUtils.fastUUID());
        user.setUserName(username);
        user.setPassword(encodedPassword);
        user.setEmail(email);
        user.setWeixinOpenid(openId);
        user.setCreateDate(LocalDateTime.now());
        user.setAdmissionStatus(Integer.valueOf(UserConstants.ADMISSION_UNPASS));
        user.setAvatar(CommConsts.DEFAULT_AVATAR);
        user.setStatus(Integer.valueOf(UserConstants.NORMAL));
        user.setIpSource(IpUtils.getIpAddr(request));
        userService.insertUser(user);


        // userInfo
        UserInfoVo userInfo = new UserInfoVo();
        userInfo.setNickName(username);
        userInfo.setAvatarUrl(user.getAvatar());

        // token
        UserToken userToken = null;
        try {
            userToken = UserTokenManager.generateToken(user.getUid().length());
        } catch (Exception e) {
            logger.error("账号注册失败,生成token失败：{}", user.getUid());
            e.printStackTrace();
            return R.fail();
        }

        Map<Object, Object> result = new HashMap<Object, Object>();
        result.put("token", userToken.getToken());
        result.put("tokenExpire", userToken.getExpireTime().toString());
        result.put("userInfo", userInfo);

        logger.info("【请求结束】账号注册,响应结果:{}", JSONObject.toJSONString(result));
        return R.ok(result);
    }

    /**
     * 账号登录
     *
     * @param body
     *            请求内容，{ username: xxx, password: xxx }
     * @param request
     *            请求对象
     * @return 登录结果
     */
    @PostMapping("/login")
    public Object login(@RequestBody String body, HttpServletRequest request) {
        logger.info("【请求开始】账户登录,请求参数,body:{}", body);

        String username = JacksonUtil.parseString(body, "username");
        String password = JacksonUtil.parseString(body, "password");
        if (username == null || password == null) {
            return R.fail();
        }

        List<User> userList = userService.queryByUsername(username);
        User user = null;
        if (userList.size() > 1) {
            logger.error("账户登录 出现多个同名用户错误,用户名:{},用户数量:{}", username, userList.size());
            return R.fail();
        } else if (userList.size() == 0) {
            logger.error("账户登录 用户尚未存在,用户名:{}", username);
            return R.fail();
        } else {
            user = userList.get(0);
            //System.out.println(user);
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(password, user.getPassword())) {
            logger.error("账户登录 ,错误密码：{},{}", password, AUTH_INVALID_ACCOUNT.desc());// 错误的密码打印到日志中作为提示也无妨
            return WxResponseUtil.fail(AUTH_INVALID_ACCOUNT);
        }

        // userInfo
        UserInfoVo userInfo = new UserInfoVo();
        userInfo.setNickName(username);
        userInfo.setAvatarUrl(user.getAvatar());

        try {
            //System.out.println(user.getCreateDate());
            //System.out.println(user.getCreateDate() == null ? user.getCreateDate() : LocalDateTime.now());
            LocalDateTime time = user.getCreateDate() == null ? user.getCreateDate() : LocalDateTime.now();
            //System.out.println(time.getClass());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            ZoneId zoneId = ZoneId.systemDefault();
            ZonedDateTime zdt = time.atZone(zoneId);
            String registerDate = sdf.format(Date.from(zdt.toInstant()));
            userInfo.setRegisterDate(registerDate);
            userInfo.setStatus(user.getStatus());
            userInfo.setUserLevel((byte) user.getStatus().intValue());// 用户层级
            userInfo.setUserLevelDesc
            (UserTypeEnum.getInstance((byte) user.getStatus().intValue()).getDesc());// 用户层级描述
            userInfo.setUserId(user.getUid());
            userInfo.setAdmissionStatus(user.getAdmissionStatus());
        } catch (Exception e) {
            logger.error("账户登录：设置用户指定信息出错："+e.getMessage());
            e.printStackTrace();
        }

        // token
        UserToken userToken = null;
        try {
            userToken = UserTokenManager.generateToken(user.getUid().length());
        } catch (Exception e) {
            logger.error("账户登录失败,生成token失败：{}", user.getUid());
            e.printStackTrace();
            return R.fail();
        }

        Map<Object, Object> result = new HashMap<Object, Object>();
        result.put("token", userToken.getToken());
        result.put("tokenExpire", userToken.getExpireTime().toString());
        result.put("userInfo", userInfo);

        logger.info("【请求结束】账户登录,响应结果:{}", JSONObject.toJSONString(result));
        return R.ok(result);
    }

    /**
     * 检测用户认证
     * @param body
     * @param request
     * @return
     */
    @PostMapping("/idCard")
    public Object idCard(@RequestBody String body, HttpServletRequest request){
        logger.info("【请求开始】账户身份证认证,请求参数,body:{}", body);
        //System.out.println(appcode);
        String name = JacksonUtil.parseString(body, "name");
        String idCard = JacksonUtil.parseString(body, "idCard");
        String uid = JacksonUtil.parseString(body, "userId");
        String imgUrl = JacksonUtil.parseString(body, "imgUrl");
        String avatarUrl = JacksonUtil.parseString(body, "avatarUrl");

        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(idCard) ||
            StringUtils.isEmpty(uid) || StringUtils.isEmpty(imgUrl) ||
            StringUtils.isEmpty(avatarUrl)) {
            logger.info("【请求失败】姓名或身份证或录取通知书或个人头像为空");
            return R.fail("姓名或身份证或录取通知书或个人头像为空!");
        }
        //**************************************************
        //验证身份证是否匹配
        User user = userService.selectUserByUid(uid);
        if(StringUtils.isNotEmpty(user.getIdCard())
        && StringUtils.isNotNull(user.getIdCard())){
            //用户已认证
            logger.info("【请求失败】用户已认证(1");
            return R.fail("用户已认证!");
        }
        //System.out.println(IdCardAuthManager.IdCardAuth(appcode, idCard, name).getData());
        R res = IdCardAuthManager.IdCardAuth(appcode, idCard, name);
        boolean isSuccess = res.getCode() == 200;
        if(!isSuccess){
            //认证失败,参数错误，调用api错误
            logger.info("【请求失败】系统内部错误");
            return R.fail("系统内部错误!");
        }
        IdCardAuthInfo info = (IdCardAuthInfo) res.getData();
        //姓名与身份证号不匹配
        if(!Constants.IDCARD_SUCCESS_STUTUS.equals(info.getStatus())){
            logger.info("【请求失败】姓名与身份证号不匹配");
            return R.fail(info,"姓名与身份证号不匹配");
        }
        //匹配
        String genderStr = info.getSex();
        Integer gender = "男".equals(genderStr) ? UserConstants.GENDER_MALE : UserConstants.GENDER_FEMALE;
        user.setName(name);
        user.setGender(gender);
        user.setIdCard(idCard);
        user.setPhoto(avatarUrl);
        logger.info("【请求结束】账户身份证认证完成");
        //**************************************************
        //验证录取通知书是否匹配
        AdmissionAuthInfo authInfo = null;
        try{
            //File file = new File(imgUrl);
            File file = AdmissionAuthManager.getFile(imgUrl);
            byte[] buff = getBytesFromFile(file);
            String url = appUrl;
            HashMap<String, String> map = new HashMap<>();
            HashMap<String, byte[]> byteMap = new HashMap<>();
            map.put("api_key", app_key);
            map.put("api_secret", app_secret);
            byteMap.put("image_file", buff);
            //System.out.println(imgUrl);
            //System.out.println(Arrays.toString(buff));
            //System.out.println(url);
            //System.out.println(app_key);
            //System.out.println(app_secret);
            byte[] result = AdmissionAuthManager.post(url, map, byteMap);
            String str = new String(result);
            //System.out.println(str);
            authInfo = JSONObject.parseObject(str, AdmissionAuthInfo.class);
            //System.out.println(authInfo);
            if(StringUtils.isNotEmpty(authInfo.getError_message())
                    || StringUtils.isNotNull(authInfo.getError_message())){
                logger.info("【请求失败】系统错误或图片格式有问题");
                return R.fail("系统错误或图片格式有问题");
            }
            List<String> textResList;
            textResList = StringUtils.extractLineContent(authInfo.getText_info());
            if(!textResList.contains(name)){
                //没有找到对应身份证上的姓名，认证失败
                logger.info("【认证错误】没有识别到录取通知书上对应学生的姓名!");
                return R.fail("认证错误!请重新上传");
            }
        }catch (Exception e) {
            //系统内部错误
            logger.info("【请求错误】系统内部错误");
            e.printStackTrace();
            return R.fail("系统内部错误");
        }
        logger.info("【请求结束】账户录取通知书认证完成");
        //**************************************************
        //验证个人认证头像是否匹配
        Map<String, String> params = new HashMap<>();
        params.put("idcard", idCard);
        params.put("name", name);
        params.put("url", avatarUrl);
        FaceAuthInfo faceAuthInfo;
        String result = null;
        try {
            result = FaceAuthManager.postForm(faceappcode, faceUrl, params);
            //System.out.println(result);
            faceAuthInfo = JSONObject.parseObject(result, FaceAuthInfo.class);
            System.out.println(faceAuthInfo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //人脸识别检测失败
        //图片传输出错
        if(Objects.equals(faceAuthInfo.getCode(), "400")){
            logger.info("【请求失败】用户人脸识别异常: {}",faceAuthInfo.getMsg());
            return R.fail(faceAuthInfo.getMsg());
        }

        //图片相似度出错
        if(StringUtils.isNotNull(faceAuthInfo.getData())){
            if(faceAuthInfo.getData().getIncorrect() != 100){
                logger.info("【请求失败】用户人脸识别异常: {}",faceAuthInfo.getData().getMsg());
                return R.fail(faceAuthInfo.getData().getMsg());
            }
        }

        //人脸识别检测成功
        //录取通知书检查成功
        user.setAdmissionStatus(Integer.valueOf(UserConstants.ADMISSION_PASS));

        //认证后根据t_student表中数据补全t_user表中的数据
        //根据idCard值去筛选
        List<Student> studentList = studentService.queryByIdCard(idCard);
        //studentList的size绝对为1
        Student student = studentList.get(0);
        user.setStudentId(student.getStudentId());
        user.setSchool(student.getSchool());
        user.setMajor(student.getMajor());

        if(userService.updateUser(user) == 0){
            //更新失败,已认证
            logger.info("【请求失败】用户已认证(2");
            return R.fail("用户已认证!");
        }

        logger.info("【请求结束】账户认证完成!");

        //返回user，更新小程序中的userInfo
        return R.ok(user);
    }

    /**
     * 检测用户是否已认证
     * @param body
     * @param request
     * @return
     */
    @PostMapping("/auth")
    public Object auth(@RequestBody String body, HttpServletRequest request){
        logger.info("【请求开始】检测账户认证状态,请求参数,body:{}", body);
        String uid = JacksonUtil.parseString(body, "userId");
        if(StringUtils.isNull(uid)){
            logger.info("【请求错误】没有获取到用户信息或未注册");
            return R.fail("没有获取到用户信息");
        }
        User user = userService.selectUserByUid(uid);
        if(user.getAdmissionStatus().toString().equals(UserConstants.ADMISSION_UNPASS)){
            logger.info("【未认证】用户还未认证");
            return R.fail("用户还未认证");
        }
        logger.info("【请求结束】用户已认证");
        return R.ok();
    }

    /**
     * 保存用户头像昵称信息
     * @param body
     * @return
     */
    @PostMapping("/saveData")
    public Object saveData(@RequestBody String body){
        logger.info("【请求开始】用户保存信息,请求参数,body:{}", body);

        String userId = JacksonUtil.parseString(body, "userId");
        String nickName = JacksonUtil.parseString(body, "nickName");
        String avatarUrl = JacksonUtil.parseString(body,"avatarUrl");

        User user = userService.selectUserByUid(userId);

        if(StringUtils.isNull(user)){
            logger.info("【请求结束】用户id错误，未找到该用户，请登录!");
            return R.fail("未找到该用户");
        }

        user.setUserName(nickName);
        user.setAvatar(avatarUrl);

        if(userService.updateUser(user) == 0){
            logger.info("【请求结束】用户保存信息");
            return R.ok("用户保存信息不变");
        }

        return R.ok("保存成功");
    }

    /**
     * 用户重置密码
     * @param body
     * @return
     */
    @PostMapping("/resetPwd")
    public Object resetPwd(@RequestBody String body){
        logger.info("【请求开始】用户重置密码,请求参数,body:{}", body);

        String email = JacksonUtil.parseString(body, "email");
        String code = JacksonUtil.parseString(body, "code");
        String password = JacksonUtil.parseString(body,"password");

        //非空判断
        if(StringUtils.isEmpty(email) || StringUtils.isEmpty(code) || StringUtils.isEmpty(password)){
            logger.info("【请求结束】邮箱或验证码或密码为空!");
            return R.fail("邮箱或验证码或密码为空!");
        }

        //邮箱是否有效
        if (!RegexUtil.isEmail(email)) {
            logger.error("【请求结束】邮箱格式错误!:{}", AUTH_INVALID_EMAIL.desc());
            return R.fail(AUTH_INVALID_EMAIL);
        }

        //用户判断
        List<User> userList;
        userList = userService.queryByEmail(email);
        if (userList.size() != 1) {
            logger.error("请求账号注册出错:{}", AUTH_EMAIL_REGISTERED.desc());
            return R.fail(AUTH_EMAIL_REGISTERED);
        }

        // 判断验证码是否正确
        String cacheCode = redisTemplate.opsForValue().get(email);
        if (cacheCode == null || cacheCode.isEmpty() || !cacheCode.equals(code)) {
            logger.error("请求账号注册出错:{}", AUTH_CAPTCHA_UNMATCH.desc());
            return R.fail(AUTH_CAPTCHA_UNMATCH);
        }

        User user = userList.get(0);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(password);

        user.setPassword(encodedPassword);
        int res = userService.updateUser(user);
        if(res < 0){
            logger.error("【请求结束】系统内部错误!");
            return R.fail("系统内部错误!");
        }
        logger.info("【请求结束】重置密码成功!");
        return R.ok("重置密码成功");
    }
}

