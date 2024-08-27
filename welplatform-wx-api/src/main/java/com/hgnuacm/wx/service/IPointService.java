package com.hgnuacm.wx.service;

import com.hgnuacm.wx.domain.Point;

import java.util.List;

/**
 * 积分Service接口
 * 
 * @author violet
 * @date 2024-01-20
 */
public interface IPointService 
{
    /**
     * 查询积分
     * 
     * @param uid 积分主键
     * @return 积分
     */
    public Point selectPointByUid(String uid);

    /**
     * 查询积分列表
     * 
     * @param point 积分
     * @return 积分集合
     */
    public List<Point> selectPointList(Point point);

    /**
     * 新增积分
     * 
     * @param point 积分
     * @return 结果
     */
    public int insertPoint(Point point);

    /**
     * 修改积分
     * 
     * @param point 积分
     * @return 结果
     */
    public int updatePoint(Point point);

    /**
     * 批量删除积分
     * 
     * @param uids 需要删除的积分主键集合
     * @return 结果
     */
    public int deletePointByUids(String[] uids);

    /**
     * 删除积分信息
     * 
     * @param uid 积分主键
     * @return 结果
     */
    public int deletePointByUid(String uid);

    public List<Point> selectAllPoint();

    public Point selectPointByUserId(String userId);
}
