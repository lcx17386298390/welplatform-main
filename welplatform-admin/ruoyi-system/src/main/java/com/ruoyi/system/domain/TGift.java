package com.ruoyi.system.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 礼品管理对象 t_gift
 * 
 * @author ruoyi
 * @date 2024-03-29
 */
public class TGift extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 礼品编号，作为主键 */
    private String giftId;

    /** 礼品名称 */
    @Excel(name = "礼品名称")
    private String giftName;

    /** 兑换该礼品所需的积分数量 */
    @Excel(name = "兑换该礼品所需的积分数量")
    private Long requiredPoints;

    /** 对礼品的简要描述 */
    @Excel(name = "对礼品的简要描述")
    private String description;

    /** 库存数量，记录礼品当前的库存量 */
    @Excel(name = "库存数量，记录礼品当前的库存量")
    private Long stockQuantity;

    /** 图片链接，用于展示礼品的图片 */
    @Excel(name = "图片链接，用于展示礼品的图片")
    private String imageLink;

    /** 礼品价格 */
    @Excel(name = "礼品价格")
    private BigDecimal price;

    /** 礼品来源公司 */
    @Excel(name = "礼品来源公司")
    private String sourceCompany;

    public void setGiftId(String giftId) 
    {
        this.giftId = giftId;
    }

    public String getGiftId() 
    {
        return giftId;
    }
    public void setGiftName(String giftName) 
    {
        this.giftName = giftName;
    }

    public String getGiftName() 
    {
        return giftName;
    }
    public void setRequiredPoints(Long requiredPoints) 
    {
        this.requiredPoints = requiredPoints;
    }

    public Long getRequiredPoints() 
    {
        return requiredPoints;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setStockQuantity(Long stockQuantity) 
    {
        this.stockQuantity = stockQuantity;
    }

    public Long getStockQuantity() 
    {
        return stockQuantity;
    }
    public void setImageLink(String imageLink) 
    {
        this.imageLink = imageLink;
    }

    public String getImageLink() 
    {
        return imageLink;
    }
    public void setPrice(BigDecimal price) 
    {
        this.price = price;
    }

    public BigDecimal getPrice() 
    {
        return price;
    }
    public void setSourceCompany(String sourceCompany) 
    {
        this.sourceCompany = sourceCompany;
    }

    public String getSourceCompany() 
    {
        return sourceCompany;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("giftId", getGiftId())
            .append("giftName", getGiftName())
            .append("requiredPoints", getRequiredPoints())
            .append("description", getDescription())
            .append("stockQuantity", getStockQuantity())
            .append("imageLink", getImageLink())
            .append("price", getPrice())
            .append("sourceCompany", getSourceCompany())
            .toString();
    }
}
