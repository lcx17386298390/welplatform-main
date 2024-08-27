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
import com.ruoyi.system.domain.TAdmin;
import com.ruoyi.system.service.ITAdminService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * admin管理Controller
 * 
 * @author ruoyi
 * @date 2024-03-29
 */
@RestController
@RequestMapping("/welplatform/admin")
public class TAdminController extends BaseController
{
    @Autowired
    private ITAdminService tAdminService;

    /**
     * 查询admin管理列表
     */
    @PreAuthorize("@ss.hasPermi('welplatform:admin:list')")
    @GetMapping("/list")
    public TableDataInfo list(TAdmin tAdmin)
    {
        startPage();
        List<TAdmin> list = tAdminService.selectTAdminList(tAdmin);
        return getDataTable(list);
    }

    /**
     * 导出admin管理列表
     */
    @PreAuthorize("@ss.hasPermi('welplatform:admin:export')")
    @Log(title = "admin管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TAdmin tAdmin)
    {
        List<TAdmin> list = tAdminService.selectTAdminList(tAdmin);
        ExcelUtil<TAdmin> util = new ExcelUtil<TAdmin>(TAdmin.class);
        util.exportExcel(response, list, "admin管理数据");
    }

    /**
     * 获取admin管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('welplatform:admin:query')")
    @GetMapping(value = "/{uid}")
    public AjaxResult getInfo(@PathVariable("uid") String uid)
    {
        return success(tAdminService.selectTAdminByUid(uid));
    }

    /**
     * 新增admin管理
     */
    @PreAuthorize("@ss.hasPermi('welplatform:admin:add')")
    @Log(title = "admin管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TAdmin tAdmin)
    {
        return toAjax(tAdminService.insertTAdmin(tAdmin));
    }

    /**
     * 修改admin管理
     */
    @PreAuthorize("@ss.hasPermi('welplatform:admin:edit')")
    @Log(title = "admin管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TAdmin tAdmin)
    {
        return toAjax(tAdminService.updateTAdmin(tAdmin));
    }

    /**
     * 删除admin管理
     */
    @PreAuthorize("@ss.hasPermi('welplatform:admin:remove')")
    @Log(title = "admin管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{uids}")
    public AjaxResult remove(@PathVariable String[] uids)
    {
        return toAjax(tAdminService.deleteTAdminByUids(uids));
    }
}
