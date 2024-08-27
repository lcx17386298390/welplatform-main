package com.hgnuacm.wx.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.hgnuacm.common.annotation.Excel;
import com.hgnuacm.common.core.domain.BaseEntity;

import java.time.LocalDateTime;

/**
 * 用户对象 t_user
 * 
 * @author violet
 * @date 2024-01-20
 */
public class User extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户id */
    private String uid;

    /** 用户名 */
    @Excel(name = "用户名")
    private String userName;
    @Excel(name = "密码")
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /** 性别 */
    @Excel(name = "性别")
    private Integer gender;

    /** 个人头像 */
    @Excel(name = "个人头像")
    private String avatar;

    /** 手机 */
    @Excel(name = "手机")
    private String mobile;

    @Excel(name = "邮箱")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /** ip来源 */
    @Excel(name = "ip来源")
    private String ipSource;

    /** 学校 */
    @Excel(name = "学校")
    private String school;

    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    /** 身份证 */
    @Excel(name = "身份证")
    private String idCard;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    /** 学号 */
    @Excel(name = "学号")
    private String studentId;

    /** 录取通知书审核状态 */
    @Excel(name = "录取通知书审核状态")
    private Integer admissionStatus;

    /** 专业 */
    @Excel(name = "专业")
    private String major;


    @Excel(name = "个人照片")
    private String photo;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Excel(name = "创建时间")
    private LocalDateTime createDate;
    @Excel(name = "微信Openid")
    private String weixinOpenid;
    @Excel(name = "微信shareUserId")
    private Integer shareUserId;

    public Integer getShareUserId() {
        return shareUserId;
    }

    public void setShareUserId(Integer shareUserId) {
        this.shareUserId = shareUserId;
    }

    public String getWeixinOpenid() {
        return weixinOpenid;
    }

    public void setWeixinOpenid(String weixinOpenid) {
        this.weixinOpenid = weixinOpenid;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public void setUid(String uid)
    {
        this.uid = uid;
    }

    public String getUid()
    {
        return uid;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }
    public void setGender(Integer gender) 
    {
        this.gender = gender;
    }

    public Integer getGender() 
    {
        return gender;
    }
    public void setAvatar(String avatar) 
    {
        this.avatar = avatar;
    }

    public String getAvatar() 
    {
        return avatar;
    }
    public void setMobile(String mobile) 
    {
        this.mobile = mobile;
    }

    public String getMobile() 
    {
        return mobile;
    }
    public void setIpSource(String ipSource) 
    {
        this.ipSource = ipSource;
    }

    public String getIpSource() 
    {
        return ipSource;
    }
    public void setSchool(String school) 
    {
        this.school = school;
    }

    public String getSchool() 
    {
        return school;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setIdCard(String idCard) 
    {
        this.idCard = idCard;
    }

    public String getIdCard() 
    {
        return idCard;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setStudentId(String studentId) 
    {
        this.studentId = studentId;
    }

    public String getStudentId() 
    {
        return studentId;
    }
    public void setAdmissionStatus(Integer admissionStatus) 
    {
        this.admissionStatus = admissionStatus;
    }

    public Integer getAdmissionStatus() 
    {
        return admissionStatus;
    }
    public void setMajor(String major) 
    {
        this.major = major;
    }

    public String getMajor() 
    {
        return major;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", gender=" + gender +
                ", avatar='" + avatar + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", ipSource='" + ipSource + '\'' +
                ", school='" + school + '\'' +
                ", name='" + name + '\'' +
                ", idCard='" + idCard + '\'' +
                ", status=" + status +
                ", studentId='" + studentId + '\'' +
                ", admissionStatus=" + admissionStatus +
                ", major='" + major + '\'' +
                ", createDate=" + createDate +
                ", weixinOpenid='" + weixinOpenid + '\'' +
                ", shareUserId=" + shareUserId +
                '}';
    }
}
