package com.hgnuacm.wx.service.impl;


import com.hgnuacm.wx.domain.TUserTask;
import com.hgnuacm.wx.mapper.TUserTaskMapper;
import com.hgnuacm.wx.service.ITUserTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 用户任务关联Service业务层处理
 *
 * @author ruoyi
 * @date 2024-03-06
 */
@Service
public class TUserTaskServiceImpl implements ITUserTaskService
{
    @Autowired
    private TUserTaskMapper tUserTaskMapper;

    /**
     * 查询用户任务关联
     *
     * @param uid 用户任务关联主键
     * @return 用户任务关联
     */
    @Override
    public TUserTask selectTUserTaskByUid(String uid)
    {
        return tUserTaskMapper.selectTUserTaskByUid(uid);
    }

    /**
     * 查询用户任务关联列表
     *
     * @param tUserTask 用户任务关联
     * @return 用户任务关联
     */
    @Override
    public List<TUserTask> selectTUserTaskList(TUserTask tUserTask)
    {
        return tUserTaskMapper.selectTUserTaskList(tUserTask);
    }

    /**
     * 新增用户任务关联
     *
     * @param tUserTask 用户任务关联
     * @return 结果
     */
    @Override
    public int insertTUserTask(TUserTask tUserTask)
    {
        return tUserTaskMapper.insertTUserTask(tUserTask);
    }

    /**
     * 修改用户任务关联
     *
     * @param tUserTask 用户任务关联
     * @return 结果
     */
    @Override
    public int updateTUserTask(TUserTask tUserTask)
    {
        return tUserTaskMapper.updateTUserTask(tUserTask);
    }

    /**
     * 批量删除用户任务关联
     *
     * @param uids 需要删除的用户任务关联主键
     * @return 结果
     */
    @Override
    public int deleteTUserTaskByUids(String[] uids)
    {
        return tUserTaskMapper.deleteTUserTaskByUids(uids);
    }

    /**
     * 删除用户任务关联信息
     *
     * @param uid 用户任务关联主键
     * @return 结果
     */
    @Override
    public int deleteTUserTaskByUid(String uid)
    {
        return tUserTaskMapper.deleteTUserTaskByUid(uid);
    }

    /**
     * 用户id查询所有用户任务
     */
    @Override
    public List<TUserTask> selectTUserTaskListByUserId(String userId){
        return tUserTaskMapper.selectTUserTaskListByUserId(userId);
    }

    /**
     * 任务id查询
     * @param taskId
     * @return
     */
    @Override
    public List<TUserTask> getTUserTaskByTaskId(String taskId) {
        return tUserTaskMapper.getTUserTaskByTaskId(taskId);
    }

    @Override
    public TUserTask getTUserTaskByTaskIdUserId(String taskId, String userId) {
        return tUserTaskMapper.getTUserTaskByTaskIdUserId(taskId, userId);
    }
}
