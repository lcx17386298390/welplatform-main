package com.hgnuacm.wx.domain;

import com.hgnuacm.common.annotation.Excel;
import com.hgnuacm.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 任务类型对象 t_task_type
 * 
 * @author Rs
 * @date 2024-03-02
 */
public class TTaskType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 任务类型id */
    private String uid;

    /** 任务类型名字 */
    @Excel(name = "任务类型名字")
    private String typeName;

    /** 任务类型 */
    @Excel(name = "任务类型")
    private Integer type;

    /** 任务类型介绍 */
    @Excel(name = "任务类型介绍")
    private String typeDesc;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
    @Excel(name = "经度")
    private double latitude;
    @Excel(name = "纬度")
    private double longitude;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    @Excel(name = "任务Id")
    private String taskId;

    public void setUid(String uid) 
    {
        this.uid = uid;
    }

    public String getUid() 
    {
        return uid;
    }
    public void setTypeName(String typeName) 
    {
        this.typeName = typeName;
    }

    public String getTypeName() 
    {
        return typeName;
    }
    public void setType(Integer type) 
    {
        this.type = type;
    }

    public Integer getType() 
    {
        return type;
    }
    public void setTypeDesc(String typeDesc) 
    {
        this.typeDesc = typeDesc;
    }

    public String getTypeDesc() 
    {
        return typeDesc;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("uid", getUid())
            .append("typeName", getTypeName())
            .append("type", getType())
            .append("typeDesc", getTypeDesc())
            .toString();
    }
}
