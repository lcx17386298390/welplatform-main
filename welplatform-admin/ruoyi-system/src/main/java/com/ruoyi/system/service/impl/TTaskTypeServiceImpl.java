package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TTaskTypeMapper;
import com.ruoyi.system.domain.TTaskType;
import com.ruoyi.system.service.ITTaskTypeService;

/**
 * 任务种类管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-03-29
 */
@Service
public class TTaskTypeServiceImpl implements ITTaskTypeService 
{
    @Autowired
    private TTaskTypeMapper tTaskTypeMapper;

    /**
     * 查询任务种类管理
     * 
     * @param uid 任务种类管理主键
     * @return 任务种类管理
     */
    @Override
    public TTaskType selectTTaskTypeByUid(String uid)
    {
        return tTaskTypeMapper.selectTTaskTypeByUid(uid);
    }

    /**
     * 查询任务种类管理列表
     * 
     * @param tTaskType 任务种类管理
     * @return 任务种类管理
     */
    @Override
    public List<TTaskType> selectTTaskTypeList(TTaskType tTaskType)
    {
        return tTaskTypeMapper.selectTTaskTypeList(tTaskType);
    }

    /**
     * 新增任务种类管理
     * 
     * @param tTaskType 任务种类管理
     * @return 结果
     */
    @Override
    public int insertTTaskType(TTaskType tTaskType)
    {
        return tTaskTypeMapper.insertTTaskType(tTaskType);
    }

    /**
     * 修改任务种类管理
     * 
     * @param tTaskType 任务种类管理
     * @return 结果
     */
    @Override
    public int updateTTaskType(TTaskType tTaskType)
    {
        return tTaskTypeMapper.updateTTaskType(tTaskType);
    }

    /**
     * 批量删除任务种类管理
     * 
     * @param uids 需要删除的任务种类管理主键
     * @return 结果
     */
    @Override
    public int deleteTTaskTypeByUids(String[] uids)
    {
        return tTaskTypeMapper.deleteTTaskTypeByUids(uids);
    }

    /**
     * 删除任务种类管理信息
     * 
     * @param uid 任务种类管理主键
     * @return 结果
     */
    @Override
    public int deleteTTaskTypeByUid(String uid)
    {
        return tTaskTypeMapper.deleteTTaskTypeByUid(uid);
    }
}
