package com.hgnuacm.wx.controller;

import com.hgnuacm.common.core.domain.R;
import com.hgnuacm.common.utils.JacksonUtil;
import com.hgnuacm.common.utils.StringUtils;
import com.hgnuacm.wx.domain.Student;
import com.hgnuacm.wx.domain.User;
import com.hgnuacm.wx.service.StudentService;
import com.hgnuacm.wx.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wx/user")
@Validated
public class WxUserInfoController {

    private static final Logger logger = LoggerFactory.getLogger(WxUserInfoController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private StudentService studentService;

    /**
     * 获取用户信息
     * @param body
     * @return
     */
    @PostMapping("/getInfo")
    public Object getUserInfo(@RequestBody String body){
        logger.info("【请求开始】获取用户信息,请求参数，body:{}", body);
        String userId = JacksonUtil.parseString(body, "userId");

        User user = userService.selectUserByUid(userId);
        //检测用户
        if(StringUtils.isNull(user)){
            logger.info("【请求结束】获取用户失败!");
            return R.fail("获取用户失败");
        }

        //根据用户身份证号，获取t_student表中的数据,!!!用户需认证!!!
        String idCard = user.getIdCard();
        if(StringUtils.isEmpty(idCard)){
            logger.info("【请求结束】用户未认证!");
            return R.fail("用户未认证");
        }

        //根据身份证号取数据
        List<Student> studentList = studentService.queryByIdCard(idCard);
        if(studentList.size() != 1){
            logger.info("【请求结束】用户有多个或未找到，系统内部错误!");
            return R.fail("用户不存在!");
        }

        Student student = studentList.get(0);
        String name = student.getName();
        String academy = student.getAcademy();
        String level = student.getLevel();
        String major = student.getMajor();
        String grade = student.getGrade();
        String classroom = student.getClassroom();
        String studentId = student.getStudentId();
        String nation = student.getNation();
        String school = student.getSchool();
        LocalDateTime enterTime = student.getEnterTime();
        LocalDateTime graduateTime = student.getGraduateTime();

        //存放数据
        Map<String,Object> userInfoList = new HashMap<>();
        userInfoList.put("姓名", name);
        userInfoList.put("学校", school);
        userInfoList.put("学院", academy);
        userInfoList.put("培养层次", level);
        userInfoList.put("专业", major);
        userInfoList.put("年级", grade);
        userInfoList.put("班级", classroom);
        userInfoList.put("学号", studentId);
        userInfoList.put("身份证号(证件号)", idCard);
        userInfoList.put("入学时间", enterTime);
        userInfoList.put("毕业时间", graduateTime);
        userInfoList.put("民族", nation);

        logger.info("【请求结束】获取用户信息成功,返回参数{}", userInfoList);
        return R.ok(userInfoList);
    }
}
