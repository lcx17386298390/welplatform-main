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
import com.ruoyi.system.domain.TUserFormid;
import com.ruoyi.system.service.ITUserFormidService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户微信数据管理Controller
 * 
 * @author ruoyi
 * @date 2024-03-29
 */
@RestController
@RequestMapping("/welplatform/formid")
public class TUserFormidController extends BaseController
{
    @Autowired
    private ITUserFormidService tUserFormidService;

    /**
     * 查询用户微信数据管理列表
     */
    @PreAuthorize("@ss.hasPermi('welplatform:formid:list')")
    @GetMapping("/list")
    public TableDataInfo list(TUserFormid tUserFormid)
    {
        startPage();
        List<TUserFormid> list = tUserFormidService.selectTUserFormidList(tUserFormid);
        return getDataTable(list);
    }

    /**
     * 导出用户微信数据管理列表
     */
    @PreAuthorize("@ss.hasPermi('welplatform:formid:export')")
    @Log(title = "用户微信数据管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TUserFormid tUserFormid)
    {
        List<TUserFormid> list = tUserFormidService.selectTUserFormidList(tUserFormid);
        ExcelUtil<TUserFormid> util = new ExcelUtil<TUserFormid>(TUserFormid.class);
        util.exportExcel(response, list, "用户微信数据管理数据");
    }

    /**
     * 获取用户微信数据管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('welplatform:formid:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(tUserFormidService.selectTUserFormidById(id));
    }

    /**
     * 新增用户微信数据管理
     */
    @PreAuthorize("@ss.hasPermi('welplatform:formid:add')")
    @Log(title = "用户微信数据管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TUserFormid tUserFormid)
    {
        return toAjax(tUserFormidService.insertTUserFormid(tUserFormid));
    }

    /**
     * 修改用户微信数据管理
     */
    @PreAuthorize("@ss.hasPermi('welplatform:formid:edit')")
    @Log(title = "用户微信数据管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TUserFormid tUserFormid)
    {
        return toAjax(tUserFormidService.updateTUserFormid(tUserFormid));
    }

    /**
     * 删除用户微信数据管理
     */
    @PreAuthorize("@ss.hasPermi('welplatform:formid:remove')")
    @Log(title = "用户微信数据管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tUserFormidService.deleteTUserFormidByIds(ids));
    }
}
