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
import com.ruoyi.system.domain.TUserTask;
import com.ruoyi.system.service.ITUserTaskService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户任务管理Controller
 * 
 * @author ruoyi
 * @date 2024-03-29
 */
@RestController
@RequestMapping("/welplatform/usertask")
public class TUserTaskController extends BaseController
{
    @Autowired
    private ITUserTaskService tUserTaskService;

    /**
     * 查询用户任务管理列表
     */
    @PreAuthorize("@ss.hasPermi('welplatform:task:list')")
    @GetMapping("/list")
    public TableDataInfo list(TUserTask tUserTask)
    {
        startPage();
        List<TUserTask> list = tUserTaskService.selectTUserTaskList(tUserTask);
        return getDataTable(list);
    }

    /**
     * 导出用户任务管理列表
     */
    @PreAuthorize("@ss.hasPermi('welplatform:task:export')")
    @Log(title = "用户任务管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TUserTask tUserTask)
    {
        List<TUserTask> list = tUserTaskService.selectTUserTaskList(tUserTask);
        ExcelUtil<TUserTask> util = new ExcelUtil<TUserTask>(TUserTask.class);
        util.exportExcel(response, list, "用户任务管理数据");
    }

    /**
     * 获取用户任务管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('welplatform:task:query')")
    @GetMapping(value = "/{uid}")
    public AjaxResult getInfo(@PathVariable("uid") String uid)
    {
        return success(tUserTaskService.selectTUserTaskByUid(uid));
    }

    /**
     * 新增用户任务管理
     */
    @PreAuthorize("@ss.hasPermi('welplatform:task:add')")
    @Log(title = "用户任务管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TUserTask tUserTask)
    {
        return toAjax(tUserTaskService.insertTUserTask(tUserTask));
    }

    /**
     * 修改用户任务管理
     */
    @PreAuthorize("@ss.hasPermi('welplatform:task:edit')")
    @Log(title = "用户任务管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TUserTask tUserTask)
    {
        return toAjax(tUserTaskService.updateTUserTask(tUserTask));
    }

    /**
     * 删除用户任务管理
     */
    @PreAuthorize("@ss.hasPermi('welplatform:task:remove')")
    @Log(title = "用户任务管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{uids}")
    public AjaxResult remove(@PathVariable String[] uids)
    {
        return toAjax(tUserTaskService.deleteTUserTaskByUids(uids));
    }
}
