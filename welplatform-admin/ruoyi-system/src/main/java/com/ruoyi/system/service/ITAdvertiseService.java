package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.TAdvertise;

/**
 * 广告管理Service接口
 * 
 * @author ruoyi
 * @date 2024-03-29
 */
public interface ITAdvertiseService 
{
    /**
     * 查询广告管理
     * 
     * @param uid 广告管理主键
     * @return 广告管理
     */
    public TAdvertise selectTAdvertiseByUid(String uid);

    /**
     * 查询广告管理列表
     * 
     * @param tAdvertise 广告管理
     * @return 广告管理集合
     */
    public List<TAdvertise> selectTAdvertiseList(TAdvertise tAdvertise);

    /**
     * 新增广告管理
     * 
     * @param tAdvertise 广告管理
     * @return 结果
     */
    public int insertTAdvertise(TAdvertise tAdvertise);

    /**
     * 修改广告管理
     * 
     * @param tAdvertise 广告管理
     * @return 结果
     */
    public int updateTAdvertise(TAdvertise tAdvertise);

    /**
     * 批量删除广告管理
     * 
     * @param uids 需要删除的广告管理主键集合
     * @return 结果
     */
    public int deleteTAdvertiseByUids(String[] uids);

    /**
     * 删除广告管理信息
     * 
     * @param uid 广告管理主键
     * @return 结果
     */
    public int deleteTAdvertiseByUid(String uid);
}
