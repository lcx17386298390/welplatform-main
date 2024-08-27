package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 学生管理对象 t_student
 * 
 * @author ruoyi
 * @date 2024-03-29
 */
public class TStudent extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    /** 学院 */
    @Excel(name = "学院")
    private String academy;

    /** 培养层次(本科) */
    @Excel(name = "培养层次(本科)")
    private String level;

    /** 专业 */
    @Excel(name = "专业")
    private String major;

    /** 年级 */
    @Excel(name = "年级")
    private String grade;

    /** 班级 */
    @Excel(name = "班级")
    private String classroom;

    /** 学号 */
    @Excel(name = "学号")
    private String studentId;

    /** 民族 */
    @Excel(name = "民族")
    private String nation;

    /** 学校 */
    @Excel(name = "学校")
    private String school;

    /** 身份证号 */
    @Excel(name = "身份证号")
    private String idCard;

    /** 入学时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "入学时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date enterTime;

    /** 毕业时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "毕业时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date graduateTime;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setAcademy(String academy) 
    {
        this.academy = academy;
    }

    public String getAcademy() 
    {
        return academy;
    }
    public void setLevel(String level) 
    {
        this.level = level;
    }

    public String getLevel() 
    {
        return level;
    }
    public void setMajor(String major) 
    {
        this.major = major;
    }

    public String getMajor() 
    {
        return major;
    }
    public void setGrade(String grade) 
    {
        this.grade = grade;
    }

    public String getGrade() 
    {
        return grade;
    }
    public void setClassroom(String classroom) 
    {
        this.classroom = classroom;
    }

    public String getClassroom() 
    {
        return classroom;
    }
    public void setStudentId(String studentId) 
    {
        this.studentId = studentId;
    }

    public String getStudentId() 
    {
        return studentId;
    }
    public void setNation(String nation) 
    {
        this.nation = nation;
    }

    public String getNation() 
    {
        return nation;
    }
    public void setSchool(String school) 
    {
        this.school = school;
    }

    public String getSchool() 
    {
        return school;
    }
    public void setIdCard(String idCard) 
    {
        this.idCard = idCard;
    }

    public String getIdCard() 
    {
        return idCard;
    }
    public void setEnterTime(Date enterTime) 
    {
        this.enterTime = enterTime;
    }

    public Date getEnterTime() 
    {
        return enterTime;
    }
    public void setGraduateTime(Date graduateTime) 
    {
        this.graduateTime = graduateTime;
    }

    public Date getGraduateTime() 
    {
        return graduateTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("academy", getAcademy())
            .append("level", getLevel())
            .append("major", getMajor())
            .append("grade", getGrade())
            .append("classroom", getClassroom())
            .append("studentId", getStudentId())
            .append("nation", getNation())
            .append("school", getSchool())
            .append("idCard", getIdCard())
            .append("enterTime", getEnterTime())
            .append("graduateTime", getGraduateTime())
            .toString();
    }
}
