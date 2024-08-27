package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TGiftMapper;
import com.ruoyi.system.domain.TGift;
import com.ruoyi.system.service.ITGiftService;

/**
 * 礼品管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-03-29
 */
@Service
public class TGiftServiceImpl implements ITGiftService 
{
    @Autowired
    private TGiftMapper tGiftMapper;

    /**
     * 查询礼品管理
     * 
     * @param giftId 礼品管理主键
     * @return 礼品管理
     */
    @Override
    public TGift selectTGiftByGiftId(String giftId)
    {
        return tGiftMapper.selectTGiftByGiftId(giftId);
    }

    /**
     * 查询礼品管理列表
     * 
     * @param tGift 礼品管理
     * @return 礼品管理
     */
    @Override
    public List<TGift> selectTGiftList(TGift tGift)
    {
        return tGiftMapper.selectTGiftList(tGift);
    }

    /**
     * 新增礼品管理
     * 
     * @param tGift 礼品管理
     * @return 结果
     */
    @Override
    public int insertTGift(TGift tGift)
    {
        return tGiftMapper.insertTGift(tGift);
    }

    /**
     * 修改礼品管理
     * 
     * @param tGift 礼品管理
     * @return 结果
     */
    @Override
    public int updateTGift(TGift tGift)
    {
        return tGiftMapper.updateTGift(tGift);
    }

    /**
     * 批量删除礼品管理
     * 
     * @param giftIds 需要删除的礼品管理主键
     * @return 结果
     */
    @Override
    public int deleteTGiftByGiftIds(String[] giftIds)
    {
        return tGiftMapper.deleteTGiftByGiftIds(giftIds);
    }

    /**
     * 删除礼品管理信息
     * 
     * @param giftId 礼品管理主键
     * @return 结果
     */
    @Override
    public int deleteTGiftByGiftId(String giftId)
    {
        return tGiftMapper.deleteTGiftByGiftId(giftId);
    }
}
