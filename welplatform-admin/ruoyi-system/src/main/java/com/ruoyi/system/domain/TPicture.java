package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 照片管理对象 t_picture
 * 
 * @author ruoyi
 * @date 2024-03-29
 */
public class TPicture extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 图片id */
    private String uid;

    /** 文件名 */
    @Excel(name = "文件名")
    private String picName;

    /** 文件地址 */
    @Excel(name = "文件地址")
    private String picUrl;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    /** 七牛云地址 */
    @Excel(name = "七牛云地址")
    private String qiNiuUrl;

    /** 文件扩展名 */
    @Excel(name = "文件扩展名")
    private String picExpandedName;

    public void setUid(String uid) 
    {
        this.uid = uid;
    }

    public String getUid() 
    {
        return uid;
    }
    public void setPicName(String picName) 
    {
        this.picName = picName;
    }

    public String getPicName() 
    {
        return picName;
    }
    public void setPicUrl(String picUrl) 
    {
        this.picUrl = picUrl;
    }

    public String getPicUrl() 
    {
        return picUrl;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setQiNiuUrl(String qiNiuUrl) 
    {
        this.qiNiuUrl = qiNiuUrl;
    }

    public String getQiNiuUrl() 
    {
        return qiNiuUrl;
    }
    public void setPicExpandedName(String picExpandedName) 
    {
        this.picExpandedName = picExpandedName;
    }

    public String getPicExpandedName() 
    {
        return picExpandedName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("uid", getUid())
            .append("picName", getPicName())
            .append("picUrl", getPicUrl())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("status", getStatus())
            .append("qiNiuUrl", getQiNiuUrl())
            .append("picExpandedName", getPicExpandedName())
            .toString();
    }
}
