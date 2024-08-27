package com.hgnuacm.wx.service;


import com.hgnuacm.wx.domain.TGift;

import java.util.List;

/**
 * 礼品Service接口
 * 
 * @author Rs
 * @date 2024-03-28
 */
public interface ITGiftService 
{
    /**
     * 查询礼品
     * 
     * @param giftId 礼品主键
     * @return 礼品
     */
    public TGift selectTGiftByGiftId(String giftId);

    /**
     * 查询礼品列表
     * 
     * @param tGift 礼品
     * @return 礼品集合
     */
    public List<TGift> selectTGiftList(TGift tGift);

    /**
     * 新增礼品
     * 
     * @param tGift 礼品
     * @return 结果
     */
    public int insertTGift(TGift tGift);

    /**
     * 修改礼品
     * 
     * @param tGift 礼品
     * @return 结果
     */
    public int updateTGift(TGift tGift);

    /**
     * 批量删除礼品
     * 
     * @param giftIds 需要删除的礼品主键集合
     * @return 结果
     */
    public int deleteTGiftByGiftIds(String[] giftIds);

    /**
     * 删除礼品信息
     * 
     * @param giftId 礼品主键
     * @return 结果
     */
    public int deleteTGiftByGiftId(String giftId);
}
