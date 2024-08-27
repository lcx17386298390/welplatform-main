package com.hgnuacm.wx.domain;

import lombok.Data;

@Data
public class IdCardAuthInfo {
    private String status;

    private String msg;

    private String idCard;

    private String name;

    private String sex;

    private String area;

    private String city;

    private String prefecture;

    private String birthday;

    private String addrCode;

    private String lastCode;

    private String traceId;
}
