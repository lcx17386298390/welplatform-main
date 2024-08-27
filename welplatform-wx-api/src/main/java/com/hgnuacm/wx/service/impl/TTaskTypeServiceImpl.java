package com.hgnuacm.wx.service.impl;

import com.hgnuacm.wx.domain.TTaskType;
import com.hgnuacm.wx.mapper.TTaskTypeMapper;
import com.hgnuacm.wx.service.ITTaskTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 任务类型Service业务层处理
 * 
 * @author Rs
 * @date 2024-03-02
 */
@Service
public class TTaskTypeServiceImpl implements ITTaskTypeService
{
    @Autowired
    private TTaskTypeMapper tTaskTypeMapper;

    /**
     * 查询任务类型
     * 
     * @param uid 任务类型主键
     * @return 任务类型
     */
    @Override
    public TTaskType selectTTaskTypeByUid(String uid)
    {
        return tTaskTypeMapper.selectTTaskTypeByUid(uid);
    }

    /**
     * 查询任务类型列表
     * 
     * @param tTaskType 任务类型
     * @return 任务类型
     */
    @Override
    public List<TTaskType> selectTTaskTypeList(TTaskType tTaskType)
    {
        return tTaskTypeMapper.selectTTaskTypeList(tTaskType);
    }

    /**
     * 新增任务类型
     * 
     * @param tTaskType 任务类型
     * @return 结果
     */
    @Override
    public int insertTTaskType(TTaskType tTaskType)
    {
        return tTaskTypeMapper.insertTTaskType(tTaskType);
    }

    /**
     * 修改任务类型
     * 
     * @param tTaskType 任务类型
     * @return 结果
     */
    @Override
    public int updateTTaskType(TTaskType tTaskType)
    {
        return tTaskTypeMapper.updateTTaskType(tTaskType);
    }

    /**
     * 批量删除任务类型
     * 
     * @param uids 需要删除的任务类型主键
     * @return 结果
     */
    @Override
    public int deleteTTaskTypeByUids(String[] uids)
    {
        return tTaskTypeMapper.deleteTTaskTypeByUids(uids);
    }

    /**
     * 删除任务类型信息
     * 
     * @param uid 任务类型主键
     * @return 结果
     */
    @Override
    public int deleteTTaskTypeByUid(String uid)
    {
        return tTaskTypeMapper.deleteTTaskTypeByUid(uid);
    }


    @Override
    public TTaskType selectTTaskTypeByTaskId(String taskId) {
        return tTaskTypeMapper.selectTTaskTypeByTaskId(taskId);
    }
}
