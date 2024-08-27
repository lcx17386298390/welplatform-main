package com.hgnuacm.wx.domain;

import lombok.Data;

@Data
public class FaceAuthData {
    private String order_no;

    private double score;

    private String msg;

    private int incorrect;

    private char sex;

    private String birthday;

    private String address;

}
