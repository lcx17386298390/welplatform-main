package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TStudentMapper;
import com.ruoyi.system.domain.TStudent;
import com.ruoyi.system.service.ITStudentService;

/**
 * 学生管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-03-29
 */
@Service
public class TStudentServiceImpl implements ITStudentService 
{
    @Autowired
    private TStudentMapper tStudentMapper;

    /**
     * 查询学生管理
     * 
     * @param id 学生管理主键
     * @return 学生管理
     */
    @Override
    public TStudent selectTStudentById(String id)
    {
        return tStudentMapper.selectTStudentById(id);
    }

    /**
     * 查询学生管理列表
     * 
     * @param tStudent 学生管理
     * @return 学生管理
     */
    @Override
    public List<TStudent> selectTStudentList(TStudent tStudent)
    {
        return tStudentMapper.selectTStudentList(tStudent);
    }

    /**
     * 新增学生管理
     * 
     * @param tStudent 学生管理
     * @return 结果
     */
    @Override
    public int insertTStudent(TStudent tStudent)
    {
        return tStudentMapper.insertTStudent(tStudent);
    }

    /**
     * 修改学生管理
     * 
     * @param tStudent 学生管理
     * @return 结果
     */
    @Override
    public int updateTStudent(TStudent tStudent)
    {
        return tStudentMapper.updateTStudent(tStudent);
    }

    /**
     * 批量删除学生管理
     * 
     * @param ids 需要删除的学生管理主键
     * @return 结果
     */
    @Override
    public int deleteTStudentByIds(String[] ids)
    {
        return tStudentMapper.deleteTStudentByIds(ids);
    }

    /**
     * 删除学生管理信息
     * 
     * @param id 学生管理主键
     * @return 结果
     */
    @Override
    public int deleteTStudentById(String id)
    {
        return tStudentMapper.deleteTStudentById(id);
    }
}
