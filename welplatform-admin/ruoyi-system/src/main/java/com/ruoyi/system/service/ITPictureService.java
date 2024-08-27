package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.TPicture;

/**
 * 照片管理Service接口
 * 
 * @author ruoyi
 * @date 2024-03-29
 */
public interface ITPictureService 
{
    /**
     * 查询照片管理
     * 
     * @param uid 照片管理主键
     * @return 照片管理
     */
    public TPicture selectTPictureByUid(String uid);

    /**
     * 查询照片管理列表
     * 
     * @param tPicture 照片管理
     * @return 照片管理集合
     */
    public List<TPicture> selectTPictureList(TPicture tPicture);

    /**
     * 新增照片管理
     * 
     * @param tPicture 照片管理
     * @return 结果
     */
    public int insertTPicture(TPicture tPicture);

    /**
     * 修改照片管理
     * 
     * @param tPicture 照片管理
     * @return 结果
     */
    public int updateTPicture(TPicture tPicture);

    /**
     * 批量删除照片管理
     * 
     * @param uids 需要删除的照片管理主键集合
     * @return 结果
     */
    public int deleteTPictureByUids(String[] uids);

    /**
     * 删除照片管理信息
     * 
     * @param uid 照片管理主键
     * @return 结果
     */
    public int deleteTPictureByUid(String uid);
}
