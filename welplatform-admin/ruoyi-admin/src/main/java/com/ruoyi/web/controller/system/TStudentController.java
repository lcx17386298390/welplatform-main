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
import com.ruoyi.system.domain.TStudent;
import com.ruoyi.system.service.ITStudentService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 学生管理Controller
 * 
 * @author ruoyi
 * @date 2024-03-29
 */
@RestController
@RequestMapping("/welplatform/student")
public class TStudentController extends BaseController
{
    @Autowired
    private ITStudentService tStudentService;

    /**
     * 查询学生管理列表
     */
    @PreAuthorize("@ss.hasPermi('welplatform:student:list')")
    @GetMapping("/list")
    public TableDataInfo list(TStudent tStudent)
    {
        startPage();
        List<TStudent> list = tStudentService.selectTStudentList(tStudent);
        return getDataTable(list);
    }

    /**
     * 导出学生管理列表
     */
    @PreAuthorize("@ss.hasPermi('welplatform:student:export')")
    @Log(title = "学生管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TStudent tStudent)
    {
        List<TStudent> list = tStudentService.selectTStudentList(tStudent);
        ExcelUtil<TStudent> util = new ExcelUtil<TStudent>(TStudent.class);
        util.exportExcel(response, list, "学生管理数据");
    }

    /**
     * 获取学生管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('welplatform:student:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(tStudentService.selectTStudentById(id));
    }

    /**
     * 新增学生管理
     */
    @PreAuthorize("@ss.hasPermi('welplatform:student:add')")
    @Log(title = "学生管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TStudent tStudent)
    {
        return toAjax(tStudentService.insertTStudent(tStudent));
    }

    /**
     * 修改学生管理
     */
    @PreAuthorize("@ss.hasPermi('welplatform:student:edit')")
    @Log(title = "学生管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TStudent tStudent)
    {
        return toAjax(tStudentService.updateTStudent(tStudent));
    }

    /**
     * 删除学生管理
     */
    @PreAuthorize("@ss.hasPermi('welplatform:student:remove')")
    @Log(title = "学生管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(tStudentService.deleteTStudentByIds(ids));
    }
}
