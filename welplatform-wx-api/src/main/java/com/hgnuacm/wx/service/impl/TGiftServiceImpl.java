package com.hgnuacm.wx.service.impl;

import java.util.List;

import com.hgnuacm.wx.domain.TGift;
import com.hgnuacm.wx.mapper.TGiftMapper;
import com.hgnuacm.wx.service.ITGiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 礼品Service业务层处理
 * 
 * @author Rs
 * @date 2024-03-28
 */
@Service
public class TGiftServiceImpl implements ITGiftService
{
    @Autowired
    private TGiftMapper tGiftMapper;

    /**
     * 查询礼品
     * 
     * @param giftId 礼品主键
     * @return 礼品
     */
    @Override
    public TGift selectTGiftByGiftId(String giftId)
    {
        return tGiftMapper.selectTGiftByGiftId(giftId);
    }

    /**
     * 查询礼品列表
     * 
     * @param tGift 礼品
     * @return 礼品
     */
    @Override
    public List<TGift> selectTGiftList(TGift tGift)
    {
        return tGiftMapper.selectTGiftList(tGift);
    }

    /**
     * 新增礼品
     * 
     * @param tGift 礼品
     * @return 结果
     */
    @Override
    public int insertTGift(TGift tGift)
    {
        return tGiftMapper.insertTGift(tGift);
    }

    /**
     * 修改礼品
     * 
     * @param tGift 礼品
     * @return 结果
     */
    @Override
    public int updateTGift(TGift tGift)
    {
        return tGiftMapper.updateTGift(tGift);
    }

    /**
     * 批量删除礼品
     * 
     * @param giftIds 需要删除的礼品主键
     * @return 结果
     */
    @Override
    public int deleteTGiftByGiftIds(String[] giftIds)
    {
        return tGiftMapper.deleteTGiftByGiftIds(giftIds);
    }

    /**
     * 删除礼品信息
     * 
     * @param giftId 礼品主键
     * @return 结果
     */
    @Override
    public int deleteTGiftByGiftId(String giftId)
    {
        return tGiftMapper.deleteTGiftByGiftId(giftId);
    }


}
