package com.hgnuacm.wx.service.impl;

import java.util.List;

import com.hgnuacm.wx.domain.Advertise;
import com.hgnuacm.wx.mapper.AdvertiseMapper;
import com.hgnuacm.wx.service.IAdvertiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 广告Service业务层处理
 * 
 * @author violet
 * @date 2024-01-20
 */
@Service
public class AdvertiseServiceImpl implements IAdvertiseService
{
    @Autowired
    private AdvertiseMapper advertiseMapper;

    /**
     * 查询广告
     * 
     * @param uid 广告主键
     * @return 广告
     */
    @Override
    public Advertise selectAdvertiseByUid(String uid)
    {
        return advertiseMapper.selectAdvertiseByUid(uid);
    }

    /**
     * 查询广告列表
     * 
     * @param advertise 广告
     * @return 广告
     */
    @Override
    public List<Advertise> selectAdvertiseList(Advertise advertise)
    {
        return advertiseMapper.selectAdvertiseList(advertise);
    }

    /**
     * 新增广告
     * 
     * @param advertise 广告
     * @return 结果
     */
    @Override
    public int insertAdvertise(Advertise advertise)
    {
        return advertiseMapper.insertAdvertise(advertise);
    }

    /**
     * 修改广告
     * 
     * @param advertise 广告
     * @return 结果
     */
    @Override
    public int updateAdvertise(Advertise advertise)
    {
        return advertiseMapper.updateAdvertise(advertise);
    }

    /**
     * 批量删除广告
     * 
     * @param uids 需要删除的广告主键
     * @return 结果
     */
    @Override
    public int deleteAdvertiseByUids(String[] uids)
    {
        return advertiseMapper.deleteAdvertiseByUids(uids);
    }

    /**
     * 删除广告信息
     * 
     * @param uid 广告主键
     * @return 结果
     */
    @Override
    public int deleteAdvertiseByUid(String uid)
    {
        return advertiseMapper.deleteAdvertiseByUid(uid);
    }
}
