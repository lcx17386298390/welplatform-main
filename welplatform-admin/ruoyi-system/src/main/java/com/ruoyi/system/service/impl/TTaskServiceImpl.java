package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TTaskMapper;
import com.ruoyi.system.domain.TTask;
import com.ruoyi.system.service.ITTaskService;

/**
 * 任务管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-03-29
 */
@Service
public class TTaskServiceImpl implements ITTaskService 
{
    @Autowired
    private TTaskMapper tTaskMapper;

    /**
     * 查询任务管理
     * 
     * @param uid 任务管理主键
     * @return 任务管理
     */
    @Override
    public TTask selectTTaskByUid(String uid)
    {
        return tTaskMapper.selectTTaskByUid(uid);
    }

    /**
     * 查询任务管理列表
     * 
     * @param tTask 任务管理
     * @return 任务管理
     */
    @Override
    public List<TTask> selectTTaskList(TTask tTask)
    {
        return tTaskMapper.selectTTaskList(tTask);
    }

    /**
     * 新增任务管理
     * 
     * @param tTask 任务管理
     * @return 结果
     */
    @Override
    public int insertTTask(TTask tTask)
    {
        tTask.setCreateTime(DateUtils.getNowDate());
        return tTaskMapper.insertTTask(tTask);
    }

    /**
     * 修改任务管理
     * 
     * @param tTask 任务管理
     * @return 结果
     */
    @Override
    public int updateTTask(TTask tTask)
    {
        tTask.setUpdateTime(DateUtils.getNowDate());
        return tTaskMapper.updateTTask(tTask);
    }

    /**
     * 批量删除任务管理
     * 
     * @param uids 需要删除的任务管理主键
     * @return 结果
     */
    @Override
    public int deleteTTaskByUids(String[] uids)
    {
        return tTaskMapper.deleteTTaskByUids(uids);
    }

    /**
     * 删除任务管理信息
     * 
     * @param uid 任务管理主键
     * @return 结果
     */
    @Override
    public int deleteTTaskByUid(String uid)
    {
        return tTaskMapper.deleteTTaskByUid(uid);
    }

    @Override
    public List<TTask> selectTaskList() {
        return tTaskMapper.selectTaskList();
    }
}
