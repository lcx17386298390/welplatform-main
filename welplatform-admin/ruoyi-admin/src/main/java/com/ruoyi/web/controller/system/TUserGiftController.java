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
import com.ruoyi.system.domain.TUserGift;
import com.ruoyi.system.service.ITUserGiftService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户礼品管理Controller
 * 
 * @author ruoyi
 * @date 2024-03-29
 */
@RestController
@RequestMapping("/welplatform/usergift")
public class TUserGiftController extends BaseController
{
    @Autowired
    private ITUserGiftService tUserGiftService;

    /**
     * 查询用户礼品管理列表
     */
    @PreAuthorize("@ss.hasPermi('welplatform:gift:list')")
    @GetMapping("/list")
    public TableDataInfo list(TUserGift tUserGift)
    {
        startPage();
        List<TUserGift> list = tUserGiftService.selectTUserGiftList(tUserGift);
        return getDataTable(list);
    }

    /**
     * 导出用户礼品管理列表
     */
    @PreAuthorize("@ss.hasPermi('welplatform:gift:export')")
    @Log(title = "用户礼品管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TUserGift tUserGift)
    {
        List<TUserGift> list = tUserGiftService.selectTUserGiftList(tUserGift);
        ExcelUtil<TUserGift> util = new ExcelUtil<TUserGift>(TUserGift.class);
        util.exportExcel(response, list, "用户礼品管理数据");
    }

    /**
     * 获取用户礼品管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('welplatform:gift:query')")
    @GetMapping(value = "/{uid}")
    public AjaxResult getInfo(@PathVariable("uid") String uid)
    {
        return success(tUserGiftService.selectTUserGiftByUid(uid));
    }

    /**
     * 新增用户礼品管理
     */
    @PreAuthorize("@ss.hasPermi('welplatform:gift:add')")
    @Log(title = "用户礼品管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TUserGift tUserGift)
    {
        return toAjax(tUserGiftService.insertTUserGift(tUserGift));
    }

    /**
     * 修改用户礼品管理
     */
    @PreAuthorize("@ss.hasPermi('welplatform:gift:edit')")
    @Log(title = "用户礼品管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TUserGift tUserGift)
    {
        return toAjax(tUserGiftService.updateTUserGift(tUserGift));
    }

    /**
     * 删除用户礼品管理
     */
    @PreAuthorize("@ss.hasPermi('welplatform:gift:remove')")
    @Log(title = "用户礼品管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{uids}")
    public AjaxResult remove(@PathVariable String[] uids)
    {
        return toAjax(tUserGiftService.deleteTUserGiftByUids(uids));
    }
}
