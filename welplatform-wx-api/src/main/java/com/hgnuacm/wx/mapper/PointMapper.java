package com.hgnuacm.wx.mapper;

import com.hgnuacm.wx.domain.Point;

import java.util.List;

/**
 * 积分Mapper接口
 * 
 * @author violet
 * @date 2024-01-20
 */
public interface PointMapper 
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
     * 删除积分
     * 
     * @param uid 积分主键
     * @return 结果
     */
    public int deletePointByUid(String uid);

    /**
     * 批量删除积分
     * 
     * @param uids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePointByUids(String[] uids);

    public List<Point> selectAllPoint();

    Point selectPointByUserId(String userId);
}
