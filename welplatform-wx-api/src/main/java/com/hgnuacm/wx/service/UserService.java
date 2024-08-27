package com.hgnuacm.wx.service;

import java.util.List;
import com.hgnuacm.wx.domain.User;

/**
 * 用户Service接口
 * 
 * @author violet
 * @date 2024-01-20
 */
public interface UserService
{
    /**
     * 查询用户
     * 
     * @param uid 用户主键
     * @return 用户
     */
    public User selectUserByUid(String uid);

    /**
     * 查询用户列表
     * 
     * @param user 用户
     * @return 用户集合
     */
    public List<User> selectUserList(User user);

    /**
     * 新增用户
     * 
     * @param user 用户
     * @return 结果
     */
    public int insertUser(User user);

    /**
     * 修改用户
     * 
     * @param user 用户
     * @return 结果
     */
    public int updateUser(User user);

    /**
     * 批量删除用户
     * 
     * @param uids 需要删除的用户主键集合
     * @return 结果
     */
    public int deleteUserByUids(String[] uids);

    /**
     * 删除用户信息
     * 
     * @param uid 用户主键
     * @return 结果
     */
    public int deleteUserByUid(String uid);

    public User selectUserByOpenId(String openId);

    public List<User> queryByUsername(String username);

    public List<User> queryByMobile(String mobile);

    public List<User> queryByOpenid(String openId);

    List<User> queryByEmail(String email);

    List<User> queryAllAdvancedUsers();


}
