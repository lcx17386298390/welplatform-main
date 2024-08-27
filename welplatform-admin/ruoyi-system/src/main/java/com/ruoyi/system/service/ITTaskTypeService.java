package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.TTaskType;

/**
 * 任务种类管理Service接口
 * 
 * @author ruoyi
 * @date 2024-03-29
 */
public interface ITTaskTypeService 
{
    /**
     * 查询任务种类管理
     * 
     * @param uid 任务种类管理主键
     * @return 任务种类管理
     */
    public TTaskType selectTTaskTypeByUid(String uid);

    /**
     * 查询任务种类管理列表
     * 
     * @param tTaskType 任务种类管理
     * @return 任务种类管理集合
     */
    public List<TTaskType> selectTTaskTypeList(TTaskType tTaskType);

    /**
     * 新增任务种类管理
     * 
     * @param tTaskType 任务种类管理
     * @return 结果
     */
    public int insertTTaskType(TTaskType tTaskType);

    /**
     * 修改任务种类管理
     * 
     * @param tTaskType 任务种类管理
     * @return 结果
     */
    public int updateTTaskType(TTaskType tTaskType);

    /**
     * 批量删除任务种类管理
     * 
     * @param uids 需要删除的任务种类管理主键集合
     * @return 结果
     */
    public int deleteTTaskTypeByUids(String[] uids);

    /**
     * 删除任务种类管理信息
     * 
     * @param uid 任务种类管理主键
     * @return 结果
     */
    public int deleteTTaskTypeByUid(String uid);
}
