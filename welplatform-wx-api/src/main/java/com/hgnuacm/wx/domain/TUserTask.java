package com.hgnuacm.wx.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hgnuacm.common.annotation.Excel;
import com.hgnuacm.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 用户任务关联对象 t_user_task
 *
 * @author ruoyi
 * @date 2024-03-06
 */
public class TUserTask extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户任务关联id */
    private String uid;

    /** 用户id */
    @Excel(name = "用户id")
    private String userId;

    /** 任务id */
    @Excel(name = "任务id")
    private String taskId;

    /** 完成时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "完成时间", width = 30, dateFormat = "yyyy-MM-dd")
    private LocalDateTime completeTime;

    /** 任务完成情况 */
    @Excel(name = "任务完成情况")
    private Integer status;

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
    public void setTaskId(String taskId)
    {
        this.taskId = taskId;
    }

    public String getTaskId()
    {
        return taskId;
    }

    public LocalDateTime getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(LocalDateTime completeTime) {
        this.completeTime = completeTime;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Integer getStatus()
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("uid", getUid())
                .append("userId", getUserId())
                .append("taskId", getTaskId())
                .append("completeTime", getCompleteTime())
                .append("status", getStatus())
                .toString();
    }
}
