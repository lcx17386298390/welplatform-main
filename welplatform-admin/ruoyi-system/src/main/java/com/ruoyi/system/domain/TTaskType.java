package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 任务种类管理对象 t_task_type
 * 
 * @author ruoyi
 * @date 2024-03-29
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

    /** 任务关键词 */
    @Excel(name = "任务关键词")
    private String typeDesc;

    /** 任务id */
    @Excel(name = "任务id")
    private String taskId;

    /** 纬度,当任务类型为2时需要 */
    @Excel(name = "纬度,当任务类型为2时需要")
    private Long latitude;

    /** 经度，当任务类型为2时需要 */
    @Excel(name = "经度，当任务类型为2时需要")
    private Long longitude;

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
    public void setTaskId(String taskId) 
    {
        this.taskId = taskId;
    }

    public String getTaskId() 
    {
        return taskId;
    }
    public void setLatitude(Long latitude) 
    {
        this.latitude = latitude;
    }

    public Long getLatitude() 
    {
        return latitude;
    }
    public void setLongitude(Long longitude) 
    {
        this.longitude = longitude;
    }

    public Long getLongitude() 
    {
        return longitude;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("uid", getUid())
            .append("typeName", getTypeName())
            .append("type", getType())
            .append("typeDesc", getTypeDesc())
            .append("taskId", getTaskId())
            .append("latitude", getLatitude())
            .append("longitude", getLongitude())
            .toString();
    }
}
