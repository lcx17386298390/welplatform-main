package com.hgnuacm.wx.mapper;

import com.hgnuacm.wx.domain.Advertise;

import java.util.List;

/**
 * 广告Mapper接口
 * 
 * @author violet
 * @date 2024-01-20
 */
public interface AdvertiseMapper 
{
    /**
     * 查询广告
     * 
     * @param uid 广告主键
     * @return 广告
     */
    public Advertise selectAdvertiseByUid(String uid);

    /**
     * 查询广告列表
     * 
     * @param advertise 广告
     * @return 广告集合
     */
    public List<Advertise> selectAdvertiseList(Advertise advertise);

    /**
     * 新增广告
     * 
     * @param advertise 广告
     * @return 结果
     */
    public int insertAdvertise(Advertise advertise);

    /**
     * 修改广告
     * 
     * @param advertise 广告
     * @return 结果
     */
    public int updateAdvertise(Advertise advertise);

    /**
     * 删除广告
     * 
     * @param uid 广告主键
     * @return 结果
     */
    public int deleteAdvertiseByUid(String uid);

    /**
     * 批量删除广告
     * 
     * @param uids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAdvertiseByUids(String[] uids);
}
