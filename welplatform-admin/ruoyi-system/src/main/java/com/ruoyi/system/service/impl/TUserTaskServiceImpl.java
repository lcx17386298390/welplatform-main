package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TUserTaskMapper;
import com.ruoyi.system.domain.TUserTask;
import com.ruoyi.system.service.ITUserTaskService;

/**
 * 用户任务管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-03-29
 */
@Service
public class TUserTaskServiceImpl implements ITUserTaskService 
{
    @Autowired
    private TUserTaskMapper tUserTaskMapper;

    /**
     * 查询用户任务管理
     * 
     * @param uid 用户任务管理主键
     * @return 用户任务管理
     */
    @Override
    public TUserTask selectTUserTaskByUid(String uid)
    {
        return tUserTaskMapper.selectTUserTaskByUid(uid);
    }

    /**
     * 查询用户任务管理列表
     * 
     * @param tUserTask 用户任务管理
     * @return 用户任务管理
     */
    @Override
    public List<TUserTask> selectTUserTaskList(TUserTask tUserTask)
    {
        return tUserTaskMapper.selectTUserTaskList(tUserTask);
    }

    /**
     * 新增用户任务管理
     * 
     * @param tUserTask 用户任务管理
     * @return 结果
     */
    @Override
    public int insertTUserTask(TUserTask tUserTask)
    {
        return tUserTaskMapper.insertTUserTask(tUserTask);
    }

    /**
     * 修改用户任务管理
     * 
     * @param tUserTask 用户任务管理
     * @return 结果
     */
    @Override
    public int updateTUserTask(TUserTask tUserTask)
    {
        return tUserTaskMapper.updateTUserTask(tUserTask);
    }

    /**
     * 批量删除用户任务管理
     * 
     * @param uids 需要删除的用户任务管理主键
     * @return 结果
     */
    @Override
    public int deleteTUserTaskByUids(String[] uids)
    {
        return tUserTaskMapper.deleteTUserTaskByUids(uids);
    }

    /**
     * 删除用户任务管理信息
     * 
     * @param uid 用户任务管理主键
     * @return 结果
     */
    @Override
    public int deleteTUserTaskByUid(String uid)
    {
        return tUserTaskMapper.deleteTUserTaskByUid(uid);
    }

    @Override
    public List<TUserTask> getTUserTasks(String taskId) {
        return tUserTaskMapper.getTUserTasks(taskId);
    }
}
