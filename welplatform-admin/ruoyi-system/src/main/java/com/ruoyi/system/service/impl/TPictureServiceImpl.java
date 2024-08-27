package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TPictureMapper;
import com.ruoyi.system.domain.TPicture;
import com.ruoyi.system.service.ITPictureService;

/**
 * 照片管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-03-29
 */
@Service
public class TPictureServiceImpl implements ITPictureService 
{
    @Autowired
    private TPictureMapper tPictureMapper;

    /**
     * 查询照片管理
     * 
     * @param uid 照片管理主键
     * @return 照片管理
     */
    @Override
    public TPicture selectTPictureByUid(String uid)
    {
        return tPictureMapper.selectTPictureByUid(uid);
    }

    /**
     * 查询照片管理列表
     * 
     * @param tPicture 照片管理
     * @return 照片管理
     */
    @Override
    public List<TPicture> selectTPictureList(TPicture tPicture)
    {
        return tPictureMapper.selectTPictureList(tPicture);
    }

    /**
     * 新增照片管理
     * 
     * @param tPicture 照片管理
     * @return 结果
     */
    @Override
    public int insertTPicture(TPicture tPicture)
    {
        tPicture.setCreateTime(DateUtils.getNowDate());
        return tPictureMapper.insertTPicture(tPicture);
    }

    /**
     * 修改照片管理
     * 
     * @param tPicture 照片管理
     * @return 结果
     */
    @Override
    public int updateTPicture(TPicture tPicture)
    {
        tPicture.setUpdateTime(DateUtils.getNowDate());
        return tPictureMapper.updateTPicture(tPicture);
    }

    /**
     * 批量删除照片管理
     * 
     * @param uids 需要删除的照片管理主键
     * @return 结果
     */
    @Override
    public int deleteTPictureByUids(String[] uids)
    {
        return tPictureMapper.deleteTPictureByUids(uids);
    }

    /**
     * 删除照片管理信息
     * 
     * @param uid 照片管理主键
     * @return 结果
     */
    @Override
    public int deleteTPictureByUid(String uid)
    {
        return tPictureMapper.deleteTPictureByUid(uid);
    }
}
