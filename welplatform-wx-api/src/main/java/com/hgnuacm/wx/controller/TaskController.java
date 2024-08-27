package com.hgnuacm.wx.controller;

import javax.servlet.http.HttpServletResponse;

import com.aliyun.facebody20191230.models.CompareFaceResponse;
import com.aliyun.facebody20191230.models.CompareFaceResponseBody;
import com.aliyun.imagerecog20190930.models.TaggingImageResponse;
import com.aliyun.imagerecog20190930.models.TaggingImageResponseBody;
import com.aliyun.tea.TeaException;
import com.aliyun.tea.TeaModel;
import com.hgnuacm.common.constant.Constants;
import com.hgnuacm.common.constant.UserConstants;
import com.hgnuacm.common.core.domain.R;
import com.hgnuacm.common.utils.JacksonUtil;
import com.hgnuacm.common.utils.PositionUtils;
import com.hgnuacm.common.utils.StringUtils;
import com.hgnuacm.common.utils.poi.ExcelUtil;
import com.hgnuacm.wx.domain.*;
import com.hgnuacm.wx.service.*;
import com.hgnuacm.wx.service.impl.CompareFaceManager;
import com.hgnuacm.wx.service.impl.ImageRecongizeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.hgnuacm.common.annotation.Log;
import com.hgnuacm.common.core.controller.BaseController;
import com.hgnuacm.common.core.domain.AjaxResult;
import com.hgnuacm.common.enums.BusinessType;
import com.hgnuacm.common.core.page.TableDataInfo;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 任务Controller
 * 
 * @author violet
 * @date 2024-01-20
 */
@RestController
@RequestMapping("/wx/taskinfo")
public class TaskController extends BaseController
{
    @Autowired
    private ITaskService taskService;

    @Autowired
    private ITUserTaskService userTaskService;

    @Autowired
    private ITTaskTypeService taskTypeService;

    @Autowired
    private UserService userService;

    @Autowired
    private IPointService pointService;

    @Value("${hgnuacm.imageDt.accessKeyId}")
    private String imageDtAccessKeyId;

    @Value("${hgnuacm.imageDt.accessSecret}")
    private String imageDtAccessKeySecret;

    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);
    /**
     * 查询任务列表
     */
    @PreAuthorize("@ss.hasPermi('welplatform-task:task:list')")
    @GetMapping("/list")
    public TableDataInfo list(Task task)
    {
        startPage();
        List<Task> list = taskService.selectTaskList(task);
        return getDataTable(list);
    }

    /**
     * 导出任务列表
     */
    @PreAuthorize("@ss.hasPermi('welplatform-task:task:export')")
    @Log(title = "任务", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Task task)
    {
        List<Task> list = taskService.selectTaskList(task);
        ExcelUtil<Task> util = new ExcelUtil<Task>(Task.class);
        util.exportExcel(response, list, "任务数据");
    }

    /**
     * 获取任务详细信息
     */
    @PreAuthorize("@ss.hasPermi('welplatform-task:task:query')")
    @GetMapping(value = "/{uid}")
    public AjaxResult getInfo(@PathVariable("uid") String uid)
    {
        return success(taskService.selectTaskByUid(uid));
    }

    /**
     * 新增任务
     */
    @PreAuthorize("@ss.hasPermi('welplatform-task:task:add')")
    @Log(title = "任务", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Task task)
    {
        return toAjax(taskService.insertTask(task));
    }

    /**
     * 修改任务
     */
    @PreAuthorize("@ss.hasPermi('welplatform-task:task:edit')")
    @Log(title = "任务", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Task task)
    {
        return toAjax(taskService.updateTask(task));
    }

    /**
     * 删除任务
     */
    @PreAuthorize("@ss.hasPermi('welplatform-task:task:remove')")
    @Log(title = "任务", businessType = BusinessType.DELETE)
	@DeleteMapping("/{uids}")
    public AjaxResult remove(@PathVariable String[] uids)
    {
        return toAjax(taskService.deleteTaskByUids(uids));
    }

    /**
     * 拍照物品图片检测
     * @param body
     * @return
     */
    @PostMapping("/finish")
    public R detectTask(@RequestBody String body) throws Exception {
        logger.info("【请求开始】用户任务拍照图片检测,请求参数,body:{}", body);

        String content = JacksonUtil.parseString(body,"content");
        String imageUrl = JacksonUtil.parseString(body,"imageUrl");
        String userId = JacksonUtil.parseString(body,"userId");
        String taskId = JacksonUtil.parseString(body,"taskId");

        //判断非空
        if(StringUtils.isEmpty(content) || StringUtils.isEmpty(imageUrl)
            || StringUtils.isEmpty(userId) || StringUtils.isEmpty(taskId)){
            logger.info("请填写打卡信息!");
            return R.fail("请填写打卡信息!");
        }

        //判断任务类型
        Task task = taskService.selectTaskByUid(taskId);
        Integer type = task.getType();

        //如果是拍照打卡任务
        if(type.equals(Constants.TASK_TYPE_PHOTO)){
            String accessKeyId = System.getenv("ALIBABA_CLOUD_ACCESS_KEY_ID");
            String accessKeySecret = System.getenv("ALIBABA_CLOUD_ACCESS_KEY_SECRET");
            // 请确保代码运行环境设置了环境变量 ALIBABA_CLOUD_ACCESS_KEY_ID 和 ALIBABA_CLOUD_ACCESS_KEY_SECRET。
            // 工程代码泄露可能会导致 AccessKey 泄露，并威胁账号下所有资源的安全性。以下代码示例使用环境变量获取 AccessKey 的方式进行调用，仅供参考，建议使用更安全的 STS 方式，更多鉴权访问方式请参见：https://help.aliyun.com/document_detail/378657.html
            com.aliyun.imagerecog20190930.Client client = ImageRecongizeService.createClient(accessKeyId, accessKeySecret);
            // 场景一，使用本地文件
            // InputStream inputStream = new FileInputStream(new File("/tmp/TaggingImage.jpg"));
            // 场景二，使用任意可访问的url
            assert imageUrl != null;
            URL url = new URL(imageUrl);
            InputStream inputStream = url.openConnection().getInputStream();
            com.aliyun.imagerecog20190930.models.TaggingImageAdvanceRequest taggingImageAdvanceRequest = new com.aliyun.imagerecog20190930.models.TaggingImageAdvanceRequest()
                    .setImageURLObject(inputStream);
            com.aliyun.teautil.models.RuntimeOptions runtime = new com.aliyun.teautil.models.RuntimeOptions();
            try {
                TaggingImageResponse taggingImageAdvanceResponse = client.taggingImageAdvance(taggingImageAdvanceRequest, runtime);
                // 获取整体结果。
                System.out.println(com.aliyun.teautil.Common.toJSONString(TeaModel.buildMap(taggingImageAdvanceResponse)));
                // 获取单个字段。
                //System.out.println(taggingImageAdvanceResponse.getBody());
                //TaggingImageBodyInfo res = JSONObject.parseObject(taggingImageAdvanceResponse.getBody().toString(), TaggingImageBodyInfo.class);
                //System.out.println(res);
                TaggingImageResponseBody res = taggingImageAdvanceResponse.getBody();
                List<String> values = new ArrayList<String>();
                List<TaggingImageResponseBody.TaggingImageResponseBodyDataTags> tags = res.getData().getTags();
                for (TaggingImageResponseBody.TaggingImageResponseBodyDataTags tag : tags) {
                    values.add(tag.getValue());
                }

                TTaskType taskType = taskTypeService.selectTTaskTypeByTaskId(taskId);

                //根据任务描述查询
                String typeDesc = taskType.getTypeDesc();
                if(!values.contains(typeDesc)){
                    //认证失败
                    logger.info("未检测到相关物品!");
                    return R.fail();
                }

            } catch (TeaException teaException) {
                // 获取整体报错信息。
                System.out.println(com.aliyun.teautil.Common.toJSONString(teaException));
                // 获取单个字段。
                System.out.println(teaException.getCode());
            }
        }


        //改变任务状态
        TUserTask userTask = userTaskService.getTUserTaskByTaskIdUserId(taskId, userId);
        userTask.setStatus(Constants.TASK_FINISHED);
        userTask.setCompleteTime(LocalDateTime.now());
        int i = userTaskService.updateTUserTask(userTask);


        //用户增加积分
        Point userPoints = pointService.selectPointByUserId(userId);
        Long taskPoints = task.getPoints();
        userPoints.setPoints(userPoints.getPoints() + taskPoints);
        int j = pointService.updatePoint(userPoints);

        //判断更新
        if(i == 0 || j == 0) {
            logger.info("系统错误，请稍后再试!");
            return R.fail("系统错误，请稍后再试!");
        }

        //打卡成功
        logger.info("打卡成功!");
        return R.ok();
    }

    /**
     * 获取详细任务信息
     * @param taskId
     * @return
     */
    @GetMapping("/detail")
    public R selectTask(@RequestParam String taskId, @RequestParam String userId) {
        logger.info("【请求开始】获取详细任务信息,请求参数,taskId:{},userId:{}", taskId,userId);
        if(StringUtils.isEmpty(taskId) || StringUtils.isEmpty(userId)){
            logger.info("【请求失败】未获取到任务信息!");
            return R.fail("未获取到任务信息,请重试");
        }
        DtsTask dtsTask = new DtsTask();
        Task task = taskService.selectTaskByUid(taskId);
        TUserTask userTask = userTaskService.getTUserTaskByTaskIdUserId(taskId,userId);
        if(task == null || userTask == null) {
            logger.info("【请求失败】未获取到任务信息!");
            return R.fail("未获取到任务信息,请重试!");
        }

        //查询任务认证状态
        dtsTask.setTask(task);
        Integer status = userTaskService.getTUserTaskByTaskIdUserId(taskId, userId).getStatus();
        dtsTask.setStatus(status.equals(Constants.TASK_FINISHED));

        return R.ok(dtsTask);
    }

    /**
     * 获取任务类型
     * @param taskId
     * @return
     */
    @GetMapping("/type")
    public R getTaskType(@RequestParam String taskId){
        logger.info("【请求开始】获取任务类型,请求参数,taskId:{}", taskId);
        if(StringUtils.isEmpty(taskId)){
            logger.info("【请求失败】未获取到任务信息!");
            return R.fail("未获取到任务信息,请重试");
        }
        TTaskType type = taskTypeService.selectTTaskTypeByTaskId(taskId);
        if(StringUtils.isNull(type)){
            logger.info("【请求失败】未获取到任务类型信息!");
            return R.fail("未获取到任务信息,请重试");
        }

        logger.info("【请求成功】任务类型,type:{}",type);
        return R.ok(type);
    }

    /**
     * 任务位置打卡
     * @param body
     * @return
     */
    @PostMapping("/position")
    public R positionTask(@RequestBody String body){
        logger.info("【请求开始】用户位置打卡检测,请求参数,body:{}", body);

        double latitude = Double.parseDouble(Objects.requireNonNull(JacksonUtil.parseString(body, "latitude")));
        double longitude = Double.parseDouble(Objects.requireNonNull(JacksonUtil.parseString(body, "longitude")));
        String taskId = JacksonUtil.parseString(body,"taskId");
        String userId = JacksonUtil.parseString(body,"userId");

        //判断非空
        if(StringUtils.isEmpty(taskId) || StringUtils.isEmpty(userId)){
            logger.info("未获取到任务信息，请重试");
            return R.fail("未获取到任务信息，请重试");
        }

        //查询
        TTaskType taskType = taskTypeService.selectTTaskTypeByTaskId(taskId);
        Task task = taskService.selectTaskByUid(taskId);
        double tarLatitude = taskType.getLatitude();
        double tarLongitude = taskType.getLongitude();

        //确定范围,单位为km
        double distance = PositionUtils.calculateDistance(latitude, longitude, tarLatitude, tarLongitude);
        if(distance > Constants.TASK_POSITION_DISTANCE_MAX){
            logger.info("用户不在打卡范围内!");
            return R.fail("您不在打卡范围内!");
        }

        //改变任务状态
        TUserTask userTask = userTaskService.getTUserTaskByTaskIdUserId(taskId, userId);
        userTask.setStatus(Constants.TASK_FINISHED);
        userTask.setCompleteTime(LocalDateTime.now());
        int i = userTaskService.updateTUserTask(userTask);


        //用户增加积分
        Point userPoints = pointService.selectPointByUserId(userId);
        Long taskPoints = task.getPoints();
        userPoints.setPoints(userPoints.getPoints() + taskPoints);
        int j = pointService.updatePoint(userPoints);

        //判断更新
        if(i == 0 || j == 0) {
            logger.info("系统错误，请稍后再试!");
            return R.fail("系统错误，请稍后再试!");
        }

        logger.info("用户打卡成功!");
        return R.ok("打卡成功!");
    }

    /**
     * 摄像头打卡
     * @param body
     * @return
     */
    @PostMapping("/camera")
    public R cameraTask(@RequestBody String body) throws Exception {
        logger.info("【请求开始】用户摄像头打卡检测,请求参数,body:{}", body);

        //获取参数
        String imageUrl = JacksonUtil.parseString(body,"imageUrl");
        String taskId = JacksonUtil.parseString(body,"taskId");

        if(StringUtils.isEmpty(imageUrl)){
            logger.info("请拍摄图片!");
            return R.fail("请拍摄图片!");
        }
        // 创建AccessKey ID和AccessKey Secret，请参考https://help.aliyun.com/document_detail/175144.html
        // 如果您使用的是RAM用户的AccessKey，还需要为子账号授予权限AliyunVIAPIFullAccess，请参考https://help.aliyun.com/document_detail/145025.html
        // 从环境变量读取配置的AccessKey ID和AccessKey Secret。运行代码示例前必须先配置环境变量。
        //String accessKeyId = System.getenv("ALIBABA_CLOUD_ACCESS_KEY_ID");
        //String accessKeySecret = System.getenv("ALIBABA_CLOUD_ACCESS_KEY_SECRET");
        String accessKeyId = imageDtAccessKeyId;
        String accessKeySecret = imageDtAccessKeySecret;
        com.aliyun.facebody20191230.Client client = CompareFaceManager.createClient(accessKeyId, accessKeySecret);
        // 场景一，使用本地文件
        //InputStream inputStreamA = new FileInputStream(new File("/tmp/CompareFace-right.png"));
        // 场景二，使用任意可访问的url
        //assert imageUrl != null;
        URL url = new URL(imageUrl);

        //查询用户个人照片
        List<User> users = userService.queryAllAdvancedUsers();
        InputStream inputStreamB = url.openConnection().getInputStream();
        User resUser = null;
        //TODO 优化代码
        for(User user : users){
            if(StringUtils.isEmpty(user.getPhoto())){
                continue;
            }
            URL compareUrl = new URL(user.getPhoto());
            InputStream inputStreamA = compareUrl.openConnection().getInputStream();
            com.aliyun.facebody20191230.models.CompareFaceAdvanceRequest compareFaceAdvanceRequest =
                    new com.aliyun.facebody20191230.models.CompareFaceAdvanceRequest()
                    .setImageURLAObject(inputStreamA)
                    .setImageURLBObject(inputStreamB);
            com.aliyun.teautil.models.RuntimeOptions runtime = new com.aliyun.teautil.models.RuntimeOptions();
            try {
                // 复制代码运行请自行打印 API 的返回值
                //检测是否有符合人脸的用户
                CompareFaceResponse compareFaceResponse = client.compareFaceAdvance(compareFaceAdvanceRequest, runtime);
                // 获取整体结果
                CompareFaceResponseBody responseBody = compareFaceResponse.getBody();
                //System.out.println(com.aliyun.teautil.Common.toJSONString(TeaModel.buildMap(compareFaceResponse)));
                //两张图片中的最大人脸属于同一个人的置信度，取值范围0~100。供参考的三个阈值是61，69和75，分别对应千分之一，万分之一和十万分之一误识率。
                // 阈值设置越高，误识率越低，通过率也越低，对安全性要求越高的场合，可以设置更高的阈值。
                // 如果某张图片中没有人脸，则报错误信息
                if(responseBody.getData().getConfidence() >= UserConstants.CAMERA_AUTH_MEDIUM) {
                    //识别成功
                    resUser = user;
                    logger.info("识别成功，相似度为:{}",responseBody.getData().getConfidence());
                    break;
                }
            } catch (TeaException teaException) {
                // 获取整体报错信息
                //System.out.println(com.aliyun.teautil.Common.toJSONString(teaException));
                // 获取单个字段
                //System.out.println(teaException.getCode());

                logger.info(teaException.getMessage());
                return R.fail(teaException.getDescription());
            }
        }
        if(StringUtils.isNull(resUser)){
            //没有这个用户
            logger.info("用户不存在，请检查小程序是否认证!");
            return R.fail("用户不存在，请检查小程序是否认证!");
        }

        //resUser为最终提取到的user用户
        String userId = resUser.getUid();
        //每个摄像头应当有对应的taskId
        Task task = taskService.selectTaskByUid(taskId);
        TUserTask userTask = userTaskService.getTUserTaskByTaskIdUserId(taskId, userId);

        //更新任务
        userTask.setStatus(Constants.TASK_FINISHED);
        userTask.setCompleteTime(LocalDateTime.now());
        int i = userTaskService.updateTUserTask(userTask);


        //用户增加积分
        Point userPoints = pointService.selectPointByUserId(userId);
        Long taskPoints = task.getPoints();
        userPoints.setPoints(userPoints.getPoints() + taskPoints);
        int j = pointService.updatePoint(userPoints);

        //判断更新
        if(i == 0 || j == 0) {
            logger.info("系统错误，请稍后再试!");
            return R.fail("系统错误，请稍后再试!");
        }

        logger.info("用户打卡成功!");
        return R.ok("打卡成功!");
    }
}
