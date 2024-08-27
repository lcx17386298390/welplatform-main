package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.TPicture;

/**
 * 照片管理Mapper接口
 * 
 * @author ruoyi
 * @date 2024-03-29
 */
public interface TPictureMapper 
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
     * 删除照片管理
     * 
     * @param uid 照片管理主键
     * @return 结果
     */
    public int deleteTPictureByUid(String uid);

    /**
     * 批量删除照片管理
     * 
     * @param uids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTPictureByUids(String[] uids);
}
