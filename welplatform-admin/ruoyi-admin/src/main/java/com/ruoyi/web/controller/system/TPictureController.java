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
import com.ruoyi.system.domain.TPicture;
import com.ruoyi.system.service.ITPictureService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 照片管理Controller
 * 
 * @author ruoyi
 * @date 2024-03-29
 */
@RestController
@RequestMapping("/welplatform/picture")
public class TPictureController extends BaseController
{
    @Autowired
    private ITPictureService tPictureService;

    /**
     * 查询照片管理列表
     */
    @PreAuthorize("@ss.hasPermi('welplatform:picture:list')")
    @GetMapping("/list")
    public TableDataInfo list(TPicture tPicture)
    {
        startPage();
        List<TPicture> list = tPictureService.selectTPictureList(tPicture);
        return getDataTable(list);
    }

    /**
     * 导出照片管理列表
     */
    @PreAuthorize("@ss.hasPermi('welplatform:picture:export')")
    @Log(title = "照片管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TPicture tPicture)
    {
        List<TPicture> list = tPictureService.selectTPictureList(tPicture);
        ExcelUtil<TPicture> util = new ExcelUtil<TPicture>(TPicture.class);
        util.exportExcel(response, list, "照片管理数据");
    }

    /**
     * 获取照片管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('welplatform:picture:query')")
    @GetMapping(value = "/{uid}")
    public AjaxResult getInfo(@PathVariable("uid") String uid)
    {
        return success(tPictureService.selectTPictureByUid(uid));
    }

    /**
     * 新增照片管理
     */
    @PreAuthorize("@ss.hasPermi('welplatform:picture:add')")
    @Log(title = "照片管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TPicture tPicture)
    {
        return toAjax(tPictureService.insertTPicture(tPicture));
    }

    /**
     * 修改照片管理
     */
    @PreAuthorize("@ss.hasPermi('welplatform:picture:edit')")
    @Log(title = "照片管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TPicture tPicture)
    {
        return toAjax(tPictureService.updateTPicture(tPicture));
    }

    /**
     * 删除照片管理
     */
    @PreAuthorize("@ss.hasPermi('welplatform:picture:remove')")
    @Log(title = "照片管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{uids}")
    public AjaxResult remove(@PathVariable String[] uids)
    {
        return toAjax(tPictureService.deleteTPictureByUids(uids));
    }
}
