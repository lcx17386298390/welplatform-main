package com.hgnuacm.wx.service.impl;

import java.util.List;
import com.hgnuacm.common.utils.DateUtils;
import com.hgnuacm.wx.domain.Task;
import com.hgnuacm.wx.mapper.TaskMapper;
import com.hgnuacm.wx.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 任务Service业务层处理
 * 
 * @author violet
 * @date 2024-01-20
 */
@Service
public class TaskServiceImpl implements ITaskService
{
    @Autowired
    private TaskMapper taskMapper;

    /**
     * 查询任务
     * 
     * @param uid 任务主键
     * @return 任务
     */
    @Override
    public Task selectTaskByUid(String uid)
    {
        return taskMapper.selectTaskByUid(uid);
    }

    /**
     * 查询任务列表
     * 
     * @param task 任务
     * @return 任务
     */
    @Override
    public List<Task> selectTaskList(Task task)
    {
        return taskMapper.selectTaskList(task);
    }

    /**
     * 新增任务
     * 
     * @param task 任务
     * @return 结果
     */
    @Override
    public int insertTask(Task task)
    {
        task.setCreateTime(DateUtils.getNowDate());
        return taskMapper.insertTask(task);
    }

    /**
     * 修改任务
     * 
     * @param task 任务
     * @return 结果
     */
    @Override
    public int updateTask(Task task)
    {
        task.setUpdateTime(DateUtils.getNowDate());
        return taskMapper.updateTask(task);
    }

    /**
     * 批量删除任务
     * 
     * @param uids 需要删除的任务主键
     * @return 结果
     */
    @Override
    public int deleteTaskByUids(String[] uids)
    {
        return taskMapper.deleteTaskByUids(uids);
    }

    /**
     * 删除任务信息
     * 
     * @param uid 任务主键
     * @return 结果
     */
    @Override
    public int deleteTaskByUid(String uid)
    {
        return taskMapper.deleteTaskByUid(uid);
    }
}
