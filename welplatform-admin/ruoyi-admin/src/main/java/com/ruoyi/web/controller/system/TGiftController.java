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
import com.ruoyi.system.domain.TGift;
import com.ruoyi.system.service.ITGiftService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 礼品管理Controller
 * 
 * @author ruoyi
 * @date 2024-03-29
 */
@RestController
@RequestMapping("/welplatform/gift")
public class TGiftController extends BaseController
{
    @Autowired
    private ITGiftService tGiftService;

    /**
     * 查询礼品管理列表
     */
    @PreAuthorize("@ss.hasPermi('welplatform:gift:list')")
    @GetMapping("/list")
    public TableDataInfo list(TGift tGift)
    {
        startPage();
        List<TGift> list = tGiftService.selectTGiftList(tGift);
        return getDataTable(list);
    }

    /**
     * 导出礼品管理列表
     */
    @PreAuthorize("@ss.hasPermi('welplatform:gift:export')")
    @Log(title = "礼品管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TGift tGift)
    {
        List<TGift> list = tGiftService.selectTGiftList(tGift);
        ExcelUtil<TGift> util = new ExcelUtil<TGift>(TGift.class);
        util.exportExcel(response, list, "礼品管理数据");
    }

    /**
     * 获取礼品管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('welplatform:gift:query')")
    @GetMapping(value = "/{giftId}")
    public AjaxResult getInfo(@PathVariable("giftId") String giftId)
    {
        return success(tGiftService.selectTGiftByGiftId(giftId));
    }

    /**
     * 新增礼品管理
     */
    @PreAuthorize("@ss.hasPermi('welplatform:gift:add')")
    @Log(title = "礼品管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TGift tGift)
    {
        return toAjax(tGiftService.insertTGift(tGift));
    }

    /**
     * 修改礼品管理
     */
    @PreAuthorize("@ss.hasPermi('welplatform:gift:edit')")
    @Log(title = "礼品管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TGift tGift)
    {
        return toAjax(tGiftService.updateTGift(tGift));
    }

    /**
     * 删除礼品管理
     */
    @PreAuthorize("@ss.hasPermi('welplatform:gift:remove')")
    @Log(title = "礼品管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{giftIds}")
    public AjaxResult remove(@PathVariable String[] giftIds)
    {
        return toAjax(tGiftService.deleteTGiftByGiftIds(giftIds));
    }
}
