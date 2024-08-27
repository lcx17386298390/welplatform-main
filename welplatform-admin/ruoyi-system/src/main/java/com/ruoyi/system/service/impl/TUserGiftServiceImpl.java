package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TUserGiftMapper;
import com.ruoyi.system.domain.TUserGift;
import com.ruoyi.system.service.ITUserGiftService;

/**
 * 用户礼品管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-03-29
 */
@Service
public class TUserGiftServiceImpl implements ITUserGiftService 
{
    @Autowired
    private TUserGiftMapper tUserGiftMapper;

    /**
     * 查询用户礼品管理
     * 
     * @param uid 用户礼品管理主键
     * @return 用户礼品管理
     */
    @Override
    public TUserGift selectTUserGiftByUid(String uid)
    {
        return tUserGiftMapper.selectTUserGiftByUid(uid);
    }

    /**
     * 查询用户礼品管理列表
     * 
     * @param tUserGift 用户礼品管理
     * @return 用户礼品管理
     */
    @Override
    public List<TUserGift> selectTUserGiftList(TUserGift tUserGift)
    {
        return tUserGiftMapper.selectTUserGiftList(tUserGift);
    }

    /**
     * 新增用户礼品管理
     * 
     * @param tUserGift 用户礼品管理
     * @return 结果
     */
    @Override
    public int insertTUserGift(TUserGift tUserGift)
    {
        return tUserGiftMapper.insertTUserGift(tUserGift);
    }

    /**
     * 修改用户礼品管理
     * 
     * @param tUserGift 用户礼品管理
     * @return 结果
     */
    @Override
    public int updateTUserGift(TUserGift tUserGift)
    {
        return tUserGiftMapper.updateTUserGift(tUserGift);
    }

    /**
     * 批量删除用户礼品管理
     * 
     * @param uids 需要删除的用户礼品管理主键
     * @return 结果
     */
    @Override
    public int deleteTUserGiftByUids(String[] uids)
    {
        return tUserGiftMapper.deleteTUserGiftByUids(uids);
    }

    /**
     * 删除用户礼品管理信息
     * 
     * @param uid 用户礼品管理主键
     * @return 结果
     */
    @Override
    public int deleteTUserGiftByUid(String uid)
    {
        return tUserGiftMapper.deleteTUserGiftByUid(uid);
    }
}
