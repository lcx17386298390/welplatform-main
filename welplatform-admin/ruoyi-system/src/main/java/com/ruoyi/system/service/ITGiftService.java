package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.TGift;

/**
 * 礼品管理Service接口
 * 
 * @author ruoyi
 * @date 2024-03-29
 */
public interface ITGiftService 
{
    /**
     * 查询礼品管理
     * 
     * @param giftId 礼品管理主键
     * @return 礼品管理
     */
    public TGift selectTGiftByGiftId(String giftId);

    /**
     * 查询礼品管理列表
     * 
     * @param tGift 礼品管理
     * @return 礼品管理集合
     */
    public List<TGift> selectTGiftList(TGift tGift);

    /**
     * 新增礼品管理
     * 
     * @param tGift 礼品管理
     * @return 结果
     */
    public int insertTGift(TGift tGift);

    /**
     * 修改礼品管理
     * 
     * @param tGift 礼品管理
     * @return 结果
     */
    public int updateTGift(TGift tGift);

    /**
     * 批量删除礼品管理
     * 
     * @param giftIds 需要删除的礼品管理主键集合
     * @return 结果
     */
    public int deleteTGiftByGiftIds(String[] giftIds);

    /**
     * 删除礼品管理信息
     * 
     * @param giftId 礼品管理主键
     * @return 结果
     */
    public int deleteTGiftByGiftId(String giftId);
}
