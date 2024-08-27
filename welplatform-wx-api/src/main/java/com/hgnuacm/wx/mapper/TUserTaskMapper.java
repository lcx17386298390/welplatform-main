package com.hgnuacm.wx.mapper;


import com.hgnuacm.wx.domain.TUserTask;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户任务关联Mapper接口
 *
 * @author ruoyi
 * @date 2024-03-06
 */
public interface TUserTaskMapper
{
    /**
     * 查询用户任务关联
     *
     * @param uid 用户任务关联主键
     * @return 用户任务关联
     */
    public TUserTask selectTUserTaskByUid(String uid);

    /**
     * 查询用户任务关联列表
     *
     * @param tUserTask 用户任务关联
     * @return 用户任务关联集合
     */
    public List<TUserTask> selectTUserTaskList(TUserTask tUserTask);

    /**
     * 新增用户任务关联
     *
     * @param tUserTask 用户任务关联
     * @return 结果
     */
    public int insertTUserTask(TUserTask tUserTask);

    /**
     * 修改用户任务关联
     *
     * @param tUserTask 用户任务关联
     * @return 结果
     */
    public int updateTUserTask(TUserTask tUserTask);

    /**
     * 删除用户任务关联
     *
     * @param uid 用户任务关联主键
     * @return 结果
     */
    public int deleteTUserTaskByUid(String uid);

    /**
     * 批量删除用户任务关联
     *
     * @param uids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTUserTaskByUids(String[] uids);

    /**
     * 用户id查询所有用户任务
     */
    public List<TUserTask>  selectTUserTaskListByUserId(String userId);

    /**
     * 任务id查询
     * @param taskId
     * @return
     */
    public List<TUserTask> getTUserTaskByTaskId(String taskId);

    public TUserTask getTUserTaskByTaskIdUserId(@Param("taskId") String taskId,@Param("userId") String userId);
}
