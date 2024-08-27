package com.hgnuacm.wx.service;

import com.hgnuacm.wx.domain.Task;

import java.util.List;

/**
 * 任务Service接口
 * 
 * @author violet
 * @date 2024-01-20
 */
public interface ITaskService 
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
     * 批量删除任务
     * 
     * @param uids 需要删除的任务主键集合
     * @return 结果
     */
    public int deleteTaskByUids(String[] uids);

    /**
     * 删除任务信息
     * 
     * @param uid 任务主键
     * @return 结果
     */
    public int deleteTaskByUid(String uid);
}
