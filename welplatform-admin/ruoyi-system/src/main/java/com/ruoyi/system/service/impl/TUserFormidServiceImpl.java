package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TUserFormidMapper;
import com.ruoyi.system.domain.TUserFormid;
import com.ruoyi.system.service.ITUserFormidService;

/**
 * 用户微信数据管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-03-29
 */
@Service
public class TUserFormidServiceImpl implements ITUserFormidService 
{
    @Autowired
    private TUserFormidMapper tUserFormidMapper;

    /**
     * 查询用户微信数据管理
     * 
     * @param id 用户微信数据管理主键
     * @return 用户微信数据管理
     */
    @Override
    public TUserFormid selectTUserFormidById(Long id)
    {
        return tUserFormidMapper.selectTUserFormidById(id);
    }

    /**
     * 查询用户微信数据管理列表
     * 
     * @param tUserFormid 用户微信数据管理
     * @return 用户微信数据管理
     */
    @Override
    public List<TUserFormid> selectTUserFormidList(TUserFormid tUserFormid)
    {
        return tUserFormidMapper.selectTUserFormidList(tUserFormid);
    }

    /**
     * 新增用户微信数据管理
     * 
     * @param tUserFormid 用户微信数据管理
     * @return 结果
     */
    @Override
    public int insertTUserFormid(TUserFormid tUserFormid)
    {
        return tUserFormidMapper.insertTUserFormid(tUserFormid);
    }

    /**
     * 修改用户微信数据管理
     * 
     * @param tUserFormid 用户微信数据管理
     * @return 结果
     */
    @Override
    public int updateTUserFormid(TUserFormid tUserFormid)
    {
        tUserFormid.setUpdateTime(DateUtils.getNowDate());
        return tUserFormidMapper.updateTUserFormid(tUserFormid);
    }

    /**
     * 批量删除用户微信数据管理
     * 
     * @param ids 需要删除的用户微信数据管理主键
     * @return 结果
     */
    @Override
    public int deleteTUserFormidByIds(Long[] ids)
    {
        return tUserFormidMapper.deleteTUserFormidByIds(ids);
    }

    /**
     * 删除用户微信数据管理信息
     * 
     * @param id 用户微信数据管理主键
     * @return 结果
     */
    @Override
    public int deleteTUserFormidById(Long id)
    {
        return tUserFormidMapper.deleteTUserFormidById(id);
    }
}
