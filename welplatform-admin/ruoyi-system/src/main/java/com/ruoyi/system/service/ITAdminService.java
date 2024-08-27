package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.TAdmin;

/**
 * admin管理Service接口
 * 
 * @author ruoyi
 * @date 2024-03-29
 */
public interface ITAdminService 
{
    /**
     * 查询admin管理
     * 
     * @param uid admin管理主键
     * @return admin管理
     */
    public TAdmin selectTAdminByUid(String uid);

    /**
     * 查询admin管理列表
     * 
     * @param tAdmin admin管理
     * @return admin管理集合
     */
    public List<TAdmin> selectTAdminList(TAdmin tAdmin);

    /**
     * 新增admin管理
     * 
     * @param tAdmin admin管理
     * @return 结果
     */
    public int insertTAdmin(TAdmin tAdmin);

    /**
     * 修改admin管理
     * 
     * @param tAdmin admin管理
     * @return 结果
     */
    public int updateTAdmin(TAdmin tAdmin);

    /**
     * 批量删除admin管理
     * 
     * @param uids 需要删除的admin管理主键集合
     * @return 结果
     */
    public int deleteTAdminByUids(String[] uids);

    /**
     * 删除admin管理信息
     * 
     * @param uid admin管理主键
     * @return 结果
     */
    public int deleteTAdminByUid(String uid);
}
