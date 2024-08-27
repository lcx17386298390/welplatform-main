package com.hgnuacm.wx.mapper;


import com.hgnuacm.wx.domain.TTaskType;

import java.util.List;

/**
 * 任务类型Mapper接口
 * 
 * @author Rs
 * @date 2024-03-02
 */
public interface TTaskTypeMapper 
{
    /**
     * 查询任务类型
     * 
     * @param uid 任务类型主键
     * @return 任务类型
     */
    public TTaskType selectTTaskTypeByUid(String uid);

    /**
     * 查询任务类型列表
     * 
     * @param tTaskType 任务类型
     * @return 任务类型集合
     */
    public List<TTaskType> selectTTaskTypeList(TTaskType tTaskType);

    /**
     * 新增任务类型
     * 
     * @param tTaskType 任务类型
     * @return 结果
     */
    public int insertTTaskType(TTaskType tTaskType);

    /**
     * 修改任务类型
     * 
     * @param tTaskType 任务类型
     * @return 结果
     */
    public int updateTTaskType(TTaskType tTaskType);

    /**
     * 删除任务类型
     * 
     * @param uid 任务类型主键
     * @return 结果
     */
    public int deleteTTaskTypeByUid(String uid);

    /**
     * 批量删除任务类型
     * 
     * @param uids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTTaskTypeByUids(String[] uids);

    public TTaskType selectTTaskTypeByTaskId(String taskId);
}
