package com.hgnuacm.wx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hgnuacm.wx.mapper.UserMapper;
import com.hgnuacm.wx.domain.User;
import com.hgnuacm.wx.service.UserService;

/**
 * 用户Service业务层处理
 * 
 * @author violet
 * @date 2024-01-20
 */
@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectUserByOpenId(String openId) {
        return userMapper.selectUserByOpenId(openId);
    }

    /**
     * 查询用户
     * 
     * @param uid 用户主键
     * @return 用户
     */
    @Override
    public User selectUserByUid(String uid)
    {
        return userMapper.selectUserByUid(uid);
    }

    /**
     * 查询用户列表
     * 
     * @param user 用户
     * @return 用户
     */
    @Override
    public List<User> selectUserList(User user)
    {
        return userMapper.selectUserList(user);
    }

    /**
     * 新增用户
     * 
     * @param user 用户
     * @return 结果
     */
    @Override
    public int insertUser(User user)
    {
        return userMapper.insertUser(user);
    }

    /**
     * 修改用户
     * 
     * @param user 用户
     * @return 结果
     */
    @Override
    public int updateUser(User user)
    {
        return userMapper.updateUser(user);
    }

    /**
     * 批量删除用户
     * 
     * @param uids 需要删除的用户主键
     * @return 结果
     */
    @Override
    public int deleteUserByUids(String[] uids)
    {
        return userMapper.deleteUserByUids(uids);
    }

    /**
     * 删除用户信息
     * 
     * @param uid 用户主键
     * @return 结果
     */
    @Override
    public int deleteUserByUid(String uid)
    {
        return userMapper.deleteUserByUid(uid);
    }

    @Override
    public List<User> queryByUsername(String username) {
        return userMapper.queryByUsername(username);
    }

    @Override
    public List<User> queryByEmail(String email) {
        return userMapper.queryByEmail(email);
    }

    @Override
    public List<User> queryByMobile(String mobile) {
        return userMapper.queryByMobile(mobile);
    }

    @Override
    public List<User> queryByOpenid(String openId) {
        return userMapper.queryByOpenid(openId);
    }

    @Override
    public List<User> queryAllAdvancedUsers() {
        return userMapper.queryAllAdvancedUsers();
    }
}
