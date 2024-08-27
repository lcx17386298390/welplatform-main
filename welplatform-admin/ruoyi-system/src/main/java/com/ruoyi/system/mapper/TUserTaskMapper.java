package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.TUserTask;

/**
 * 用户任务管理Mapper接口
 * 
 * @author ruoyi
 * @date 2024-03-29
 */
public interface TUserTaskMapper 
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
     * 删除用户任务管理
     * 
     * @param uid 用户任务管理主键
     * @return 结果
     */
    public int deleteTUserTaskByUid(String uid);

    /**
     * 批量删除用户任务管理
     * 
     * @param uids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTUserTaskByUids(String[] uids);

    List<TUserTask> getTUserTasks(String taskId);
}
