package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户管理对象 t_user
 * 
 * @author ruoyi
 * @date 2024-03-29
 */
public class TUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户id */
    private String uid;

    /** 用户名 */
    @Excel(name = "用户名")
    private String userName;

    /** 用户密码 */
    @Excel(name = "用户密码")
    private String password;

    /** 性别(0:男,1:女) */
    @Excel(name = "性别(0:男,1:女)")
    private Integer gender;

    /** 个人头像 */
    @Excel(name = "个人头像")
    private String avatar;

    /** 手机 */
    @Excel(name = "手机")
    private String mobile;

    /** 最近一次ip来源 */
    @Excel(name = "最近一次ip来源")
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

    /** 小程序openid */
    @Excel(name = "小程序openid")
    private String weixinOpenid;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long shareUserId;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

    /** 个人照片 */
    @Excel(name = "个人照片")
    private String photo;

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
    public void setPassword(String password) 
    {
        this.password = password;
    }

    public String getPassword() 
    {
        return password;
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
    public void setWeixinOpenid(String weixinOpenid) 
    {
        this.weixinOpenid = weixinOpenid;
    }

    public String getWeixinOpenid() 
    {
        return weixinOpenid;
    }
    public void setShareUserId(Long shareUserId) 
    {
        this.shareUserId = shareUserId;
    }

    public Long getShareUserId() 
    {
        return shareUserId;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setPhoto(String photo) 
    {
        this.photo = photo;
    }

    public String getPhoto() 
    {
        return photo;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("uid", getUid())
            .append("userName", getUserName())
            .append("password", getPassword())
            .append("gender", getGender())
            .append("avatar", getAvatar())
            .append("mobile", getMobile())
            .append("ipSource", getIpSource())
            .append("school", getSchool())
            .append("name", getName())
            .append("idCard", getIdCard())
            .append("createTime", getCreateTime())
            .append("status", getStatus())
            .append("studentId", getStudentId())
            .append("admissionStatus", getAdmissionStatus())
            .append("major", getMajor())
            .append("weixinOpenid", getWeixinOpenid())
            .append("shareUserId", getShareUserId())
            .append("email", getEmail())
            .append("photo", getPhoto())
            .toString();
    }
}
