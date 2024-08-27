package com.hgnuacm.wx.mapper;


import com.hgnuacm.wx.domain.TUserGift;

import java.util.List;

/**
 * 用户购买礼品Mapper接口
 *
 * @author Rs
 * @date 2024-04-02
 */
public interface TUserGiftMapper
{
    /**
     * 查询用户购买礼品
     *
     * @param uid 用户购买礼品主键
     * @return 用户购买礼品
     */
    public TUserGift selectTUserGiftByUid(Long uid);

    /**
     * 查询用户购买礼品列表
     *
     * @param tUserGift 用户购买礼品
     * @return 用户购买礼品集合
     */
    public List<TUserGift> selectTUserGiftList(TUserGift tUserGift);

    /**
     * 新增用户购买礼品
     *
     * @param tUserGift 用户购买礼品
     * @return 结果
     */
    public int insertTUserGift(TUserGift tUserGift);

    /**
     * 修改用户购买礼品
     *
     * @param tUserGift 用户购买礼品
     * @return 结果
     */
    public int updateTUserGift(TUserGift tUserGift);

    /**
     * 删除用户购买礼品
     *
     * @param uid 用户购买礼品主键
     * @return 结果
     */
    public int deleteTUserGiftByUid(Long uid);

    /**
     * 批量删除用户购买礼品
     *
     * @param uids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTUserGiftByUids(Long[] uids);
}
