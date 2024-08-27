package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户微信数据管理对象 t_user_formid
 * 
 * @author ruoyi
 * @date 2024-03-29
 */
public class TUserFormid extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 缓存的FormId */
    @Excel(name = "缓存的FormId")
    private String formId;

    /** 是FormId还是prepayId */
    @Excel(name = "是FormId还是prepayId")
    private Integer isprepay;

    /** 可用次数，fromId为1，prepay为3，用1次减1 */
    @Excel(name = "可用次数，fromId为1，prepay为3，用1次减1")
    private Integer useAmount;

    /** 过期时间，腾讯规定为7天 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "过期时间，腾讯规定为7天", width = 30, dateFormat = "yyyy-MM-dd")
    private Date expireTime;

    /** 微信登录openid */
    @Excel(name = "微信登录openid")
    private String openId;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date addTime;

    /** 逻辑删除 */
    @Excel(name = "逻辑删除")
    private Integer deleted;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setFormId(String formId) 
    {
        this.formId = formId;
    }

    public String getFormId() 
    {
        return formId;
    }
    public void setIsprepay(Integer isprepay) 
    {
        this.isprepay = isprepay;
    }

    public Integer getIsprepay() 
    {
        return isprepay;
    }
    public void setUseAmount(Integer useAmount) 
    {
        this.useAmount = useAmount;
    }

    public Integer getUseAmount() 
    {
        return useAmount;
    }
    public void setExpireTime(Date expireTime) 
    {
        this.expireTime = expireTime;
    }

    public Date getExpireTime() 
    {
        return expireTime;
    }
    public void setOpenId(String openId) 
    {
        this.openId = openId;
    }

    public String getOpenId() 
    {
        return openId;
    }
    public void setAddTime(Date addTime) 
    {
        this.addTime = addTime;
    }

    public Date getAddTime() 
    {
        return addTime;
    }
    public void setDeleted(Integer deleted) 
    {
        this.deleted = deleted;
    }

    public Integer getDeleted() 
    {
        return deleted;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("formId", getFormId())
            .append("isprepay", getIsprepay())
            .append("useAmount", getUseAmount())
            .append("expireTime", getExpireTime())
            .append("openId", getOpenId())
            .append("addTime", getAddTime())
            .append("updateTime", getUpdateTime())
            .append("deleted", getDeleted())
            .toString();
    }
}
