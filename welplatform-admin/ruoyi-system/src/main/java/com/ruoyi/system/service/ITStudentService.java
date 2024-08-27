package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.TStudent;

/**
 * 学生管理Service接口
 * 
 * @author ruoyi
 * @date 2024-03-29
 */
public interface ITStudentService 
{
    /**
     * 查询学生管理
     * 
     * @param id 学生管理主键
     * @return 学生管理
     */
    public TStudent selectTStudentById(String id);

    /**
     * 查询学生管理列表
     * 
     * @param tStudent 学生管理
     * @return 学生管理集合
     */
    public List<TStudent> selectTStudentList(TStudent tStudent);

    /**
     * 新增学生管理
     * 
     * @param tStudent 学生管理
     * @return 结果
     */
    public int insertTStudent(TStudent tStudent);

    /**
     * 修改学生管理
     * 
     * @param tStudent 学生管理
     * @return 结果
     */
    public int updateTStudent(TStudent tStudent);

    /**
     * 批量删除学生管理
     * 
     * @param ids 需要删除的学生管理主键集合
     * @return 结果
     */
    public int deleteTStudentByIds(String[] ids);

    /**
     * 删除学生管理信息
     * 
     * @param id 学生管理主键
     * @return 结果
     */
    public int deleteTStudentById(String id);
}
