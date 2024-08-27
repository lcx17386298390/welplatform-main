package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.TUserTask;

/**
 * 用户任务管理Service接口
 * 
 * @author ruoyi
 * @date 2024-03-29
 */
public interface ITUserTaskService 
{
    /**
     * 查询用户任务管理
     * 
     * @param uid 用户任务管理主键
     * @return 用户任务管理
     */
    public TUserTask selectTUserTaskByUid(String uid);

    /**
     * 查询用户任务管理列表
     * 
     * @param tUserTask 用户任务管理
     * @return 用户任务管理集合
     */
    public List<TUserTask> selectTUserTaskList(TUserTask tUserTask);

    /**
     * 新增用户任务管理
     * 
     * @param tUserTask 用户任务管理
     * @return 结果
     */
    public int insertTUserTask(TUserTask tUserTask);

    /**
     * 修改用户任务管理
     * 
     * @param tUserTask 用户任务管理
     * @return 结果
     */
    public int updateTUserTask(TUserTask tUserTask);

    /**
     * 批量删除用户任务管理
     * 
     * @param uids 需要删除的用户任务管理主键集合
     * @return 结果
     */
    public int deleteTUserTaskByUids(String[] uids);

    /**
     * 删除用户任务管理信息
     * 
     * @param uid 用户任务管理主键
     * @return 结果
     */
    public int deleteTUserTaskByUid(String uid);

    public List<TUserTask> getTUserTasks(String taskId);
}
