package com.hgnuacm.wx.mapper;


import com.hgnuacm.wx.domain.TGift;

import java.util.List;

/**
 * 礼品Mapper接口
 * 
 * @author Rs
 * @date 2024-03-28
 */
public interface TGiftMapper 
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
     * 删除礼品
     * 
     * @param giftId 礼品主键
     * @return 结果
     */
    public int deleteTGiftByGiftId(String giftId);

    /**
     * 批量删除礼品
     * 
     * @param giftIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTGiftByGiftIds(String[] giftIds);
}
