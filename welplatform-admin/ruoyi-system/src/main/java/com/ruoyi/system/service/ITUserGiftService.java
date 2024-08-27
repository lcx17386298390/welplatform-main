package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.TUserGift;

/**
 * 用户礼品管理Service接口
 * 
 * @author ruoyi
 * @date 2024-03-29
 */
public interface ITUserGiftService 
{
    /**
     * 查询用户礼品管理
     * 
     * @param uid 用户礼品管理主键
     * @return 用户礼品管理
     */
    public TUserGift selectTUserGiftByUid(String uid);

    /**
     * 查询用户礼品管理列表
     * 
     * @param tUserGift 用户礼品管理
     * @return 用户礼品管理集合
     */
    public List<TUserGift> selectTUserGiftList(TUserGift tUserGift);

    /**
     * 新增用户礼品管理
     * 
     * @param tUserGift 用户礼品管理
     * @return 结果
     */
    public int insertTUserGift(TUserGift tUserGift);

    /**
     * 修改用户礼品管理
     * 
     * @param tUserGift 用户礼品管理
     * @return 结果
     */
    public int updateTUserGift(TUserGift tUserGift);

    /**
     * 批量删除用户礼品管理
     * 
     * @param uids 需要删除的用户礼品管理主键集合
     * @return 结果
     */
    public int deleteTUserGiftByUids(String[] uids);

    /**
     * 删除用户礼品管理信息
     * 
     * @param uid 用户礼品管理主键
     * @return 结果
     */
    public int deleteTUserGiftByUid(String uid);
}