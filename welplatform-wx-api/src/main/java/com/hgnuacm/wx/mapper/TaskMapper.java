package com.hgnuacm.wx.mapper;

import com.hgnuacm.wx.domain.Task;

import java.util.List;

/**
 * 任务Mapper接口
 * 
 * @author violet
 * @date 2024-01-20
 */
public interface TaskMapper 
{
    /**
     * 查询任务
     * 
     * @param uid 任务主键
     * @return 任务
     */
    public Task selectTaskByUid(String uid);

    /**
     * 查询任务列表
     * 
     * @param task 任务
     * @return 任务集合
     */
    public List<Task> selectTaskList(Task task);

    /**
     * 新增任务
     * 
     * @param task 任务
     * @return 结果
     */
    public int insertTask(Task task);

    /**
     * 修改任务
     * 
     * @param task 任务
     * @return 结果
     */
    public int updateTask(Task task);

    /**
     * 删除任务
     * 
     * @param uid 任务主键
     * @return 结果
     */
    public int deleteTaskByUid(String uid);

    /**
     * 批量删除任务
     * 
     * @param uids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTaskByUids(String[] uids);
}
