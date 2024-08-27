package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.TUserFormid;

/**
 * 用户微信数据管理Service接口
 * 
 * @author ruoyi
 * @date 2024-03-29
 */
public interface ITUserFormidService 
{
    /**
     * 查询用户微信数据管理
     * 
     * @param id 用户微信数据管理主键
     * @return 用户微信数据管理
     */
    public TUserFormid selectTUserFormidById(Long id);

    /**
     * 查询用户微信数据管理列表
     * 
     * @param tUserFormid 用户微信数据管理
     * @return 用户微信数据管理集合
     */
    public List<TUserFormid> selectTUserFormidList(TUserFormid tUserFormid);

    /**
     * 新增用户微信数据管理
     * 
     * @param tUserFormid 用户微信数据管理
     * @return 结果
     */
    public int insertTUserFormid(TUserFormid tUserFormid);

    /**
     * 修改用户微信数据管理
     * 
     * @param tUserFormid 用户微信数据管理
     * @return 结果
     */
    public int updateTUserFormid(TUserFormid tUserFormid);

    /**
     * 批量删除用户微信数据管理
     * 
     * @param ids 需要删除的用户微信数据管理主键集合
     * @return 结果
     */
    public int deleteTUserFormidByIds(Long[] ids);

    /**
     * 删除用户微信数据管理信息
     * 
     * @param id 用户微信数据管理主键
     * @return 结果
     */
    public int deleteTUserFormidById(Long id);
}
