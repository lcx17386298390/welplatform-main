package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TPointMapper;
import com.ruoyi.system.domain.TPoint;
import com.ruoyi.system.service.ITPointService;

/**
 * 积分管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-03-29
 */
@Service
public class TPointServiceImpl implements ITPointService 
{
    @Autowired
    private TPointMapper tPointMapper;

    /**
     * 查询积分管理
     * 
     * @param uid 积分管理主键
     * @return 积分管理
     */
    @Override
    public TPoint selectTPointByUid(String uid)
    {
        return tPointMapper.selectTPointByUid(uid);
    }

    /**
     * 查询积分管理列表
     * 
     * @param tPoint 积分管理
     * @return 积分管理
     */
    @Override
    public List<TPoint> selectTPointList(TPoint tPoint)
    {
        return tPointMapper.selectTPointList(tPoint);
    }

    /**
     * 新增积分管理
     * 
     * @param tPoint 积分管理
     * @return 结果
     */
    @Override
    public int insertTPoint(TPoint tPoint)
    {
        return tPointMapper.insertTPoint(tPoint);
    }

    /**
     * 修改积分管理
     * 
     * @param tPoint 积分管理
     * @return 结果
     */
    @Override
    public int updateTPoint(TPoint tPoint)
    {
        tPoint.setUpdateTime(DateUtils.getNowDate());
        return tPointMapper.updateTPoint(tPoint);
    }

    /**
     * 批量删除积分管理
     * 
     * @param uids 需要删除的积分管理主键
     * @return 结果
     */
    @Override
    public int deleteTPointByUids(String[] uids)
    {
        return tPointMapper.deleteTPointByUids(uids);
    }

    /**
     * 删除积分管理信息
     * 
     * @param uid 积分管理主键
     * @return 结果
     */
    @Override
    public int deleteTPointByUid(String uid)
    {
        return tPointMapper.deleteTPointByUid(uid);
    }
}
