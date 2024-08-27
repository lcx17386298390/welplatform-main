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
import com.ruoyi.system.domain.TAdvertise;
import com.ruoyi.system.service.ITAdvertiseService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 广告管理Controller
 * 
 * @author ruoyi
 * @date 2024-03-29
 */
@RestController
@RequestMapping("/welplatform/advertise")
public class TAdvertiseController extends BaseController
{
    @Autowired
    private ITAdvertiseService tAdvertiseService;

    /**
     * 查询广告管理列表
     */
    @PreAuthorize("@ss.hasPermi('welplatform:advertise:list')")
    @GetMapping("/list")
    public TableDataInfo list(TAdvertise tAdvertise)
    {
        startPage();
        List<TAdvertise> list = tAdvertiseService.selectTAdvertiseList(tAdvertise);
        return getDataTable(list);
    }

    /**
     * 导出广告管理列表
     */
    @PreAuthorize("@ss.hasPermi('welplatform:advertise:export')")
    @Log(title = "广告管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TAdvertise tAdvertise)
    {
        List<TAdvertise> list = tAdvertiseService.selectTAdvertiseList(tAdvertise);
        ExcelUtil<TAdvertise> util = new ExcelUtil<TAdvertise>(TAdvertise.class);
        util.exportExcel(response, list, "广告管理数据");
    }

    /**
     * 获取广告管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('welplatform:advertise:query')")
    @GetMapping(value = "/{uid}")
    public AjaxResult getInfo(@PathVariable("uid") String uid)
    {
        return success(tAdvertiseService.selectTAdvertiseByUid(uid));
    }

    /**
     * 新增广告管理
     */
    @PreAuthorize("@ss.hasPermi('welplatform:advertise:add')")
    @Log(title = "广告管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TAdvertise tAdvertise)
    {
        return toAjax(tAdvertiseService.insertTAdvertise(tAdvertise));
    }

    /**
     * 修改广告管理
     */
    @PreAuthorize("@ss.hasPermi('welplatform:advertise:edit')")
    @Log(title = "广告管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TAdvertise tAdvertise)
    {
        return toAjax(tAdvertiseService.updateTAdvertise(tAdvertise));
    }

    /**
     * 删除广告管理
     */
    @PreAuthorize("@ss.hasPermi('welplatform:advertise:remove')")
    @Log(title = "广告管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{uids}")
    public AjaxResult remove(@PathVariable String[] uids)
    {
        return toAjax(tAdvertiseService.deleteTAdvertiseByUids(uids));
    }
}
