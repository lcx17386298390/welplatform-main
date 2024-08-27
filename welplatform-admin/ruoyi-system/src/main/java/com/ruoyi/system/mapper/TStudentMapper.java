package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.TStudent;

/**
 * 学生管理Mapper接口
 * 
 * @author ruoyi
 * @date 2024-03-29
 */
public interface TStudentMapper 
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
     * 删除学生管理
     * 
     * @param id 学生管理主键
     * @return 结果
     */
    public int deleteTStudentById(String id);

    /**
     * 批量删除学生管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTStudentByIds(String[] ids);
}
