package com.hgnuacm.wx.domain;

import lombok.Data;

@Data
public class AdmissionAuthInfo {
    private String request_id;

    private Integer time_used;

    private String error_message;

    private String[] text_info;
}
