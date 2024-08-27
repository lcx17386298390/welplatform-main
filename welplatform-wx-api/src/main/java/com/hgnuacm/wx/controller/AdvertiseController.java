package com.hgnuacm.wx.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.hgnuacm.common.utils.poi.ExcelUtil;
import com.hgnuacm.wx.domain.Advertise;
import com.hgnuacm.wx.service.IAdvertiseService;
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
import com.hgnuacm.common.core.controller.BaseController;
import com.hgnuacm.common.core.domain.AjaxResult;
import com.hgnuacm.common.enums.BusinessType;
import com.hgnuacm.common.core.page.TableDataInfo;

/**
 * 广告Controller
 * 
 * @author violet
 * @date 2024-01-20
 */
@RestController
@RequestMapping("/welplatform-advertise/advertise")
public class AdvertiseController extends BaseController
{
    @Autowired
    private IAdvertiseService advertiseService;

    /**
     * 查询广告列表
     */
    @PreAuthorize("@ss.hasPermi('welplatform-advertise:advertise:list')")
    @GetMapping("/list")
    public TableDataInfo list(Advertise advertise)
    {
        startPage();
        List<Advertise> list = advertiseService.selectAdvertiseList(advertise);
        return getDataTable(list);
    }

    /**
     * 导出广告列表
     */
    @PreAuthorize("@ss.hasPermi('welplatform-advertise:advertise:export')")
    @Log(title = "广告", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Advertise advertise)
    {
        List<Advertise> list = advertiseService.selectAdvertiseList(advertise);
        ExcelUtil<Advertise> util = new ExcelUtil<Advertise>(Advertise.class);
        util.exportExcel(response, list, "广告数据");
    }

    /**
     * 获取广告详细信息
     */
    @PreAuthorize("@ss.hasPermi('welplatform-advertise:advertise:query')")
    @GetMapping(value = "/{uid}")
    public AjaxResult getInfo(@PathVariable("uid") String uid)
    {
        return success(advertiseService.selectAdvertiseByUid(uid));
    }

    /**
     * 新增广告
     */
    @PreAuthorize("@ss.hasPermi('welplatform-advertise:advertise:add')")
    @Log(title = "广告", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Advertise advertise)
    {
        return toAjax(advertiseService.insertAdvertise(advertise));
    }

    /**
     * 修改广告
     */
    @PreAuthorize("@ss.hasPermi('welplatform-advertise:advertise:edit')")
    @Log(title = "广告", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Advertise advertise)
    {
        return toAjax(advertiseService.updateAdvertise(advertise));
    }

    /**
     * 删除广告
     */
    @PreAuthorize("@ss.hasPermi('welplatform-advertise:advertise:remove')")
    @Log(title = "广告", businessType = BusinessType.DELETE)
	@DeleteMapping("/{uids}")
    public AjaxResult remove(@PathVariable String[] uids)
    {
        return toAjax(advertiseService.deleteAdvertiseByUids(uids));
    }
}
