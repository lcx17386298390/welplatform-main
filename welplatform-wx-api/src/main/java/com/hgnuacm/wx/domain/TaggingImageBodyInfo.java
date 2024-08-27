package com.hgnuacm.wx.domain;

import lombok.Data;

@Data
public class TaggingImageBodyInfo {

    private String RequestId;

    private RequestData[] Data;
}
