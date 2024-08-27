package com.hgnuacm.wx.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DtsGift {

    private String giftId;
    private String giftName;
    private Long requiredPoints;
    private Long stockQuantity;
    private String imageLink;
    private BigDecimal price;



    public DtsGift(String giftId, String giftName, Long requiredPoints, Long stockQuantity, String imageLink, BigDecimal price) {
        this.giftId = giftId;
        this.giftName = giftName;
        this.requiredPoints = requiredPoints;
        this.stockQuantity = stockQuantity;
        this.imageLink = imageLink;
        this.price = price;
    }
}
