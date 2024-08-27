package com.hgnuacm.wx.domain;

import com.hgnuacm.common.annotation.Excel;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Student {
    private static final long serialVersionUID = 1L;
    private String id;
    @Excel(name = "姓名")
    private String name;
    @Excel(name = "学院")
    private String academy;
    @Excel(name = "学历")
    private String level;
    @Excel(name = "专业")
    private String major;
    @Excel(name = "年级")
    private String grade;
    @Excel(name = "班级")
    private String classroom;
    @Excel(name = "学号")
    private String studentId;
    @Excel(name = "民族")
    private String nation;
    @Excel(name = "学校")
    private String school;
    @Excel(name = "身份证号")
    private String idCard;
    @Excel(name = "入学时间")
    private LocalDateTime enterTime;
    @Excel(name = "毕业时间")
    private LocalDateTime graduateTime;
}
