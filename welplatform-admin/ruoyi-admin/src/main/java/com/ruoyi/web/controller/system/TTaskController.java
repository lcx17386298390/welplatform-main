package com.ruoyi.web.controller.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.system.domain.TUserTask;
import com.ruoyi.system.service.ITUserService;
import com.ruoyi.system.service.ITUserTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.ruoyi.system.domain.TTask;
import com.ruoyi.system.service.ITTaskService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 任务管理Controller
 * 
 * @author ruoyi
 * @date 2024-03-29
 */
@RestController
@RequestMapping("/welplatform/task")
public class TTaskController extends BaseController
{
    @Autowired
    private ITTaskService tTaskService;

    @Autowired
    private ITUserTaskService userTaskService;

    @Autowired
    private ITUserService userService;

    @Autowired
    private static final Logger logger = LoggerFactory.getLogger(TTaskController.class);
    /**
     * 查询任务管理列表
     */
    @PreAuthorize("@ss.hasPermi('welplatform:task:list')")
    @GetMapping("/list")
    public TableDataInfo list(TTask tTask)
    {
        startPage();
        List<TTask> list = tTaskService.selectTTaskList(tTask);
        return getDataTable(list);
    }

    /**
     * 导出任务管理列表
     */
    @PreAuthorize("@ss.hasPermi('welplatform:task:export')")
    @Log(title = "任务管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TTask tTask)
    {
        List<TTask> list = tTaskService.selectTTaskList(tTask);
        ExcelUtil<TTask> util = new ExcelUtil<TTask>(TTask.class);
        util.exportExcel(response, list, "任务管理数据");
    }

    /**
     * 获取任务管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('welplatform:task:query')")
    @GetMapping(value = "/{uid}")
    public AjaxResult getInfo(@PathVariable("uid") String uid)
    {
        return success(tTaskService.selectTTaskByUid(uid));
    }

    /**
     * 新增任务管理
     */
    @PreAuthorize("@ss.hasPermi('welplatform:task:add')")
    @Log(title = "任务管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TTask tTask)
    {
        return toAjax(tTaskService.insertTTask(tTask));
    }

    /**
     * 修改任务管理
     */
    @PreAuthorize("@ss.hasPermi('welplatform:task:edit')")
    @Log(title = "任务管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TTask tTask)
    {
        return toAjax(tTaskService.updateTTask(tTask));
    }

    /**
     * 删除任务管理
     */
    @PreAuthorize("@ss.hasPermi('welplatform:task:remove')")
    @Log(title = "任务管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{uids}")
    public AjaxResult remove(@PathVariable String[] uids)
    {
        return toAjax(tTaskService.deleteTTaskByUids(uids));
    }

    /**
     * 获取各任务名字数组，各任务完成人数数组，各任务未完成人数数组
     * @return
     */
    @GetMapping("/getTaskCnt")
    public R getTaskCnt(){
        logger.info("【请求开始】获取各任务名字数组，各任务完成人数数组，各任务未完成人数数组");
        Map<String,Object> taskMap = new HashMap<>();
        List<TTask> taskList = tTaskService.selectTaskList();
        int count = userService.getUserCount();
        String[] taskNameArr = new String[taskList.size()];
        Integer[] finishTaskArr = new Integer[taskList.size()];
        Integer[] notfinishTaskArr = new Integer[taskList.size()];
        for(int i = 0; i < taskList.size(); i++){
            //各任务名字数组
            taskNameArr[i] = taskList.get(i).getTaskName();
            //各任务完成情况列表
            List<TUserTask> userTaskList = userTaskService.getTUserTasks(taskList.get(i).getUid());
            //获取完成当前任务的人数数量
            long finishTaskCnt = userTaskList.stream()
                    .filter(tUserTask -> tUserTask.getStatus().equals(Constants.HAS_FINISHED_TASK))
                    .count();
            finishTaskArr[i] = Math.toIntExact(finishTaskCnt);
            notfinishTaskArr[i] = Math.toIntExact(count - finishTaskCnt);
        }

        taskMap.put("taskNameArr",taskNameArr);
        taskMap.put("finishTaskArr",finishTaskArr);
        taskMap.put("notfinishTaskArr",notfinishTaskArr);

        System.out.println(taskMap);
        return R.ok(taskMap);
    }
}
