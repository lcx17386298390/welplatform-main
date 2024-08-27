package com.hgnuacm.wx.controller;

import javax.servlet.http.HttpServletResponse;

import com.hgnuacm.common.core.controller.BaseController;
import com.hgnuacm.common.core.domain.AjaxResult;
import com.hgnuacm.common.core.page.TableDataInfo;
import com.hgnuacm.common.enums.BusinessType;
import com.hgnuacm.common.utils.poi.ExcelUtil;
import com.hgnuacm.wx.domain.TTaskType;
import com.hgnuacm.wx.service.ITTaskTypeService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hgnuacm.common.annotation.Log;

import java.util.List;

/**
 * 任务类型Controller
 * 
 * @author Rs
 * @date 2024-03-02
 */
@RestController
@RequestMapping("/system/typetask")
public class TTaskTypeController extends BaseController
{
    @Autowired
    private ITTaskTypeService tTaskTypeService;

    /**
     * 查询任务类型列表
     */
    @PreAuthorize("@ss.hasPermi('system:type:list')")
    @GetMapping("/list")
    public TableDataInfo list(TTaskType tTaskType)
    {
        startPage();
        List<TTaskType> list = tTaskTypeService.selectTTaskTypeList(tTaskType);
        return getDataTable(list);
    }

    /**
     * 导出任务类型列表
     */
    @PreAuthorize("@ss.hasPermi('system:type:export')")
    @Log(title = "任务类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TTaskType tTaskType)
    {
        List<TTaskType> list = tTaskTypeService.selectTTaskTypeList(tTaskType);
        ExcelUtil<TTaskType> util = new ExcelUtil<TTaskType>(TTaskType.class);
        util.exportExcel(response, list, "任务类型数据");
    }

    /**
     * 获取任务类型详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:type:query')")
    @GetMapping(value = "/{uid}")
    public AjaxResult getInfo(@PathVariable("uid") String uid)
    {
        return success(tTaskTypeService.selectTTaskTypeByUid(uid));
    }

    /**
     * 新增任务类型
     */
    @PreAuthorize("@ss.hasPermi('system:type:add')")
    @Log(title = "任务类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TTaskType tTaskType)
    {
        return toAjax(tTaskTypeService.insertTTaskType(tTaskType));
    }

    /**
     * 修改任务类型
     */
    @PreAuthorize("@ss.hasPermi('system:type:edit')")
    @Log(title = "任务类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TTaskType tTaskType)
    {
        return toAjax(tTaskTypeService.updateTTaskType(tTaskType));
    }

    /**
     * 删除任务类型
     */
    @PreAuthorize("@ss.hasPermi('system:type:remove')")
    @Log(title = "任务类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{uids}")
    public AjaxResult remove(@PathVariable String[] uids)
    {
        return toAjax(tTaskTypeService.deleteTTaskTypeByUids(uids));
    }
}
