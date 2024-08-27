package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 积分管理对象 t_point
 * 
 * @author ruoyi
 * @date 2024-03-29
 */
public class TPoint extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 积分记录id */
    private String uid;

    /** 用户id */
    @Excel(name = "用户id")
    private String userId;

    /** 用户积分值 */
    @Excel(name = "用户积分值")
    private Long points;

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
    public void setPoints(Long points) 
    {
        this.points = points;
    }

    public Long getPoints() 
    {
        return points;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("uid", getUid())
            .append("userId", getUserId())
            .append("points", getPoints())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
