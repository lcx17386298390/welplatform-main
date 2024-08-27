package com.hgnuacm.wx.domain;

import lombok.Data;

@Data
public class WxLoginInfo {
    private static final long serialVersionUID = -7722430332896313642L;
    private String code;
    private UserInfoVo userInfo;
    private Integer shareUserId;
}
