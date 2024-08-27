package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.TUser;

/**
 * 用户管理Service接口
 * 
 * @author ruoyi
 * @date 2024-03-29
 */
public interface ITUserService 
{
    /**
     * 查询用户管理
     * 
     * @param uid 用户管理主键
     * @return 用户管理
     */
    public TUser selectTUserByUid(String uid);

    /**
     * 查询用户管理列表
     * 
     * @param tUser 用户管理
     * @return 用户管理集合
     */
    public List<TUser> selectTUserList(TUser tUser);

    /**
     * 新增用户管理
     * 
     * @param tUser 用户管理
     * @return 结果
     */
    public int insertTUser(TUser tUser);

    /**
     * 修改用户管理
     * 
     * @param tUser 用户管理
     * @return 结果
     */
    public int updateTUser(TUser tUser);

    /**
     * 批量删除用户管理
     * 
     * @param uids 需要删除的用户管理主键集合
     * @return 结果
     */
    public int deleteTUserByUids(String[] uids);

    /**
     * 删除用户管理信息
     * 
     * @param uid 用户管理主键
     * @return 结果
     */
    public int deleteTUserByUid(String uid);

    public List<TUser> getUserList();

    public int getUserCount();
}
