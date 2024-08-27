package com.hgnuacm.wx.domain;

import java.time.LocalDateTime;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.hgnuacm.common.annotation.Excel;
import com.hgnuacm.common.core.domain.BaseEntity;

/**
 * 任务对象 t_task
 * 
 * @author violet
 * @date 2024-01-20
 */
public class Task extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 任务id */
    private String uid;

    /** 任务标题 */
    @Excel(name = "任务标题")
    private String taskName;

    /** 任务描述 */
    @Excel(name = "任务描述")
    private String taskDescription;

    /** 任务积分 */
    @Excel(name = "任务积分")
    private Long points;

    /** 任务内容 */
    @Excel(name = "任务内容")
    private String content;

    /** 任务过期时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "任务过期时间", width = 30, dateFormat = "yyyy-MM-dd")
    private LocalDateTime expireTime;

    /** 任务类型 */
    @Excel(name = "任务类型")
    private Integer type;

    /** 任务点击数 */
    @Excel(name = "任务点击数")
    private Long clickCount;

    /** 管理员id */
    @Excel(name = "管理员id")
    private String adminUid;

    /** 发布者 */
    @Excel(name = "发布者")
    private String author;

    /** 推荐等级(0:正常) */
    @Excel(name = "推荐等级(0:正常)")
    private Integer level;

    /** 是否发布:0:否：1：是 */
    @Excel(name = "是否发布:0:否：1：是")
    private String isPublish;

    /** 任务图片uid */
    @Excel(name = "任务图片uid")
    private String fileUid;

    public void setUid(String uid) 
    {
        this.uid = uid;
    }

    public String getUid() 
    {
        return uid;
    }
    public void setTaskName(String taskName) 
    {
        this.taskName = taskName;
    }

    public String getTaskName() 
    {
        return taskName;
    }
    public void setTaskDescription(String taskDescription) 
    {
        this.taskDescription = taskDescription;
    }

    public String getTaskDescription() 
    {
        return taskDescription;
    }
    public void setPoints(Long points) 
    {
        this.points = points;
    }

    public Long getPoints() 
    {
        return points;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }

    public void setType(Integer type) 
    {
        this.type = type;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }

    public Integer getType()
    {
        return type;
    }
    public void setClickCount(Long clickCount) 
    {
        this.clickCount = clickCount;
    }

    public Long getClickCount() 
    {
        return clickCount;
    }
    public void setAdminUid(String adminUid) 
    {
        this.adminUid = adminUid;
    }

    public String getAdminUid() 
    {
        return adminUid;
    }
    public void setAuthor(String author) 
    {
        this.author = author;
    }

    public String getAuthor() 
    {
        return author;
    }
    public void setLevel(Integer level) 
    {
        this.level = level;
    }

    public Integer getLevel() 
    {
        return level;
    }
    public void setIsPublish(String isPublish) 
    {
        this.isPublish = isPublish;
    }

    public String getIsPublish() 
    {
        return isPublish;
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
            .append("taskName", getTaskName())
            .append("taskDescription", getTaskDescription())
            .append("points", getPoints())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("content", getContent())
            .append("expireTime", getExpireTime())
            .append("type", getType())
            .append("clickCount", getClickCount())
            .append("adminUid", getAdminUid())
            .append("author", getAuthor())
            .append("level", getLevel())
            .append("isPublish", getIsPublish())
            .append("fileUid", getFileUid())
            .toString();
    }
}
