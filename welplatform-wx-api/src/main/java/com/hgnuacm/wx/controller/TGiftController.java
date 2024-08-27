package com.hgnuacm.wx.controller;

import javax.servlet.http.HttpServletResponse;

import com.github.pagehelper.PageInfo;
import com.hgnuacm.common.annotation.Log;
import com.hgnuacm.common.core.controller.BaseController;
import com.hgnuacm.common.core.domain.AjaxResult;
import com.hgnuacm.common.core.domain.R;
import com.hgnuacm.common.core.page.TableDataInfo;
import com.hgnuacm.common.enums.BusinessType;
import com.hgnuacm.common.utils.poi.ExcelUtil;
import com.hgnuacm.wx.domain.DtsGift;
import com.hgnuacm.wx.domain.TGift;
import com.hgnuacm.wx.service.ITGiftService;
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

import java.util.ArrayList;
import java.util.List;


/**
 * 礼品Controller
 * 
 * @author Rs
 * @date 2024-03-28
 */
@RestController
@RequestMapping("/wx/gift")
public class TGiftController extends BaseController
{
    @Autowired
    private ITGiftService tGiftService;

    /**
     * 查询礼品列表
     */
    @PreAuthorize("@ss.hasPermi('system:gift:list')")
    @GetMapping("/list")
    public TableDataInfo list(TGift tGift)
    {
        startPage();
        List<TGift> list = tGiftService.selectTGiftList(tGift);
        return getDataTable(list);
    }

    /**
     * 导出礼品列表
     */
    @PreAuthorize("@ss.hasPermi('system:gift:export')")
    @Log(title = "礼品", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TGift tGift)
    {
        List<TGift> list = tGiftService.selectTGiftList(tGift);
        ExcelUtil<TGift> util = new ExcelUtil<TGift>(TGift.class);
        util.exportExcel(response, list, "礼品数据");
    }

    /**
     * 获取礼品详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:gift:query')")
    @GetMapping(value = "/{giftId}")
    public AjaxResult getInfo(@PathVariable("giftId") String giftId)
    {
        return success(tGiftService.selectTGiftByGiftId(giftId));
    }

    /**
     * 新增礼品
     */
    @PreAuthorize("@ss.hasPermi('system:gift:add')")
    @Log(title = "礼品", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TGift tGift)
    {
        return toAjax(tGiftService.insertTGift(tGift));
    }

    /**
     * 修改礼品
     */
    @PreAuthorize("@ss.hasPermi('system:gift:edit')")
    @Log(title = "礼品", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TGift tGift)
    {
        return toAjax(tGiftService.updateTGift(tGift));
    }

    /**
     * 删除礼品
     */
    @PreAuthorize("@ss.hasPermi('system:gift:remove')")
    @Log(title = "礼品", businessType = BusinessType.DELETE)
	@DeleteMapping("/{giftIds}")
    public AjaxResult remove(@PathVariable String[] giftIds) {
        return toAjax(tGiftService.deleteTGiftByGiftIds(giftIds));
    }
    /**
     * 获取礼品列表
     */
    @GetMapping("/giftlist")
    public TableDataInfo getGiftList(TGift tgift){
        logger.info("【请求结束】查询礼品开始");
        startPage();
        List<TGift> list=tGiftService.selectTGiftList(tgift);
        List<DtsGift> giftslist=new ArrayList<>();
        for(TGift tGift:list){
            DtsGift dtsGift=new DtsGift(tGift.getGiftId(),tGift.getGiftName(),tGift.getRequiredPoints(),tGift.getStockQuantity(),tGift.getImageLink(),tGift.getPrice());
            giftslist.add(dtsGift);
        }

//            TableDataInfo tableDataInfo=new TableDataInfo();
//            tableDataInfo.setRows(giftslist);
//            tableDataInfo.setTotal(new PageInfo<>(list).getTotal());
//            return tableDataInfo;
//
        logger.info("【请求结束】查询礼品完成");
        return getDataTable(giftslist);
    }

//    @GetMapping("/giftId")
//    public


}
