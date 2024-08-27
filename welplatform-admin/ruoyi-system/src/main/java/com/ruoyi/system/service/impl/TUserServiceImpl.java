package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TUserMapper;
import com.ruoyi.system.domain.TUser;
import com.ruoyi.system.service.ITUserService;

/**
 * 用户管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-03-29
 */
@Service
public class TUserServiceImpl implements ITUserService 
{
    @Autowired
    private TUserMapper tUserMapper;

    /**
     * 查询用户管理
     * 
     * @param uid 用户管理主键
     * @return 用户管理
     */
    @Override
    public TUser selectTUserByUid(String uid)
    {
        return tUserMapper.selectTUserByUid(uid);
    }

    /**
     * 查询用户管理列表
     * 
     * @param tUser 用户管理
     * @return 用户管理
     */
    @Override
    public List<TUser> selectTUserList(TUser tUser)
    {
        return tUserMapper.selectTUserList(tUser);
    }

    /**
     * 新增用户管理
     * 
     * @param tUser 用户管理
     * @return 结果
     */
    @Override
    public int insertTUser(TUser tUser)
    {
        tUser.setCreateTime(DateUtils.getNowDate());
        return tUserMapper.insertTUser(tUser);
    }

    /**
     * 修改用户管理
     * 
     * @param tUser 用户管理
     * @return 结果
     */
    @Override
    public int updateTUser(TUser tUser)
    {
        return tUserMapper.updateTUser(tUser);
    }

    /**
     * 批量删除用户管理
     * 
     * @param uids 需要删除的用户管理主键
     * @return 结果
     */
    @Override
    public int deleteTUserByUids(String[] uids)
    {
        return tUserMapper.deleteTUserByUids(uids);
    }

    /**
     * 删除用户管理信息
     * 
     * @param uid 用户管理主键
     * @return 结果
     */
    @Override
    public int deleteTUserByUid(String uid)
    {
        return tUserMapper.deleteTUserByUid(uid);
    }

    @Override
    public List<TUser> getUserList() {
        return tUserMapper.getUserList();
    }

    @Override
    public int getUserCount() {
        return tUserMapper.getUserCount();
    }
}
