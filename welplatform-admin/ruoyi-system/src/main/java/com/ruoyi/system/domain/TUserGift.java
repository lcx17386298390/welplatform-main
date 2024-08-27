package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户礼品管理对象 t_user_gift
 * 
 * @author ruoyi
 * @date 2024-03-29
 */
public class TUserGift extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 购买id */
    private String uid;

    /** 用户id */
    @Excel(name = "用户id")
    private String userId;

    /** 礼品id */
    @Excel(name = "礼品id")
    private String giftId;

    /** 用户地址 */
    @Excel(name = "用户地址")
    private String userAddress;

    public void setUid(String uid) 
    {
        this.uid = uid;
    }

    public String getUid() 
    {
        return uid;
    }
    public void setUserId(String userId) 
    {
        this.userId = userId;
    }

    public String getUserId() 
    {
        return userId;
    }
    public void setGiftId(String giftId) 
    {
        this.giftId = giftId;
    }

    public String getGiftId() 
    {
        return giftId;
    }
    public void setUserAddress(String userAddress) 
    {
        this.userAddress = userAddress;
    }

    public String getUserAddress() 
    {
        return userAddress;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("uid", getUid())
            .append("userId", getUserId())
            .append("giftId", getGiftId())
            .append("remark", getRemark())
            .append("userAddress", getUserAddress())
            .toString();
    }
}
