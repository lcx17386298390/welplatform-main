package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.TTask;

/**
 * 任务管理Service接口
 * 
 * @author ruoyi
 * @date 2024-03-29
 */
public interface ITTaskService 
{
    /**
     * 查询任务管理
     * 
     * @param uid 任务管理主键
     * @return 任务管理
     */
    public TTask selectTTaskByUid(String uid);

    /**
     * 查询任务管理列表
     * 
     * @param tTask 任务管理
     * @return 任务管理集合
     */
    public List<TTask> selectTTaskList(TTask tTask);

    /**
     * 新增任务管理
     * 
     * @param tTask 任务管理
     * @return 结果
     */
    public int insertTTask(TTask tTask);

    /**
     * 修改任务管理
     * 
     * @param tTask 任务管理
     * @return 结果
     */
    public int updateTTask(TTask tTask);

    /**
     * 批量删除任务管理
     * 
     * @param uids 需要删除的任务管理主键集合
     * @return 结果
     */
    public int deleteTTaskByUids(String[] uids);

    /**
     * 删除任务管理信息
     * 
     * @param uid 任务管理主键
     * @return 结果
     */
    public int deleteTTaskByUid(String uid);

    public List<TTask> selectTaskList();
}
