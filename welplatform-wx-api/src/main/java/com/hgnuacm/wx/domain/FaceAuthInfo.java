package com.hgnuacm.wx.domain;

import lombok.Data;

@Data
public class FaceAuthInfo {
    private String msg;

    private boolean success;

    private String code;

    private FaceAuthData data;
}
