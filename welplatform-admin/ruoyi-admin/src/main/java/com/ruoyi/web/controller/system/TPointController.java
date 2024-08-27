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
import com.ruoyi.system.domain.TPoint;
import com.ruoyi.system.service.ITPointService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 积分管理Controller
 * 
 * @author ruoyi
 * @date 2024-03-29
 */
@RestController
@RequestMapping("/welplatform/point")
public class TPointController extends BaseController
{
    @Autowired
    private ITPointService tPointService;

    /**
     * 查询积分管理列表
     */
    @PreAuthorize("@ss.hasPermi('welplatform:point:list')")
    @GetMapping("/list")
    public TableDataInfo list(TPoint tPoint)
    {
        startPage();
        List<TPoint> list = tPointService.selectTPointList(tPoint);
        return getDataTable(list);
    }

    /**
     * 导出积分管理列表
     */
    @PreAuthorize("@ss.hasPermi('welplatform:point:export')")
    @Log(title = "积分管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TPoint tPoint)
    {
        List<TPoint> list = tPointService.selectTPointList(tPoint);
        ExcelUtil<TPoint> util = new ExcelUtil<TPoint>(TPoint.class);
        util.exportExcel(response, list, "积分管理数据");
    }

    /**
     * 获取积分管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('welplatform:point:query')")
    @GetMapping(value = "/{uid}")
    public AjaxResult getInfo(@PathVariable("uid") String uid)
    {
        return success(tPointService.selectTPointByUid(uid));
    }

    /**
     * 新增积分管理
     */
    @PreAuthorize("@ss.hasPermi('welplatform:point:add')")
    @Log(title = "积分管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TPoint tPoint)
    {
        return toAjax(tPointService.insertTPoint(tPoint));
    }

    /**
     * 修改积分管理
     */
    @PreAuthorize("@ss.hasPermi('welplatform:point:edit')")
    @Log(title = "积分管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TPoint tPoint)
    {
        return toAjax(tPointService.updateTPoint(tPoint));
    }

    /**
     * 删除积分管理
     */
    @PreAuthorize("@ss.hasPermi('welplatform:point:remove')")
    @Log(title = "积分管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{uids}")
    public AjaxResult remove(@PathVariable String[] uids)
    {
        return toAjax(tPointService.deleteTPointByUids(uids));
    }
}
