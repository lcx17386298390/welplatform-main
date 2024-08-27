package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TAdminMapper;
import com.ruoyi.system.domain.TAdmin;
import com.ruoyi.system.service.ITAdminService;

/**
 * admin管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-03-29
 */
@Service
public class TAdminServiceImpl implements ITAdminService 
{
    @Autowired
    private TAdminMapper tAdminMapper;

    /**
     * 查询admin管理
     * 
     * @param uid admin管理主键
     * @return admin管理
     */
    @Override
    public TAdmin selectTAdminByUid(String uid)
    {
        return tAdminMapper.selectTAdminByUid(uid);
    }

    /**
     * 查询admin管理列表
     * 
     * @param tAdmin admin管理
     * @return admin管理
     */
    @Override
    public List<TAdmin> selectTAdminList(TAdmin tAdmin)
    {
        return tAdminMapper.selectTAdminList(tAdmin);
    }

    /**
     * 新增admin管理
     * 
     * @param tAdmin admin管理
     * @return 结果
     */
    @Override
    public int insertTAdmin(TAdmin tAdmin)
    {
        tAdmin.setCreateTime(DateUtils.getNowDate());
        return tAdminMapper.insertTAdmin(tAdmin);
    }

    /**
     * 修改admin管理
     * 
     * @param tAdmin admin管理
     * @return 结果
     */
    @Override
    public int updateTAdmin(TAdmin tAdmin)
    {
        tAdmin.setUpdateTime(DateUtils.getNowDate());
        return tAdminMapper.updateTAdmin(tAdmin);
    }

    /**
     * 批量删除admin管理
     * 
     * @param uids 需要删除的admin管理主键
     * @return 结果
     */
    @Override
    public int deleteTAdminByUids(String[] uids)
    {
        return tAdminMapper.deleteTAdminByUids(uids);
    }

    /**
     * 删除admin管理信息
     * 
     * @param uid admin管理主键
     * @return 结果
     */
    @Override
    public int deleteTAdminByUid(String uid)
    {
        return tAdminMapper.deleteTAdminByUid(uid);
    }
}
