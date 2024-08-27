package com.hgnuacm.wx.domain;

import lombok.Data;

@Data
public class DtsUserPoint {

    private String userName;

    private Long points;

    private String avatar;

    public DtsUserPoint(String userName, Long points, String avatar) {
        this.userName = userName;
        this.points = points;
        this.avatar = avatar;
    }
}
