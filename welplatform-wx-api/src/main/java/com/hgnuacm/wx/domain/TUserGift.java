
package com.hgnuacm.wx.domain;
import com.hgnuacm.common.annotation.Excel;
import com.hgnuacm.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 用户购买礼品对象 t_user_gift
 *
 * @author Rs
 * @date 2024-04-02
 */
public class TUserGift extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 购买id */
    private Long uid;

    /** 用户id */
    @Excel(name = "用户id")
    private String userId;

    /** 礼品id */
    @Excel(name = "礼品id")
    private String giftId;

    /** 用户地址 */
    @Excel(name = "用户地址")
    private String userAddress;

    public void setUid(Long uid)
    {
        this.uid = uid;
    }

    public Long getUid()
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
