package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TAdvertiseMapper;
import com.ruoyi.system.domain.TAdvertise;
import com.ruoyi.system.service.ITAdvertiseService;

/**
 * 广告管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-03-29
 */
@Service
public class TAdvertiseServiceImpl implements ITAdvertiseService 
{
    @Autowired
    private TAdvertiseMapper tAdvertiseMapper;

    /**
     * 查询广告管理
     * 
     * @param uid 广告管理主键
     * @return 广告管理
     */
    @Override
    public TAdvertise selectTAdvertiseByUid(String uid)
    {
        return tAdvertiseMapper.selectTAdvertiseByUid(uid);
    }

    /**
     * 查询广告管理列表
     * 
     * @param tAdvertise 广告管理
     * @return 广告管理
     */
    @Override
    public List<TAdvertise> selectTAdvertiseList(TAdvertise tAdvertise)
    {
        return tAdvertiseMapper.selectTAdvertiseList(tAdvertise);
    }

    /**
     * 新增广告管理
     * 
     * @param tAdvertise 广告管理
     * @return 结果
     */
    @Override
    public int insertTAdvertise(TAdvertise tAdvertise)
    {
        return tAdvertiseMapper.insertTAdvertise(tAdvertise);
    }

    /**
     * 修改广告管理
     * 
     * @param tAdvertise 广告管理
     * @return 结果
     */
    @Override
    public int updateTAdvertise(TAdvertise tAdvertise)
    {
        return tAdvertiseMapper.updateTAdvertise(tAdvertise);
    }

    /**
     * 批量删除广告管理
     * 
     * @param uids 需要删除的广告管理主键
     * @return 结果
     */
    @Override
    public int deleteTAdvertiseByUids(String[] uids)
    {
        return tAdvertiseMapper.deleteTAdvertiseByUids(uids);
    }

    /**
     * 删除广告管理信息
     * 
     * @param uid 广告管理主键
     * @return 结果
     */
    @Override
    public int deleteTAdvertiseByUid(String uid)
    {
        return tAdvertiseMapper.deleteTAdvertiseByUid(uid);
    }
}
