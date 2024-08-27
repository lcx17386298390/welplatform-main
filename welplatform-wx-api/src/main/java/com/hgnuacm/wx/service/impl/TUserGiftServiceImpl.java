package com.hgnuacm.wx.service.impl;
import java.util.List;

import com.hgnuacm.wx.domain.TUserGift;
import com.hgnuacm.wx.mapper.TUserGiftMapper;
import com.hgnuacm.wx.service.ITUserGiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户购买礼品Service业务层处理
 *
 * @author Rs
 * @date 2024-04-02
 */
@Service
public class TUserGiftServiceImpl implements ITUserGiftService
{
    @Autowired
    private TUserGiftMapper tUserGiftMapper;

    /**
     * 查询用户购买礼品
     *
     * @param uid 用户购买礼品主键
     * @return 用户购买礼品
     */
    @Override
    public TUserGift selectTUserGiftByUid(Long uid)
    {
        return tUserGiftMapper.selectTUserGiftByUid(uid);
    }

    /**
     * 查询用户购买礼品列表
     *
     * @param tUserGift 用户购买礼品
     * @return 用户购买礼品
     */
    @Override
    public List<TUserGift> selectTUserGiftList(TUserGift tUserGift)
    {
        return tUserGiftMapper.selectTUserGiftList(tUserGift);
    }

    /**
     * 新增用户购买礼品
     *
     * @param tUserGift 用户购买礼品
     * @return 结果
     */
    @Override
    public int insertTUserGift(TUserGift tUserGift)
    {
        return tUserGiftMapper.insertTUserGift(tUserGift);
    }

    /**
     * 修改用户购买礼品
     *
     * @param tUserGift 用户购买礼品
     * @return 结果
     */
    @Override
    public int updateTUserGift(TUserGift tUserGift)
    {
        return tUserGiftMapper.updateTUserGift(tUserGift);
    }

    /**
     * 批量删除用户购买礼品
     *
     * @param uids 需要删除的用户购买礼品主键
     * @return 结果
     */
    @Override
    public int deleteTUserGiftByUids(Long[] uids)
    {
        return tUserGiftMapper.deleteTUserGiftByUids(uids);
    }

    /**
     * 删除用户购买礼品信息
     *
     * @param uid 用户购买礼品主键
     * @return 结果
     */
    @Override
    public int deleteTUserGiftByUid(Long uid)
    {
        return tUserGiftMapper.deleteTUserGiftByUid(uid);
    }
}
