package com.hgnuacm.wx.service.impl;

import java.util.List;
import com.hgnuacm.common.utils.DateUtils;
import com.hgnuacm.wx.domain.Point;
import com.hgnuacm.wx.mapper.PointMapper;
import com.hgnuacm.wx.service.IPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 积分Service业务层处理
 * 
 * @author violet
 * @date 2024-01-20
 */
@Service
public class PointServiceImpl implements IPointService
{
    @Autowired
    private PointMapper pointMapper;

    /**
     * 查询积分
     * 
     * @param uid 积分主键
     * @return 积分
     */
    @Override
    public Point selectPointByUid(String uid)
    {
        return pointMapper.selectPointByUid(uid);
    }

    /**
     * 查询积分列表
     * 
     * @param point 积分
     * @return 积分
     */
    @Override
    public List<Point> selectPointList(Point point)
    {
        return pointMapper.selectPointList(point);
    }

    /**
     * 新增积分
     * 
     * @param point 积分
     * @return 结果
     */
    @Override
    public int insertPoint(Point point)
    {
        return pointMapper.insertPoint(point);
    }

    /**
     * 修改积分
     * 
     * @param point 积分
     * @return 结果
     */
    @Override
    public int updatePoint(Point point)
    {
        point.setUpdateTime(DateUtils.getNowDate());
        return pointMapper.updatePoint(point);
    }

    /**
     * 批量删除积分
     * 
     * @param uids 需要删除的积分主键
     * @return 结果
     */
    @Override
    public int deletePointByUids(String[] uids)
    {
        return pointMapper.deletePointByUids(uids);
    }

    /**
     * 删除积分信息
     * 
     * @param uid 积分主键
     * @return 结果
     */
    @Override
    public int deletePointByUid(String uid)
    {
        return pointMapper.deletePointByUid(uid);
    }

    @Override
    public List<Point> selectAllPoint() {
        return pointMapper.selectAllPoint();
    }

    @Override
    public Point selectPointByUserId(String userId) {
        return pointMapper.selectPointByUserId(userId);
    }
}
