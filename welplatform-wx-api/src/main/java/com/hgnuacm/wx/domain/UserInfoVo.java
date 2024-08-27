package com.hgnuacm.wx.domain;

import com.hgnuacm.common.annotation.Excel;
import lombok.Data;

@Data
public class UserInfoVo {
    private static final long serialVersionUID = -5813029516433359765L;
    private String userId;

    /** 用户名 */
    private String nickName;

    /** 性别 */
    private Integer gender;

    /** 个人头像 */
    private String avatarUrl;

    private String country;
    private String province;
    private String city;
    private String language;

    /** 手机 */
    private String phone;

    /** ip来源 */
    //private String ipSource;

    /** 学校 */
    private String school;

    /** 姓名 */
    private String name;

    /** 身份证 */
    private String idCard;

    /** 状态 */
    private Integer status;

    /** 学号 */
    private String studentId;

    /** 录取通知书审核状态 */
    private Integer admissionStatus;

    /** 专业 */
    private String major;

    private Byte userLevel;// 用户层级 0 普通用户，1 VIP用户，2 区域代理用户
    private String userLevelDesc;// 代理用户描述

    private String registerDate;//注册日期

    private String photo;

    private Long points;

    //private LocalDateTime createDate;
}
