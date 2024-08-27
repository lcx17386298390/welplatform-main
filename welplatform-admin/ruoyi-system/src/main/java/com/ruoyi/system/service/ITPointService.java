package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.TPoint;

/**
 * 积分管理Service接口
 * 
 * @author ruoyi
 * @date 2024-03-29
 */
public interface ITPointService 
{
    /**
     * 查询积分管理
     * 
     * @param uid 积分管理主键
     * @return 积分管理
     */
    public TPoint selectTPointByUid(String uid);

    /**
     * 查询积分管理列表
     * 
     * @param tPoint 积分管理
     * @return 积分管理集合
     */
    public List<TPoint> selectTPointList(TPoint tPoint);

    /**
     * 新增积分管理
     * 
     * @param tPoint 积分管理
     * @return 结果
     */
    public int insertTPoint(TPoint tPoint);

    /**
     * 修改积分管理
     * 
     * @param tPoint 积分管理
     * @return 结果
     */
    public int updateTPoint(TPoint tPoint);

    /**
     * 批量删除积分管理
     * 
     * @param uids 需要删除的积分管理主键集合
     * @return 结果
     */
    public int deleteTPointByUids(String[] uids);

    /**
     * 删除积分管理信息
     * 
     * @param uid 积分管理主键
     * @return 结果
     */
    public int deleteTPointByUid(String uid);
}