package com.ruoyi.system.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 广告管理对象 t_advertise
 * 
 * @author ruoyi
 * @date 2024-03-29
 */
public class TAdvertise extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 广告id */
    private String uid;

    /** 广告标题 */
    @Excel(name = "广告标题")
    private String title;

    /** 设计关键词 */
    @Excel(name = "设计关键词")
    private String keyword;

    /** 费用 */
    @Excel(name = "费用")
    private BigDecimal cost;

    /** 广告内容 */
    @Excel(name = "广告内容")
    private String content;

    /** 广告点击数 */
    @Excel(name = "广告点击数")
    private Long clickCount;

    /** 推荐等级 */
    @Excel(name = "推荐等级")
    private Integer level;

    /** 广告图片uid */
    @Excel(name = "广告图片uid")
    private String fileUid;

    public void setUid(String uid) 
    {
        this.uid = uid;
    }

    public String getUid() 
    {
        return uid;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setKeyword(String keyword) 
    {
        this.keyword = keyword;
    }

    public String getKeyword() 
    {
        return keyword;
    }
    public void setCost(BigDecimal cost) 
    {
        this.cost = cost;
    }

    public BigDecimal getCost() 
    {
        return cost;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setClickCount(Long clickCount) 
    {
        this.clickCount = clickCount;
    }

    public Long getClickCount() 
    {
        return clickCount;
    }
    public void setLevel(Integer level) 
    {
        this.level = level;
    }

    public Integer getLevel() 
    {
        return level;
    }
    public void setFileUid(String fileUid) 
    {
        this.fileUid = fileUid;
    }

    public String getFileUid() 
    {
        return fileUid;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("uid", getUid())
            .append("title", getTitle())
            .append("keyword", getKeyword())
            .append("cost", getCost())
            .append("content", getContent())
            .append("clickCount", getClickCount())
            .append("level", getLevel())
            .append("fileUid", getFileUid())
            .toString();
    }
}
