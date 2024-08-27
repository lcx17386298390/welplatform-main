package com.ruoyi.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.TTaskType;
import com.ruoyi.system.service.ITTaskTypeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 任务种类管理Controller
 * 
 * @author ruoyi
 * @date 2024-03-29
 */
@RestController
@RequestMapping("/welplatform/type")
public class TTaskTypeController extends BaseController
{
    @Autowired
    private ITTaskTypeService tTaskTypeService;

    /**
     * 查询任务种类管理列表
     */
    @PreAuthorize("@ss.hasPermi('welplatform:type:list')")
    @GetMapping("/list")
    public TableDataInfo list(TTaskType tTaskType)
    {
        startPage();
        List<TTaskType> list = tTaskTypeService.selectTTaskTypeList(tTaskType);
        return getDataTable(list);
    }

    /**
     * 导出任务种类管理列表
     */
    @PreAuthorize("@ss.hasPermi('welplatform:type:export')")
    @Log(title = "任务种类管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TTaskType tTaskType)
    {
        List<TTaskType> list = tTaskTypeService.selectTTaskTypeList(tTaskType);
        ExcelUtil<TTaskType> util = new ExcelUtil<TTaskType>(TTaskType.class);
        util.exportExcel(response, list, "任务种类管理数据");
    }

    /**
     * 获取任务种类管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('welplatform:type:query')")
    @GetMapping(value = "/{uid}")
    public AjaxResult getInfo(@PathVariable("uid") String uid)
    {
        return success(tTaskTypeService.selectTTaskTypeByUid(uid));
    }

    /**
     * 新增任务种类管理
     */
    @PreAuthorize("@ss.hasPermi('welplatform:type:add')")
    @Log(title = "任务种类管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TTaskType tTaskType)
    {
        return toAjax(tTaskTypeService.insertTTaskType(tTaskType));
    }

    /**
     * 修改任务种类管理
     */
    @PreAuthorize("@ss.hasPermi('welplatform:type:edit')")
    @Log(title = "任务种类管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TTaskType tTaskType)
    {
        return toAjax(tTaskTypeService.updateTTaskType(tTaskType));
    }

    /**
     * 删除任务种类管理
     */
    @PreAuthorize("@ss.hasPermi('welplatform:type:remove')")
    @Log(title = "任务种类管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{uids}")
    public AjaxResult remove(@PathVariable String[] uids)
    {
        return toAjax(tTaskTypeService.deleteTTaskTypeByUids(uids));
    }
}
