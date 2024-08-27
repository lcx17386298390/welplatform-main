package com.hgnuacm.wx.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.hgnuacm.common.annotation.Log;
import com.hgnuacm.common.core.controller.BaseController;
import com.hgnuacm.common.core.domain.AjaxResult;
import com.hgnuacm.common.core.page.TableDataInfo;
import com.hgnuacm.common.enums.BusinessType;
import com.hgnuacm.common.utils.poi.ExcelUtil;
import com.hgnuacm.wx.domain.TUserGift;
import com.hgnuacm.wx.service.ITUserGiftService;
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


/**
 * 用户购买礼品Controller
 *
 * @author Rs
 * @date 2024-04-02
 */
@RestController
@RequestMapping("/wx/usergift")
public class TUserGiftController extends BaseController
{
    @Autowired
    private ITUserGiftService tUserGiftService;

    /**
     * 查询用户购买礼品列表
     */
    @PreAuthorize("@ss.hasPermi('system:gift:list')")
    @GetMapping("/list")
    public TableDataInfo list(TUserGift tUserGift)
    {
        startPage();
        List<TUserGift> list = tUserGiftService.selectTUserGiftList(tUserGift);
        return getDataTable(list);
    }

    /**
     * 导出用户购买礼品列表
     */
    @PreAuthorize("@ss.hasPermi('system:gift:export')")
    @Log(title = "用户购买礼品", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TUserGift tUserGift)
    {
        List<TUserGift> list = tUserGiftService.selectTUserGiftList(tUserGift);
        ExcelUtil<TUserGift> util = new ExcelUtil<TUserGift>(TUserGift.class);
        util.exportExcel(response, list, "用户购买礼品数据");
    }

    /**
     * 获取用户购买礼品详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:gift:query')")
    @GetMapping(value = "/{uid}")
    public AjaxResult getInfo(@PathVariable("uid") Long uid)
    {
        return success(tUserGiftService.selectTUserGiftByUid(uid));
    }

    /**
     * 新增用户购买礼品
     */
    @PreAuthorize("@ss.hasPermi('system:gift:add')")
    @Log(title = "用户购买礼品", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TUserGift tUserGift)
    {
        return toAjax(tUserGiftService.insertTUserGift(tUserGift));
    }

    /**
     * 修改用户购买礼品
     */
    @PreAuthorize("@ss.hasPermi('system:gift:edit')")
    @Log(title = "用户购买礼品", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TUserGift tUserGift)
    {
        return toAjax(tUserGiftService.updateTUserGift(tUserGift));
    }

    /**
     * 删除用户购买礼品
     */
    @PreAuthorize("@ss.hasPermi('system:gift:remove')")
    @Log(title = "用户购买礼品", businessType = BusinessType.DELETE)
    @DeleteMapping("/{uids}")
    public AjaxResult remove(@PathVariable Long[] uids)
    {
        return toAjax(tUserGiftService.deleteTUserGiftByUids(uids));
    }
}
