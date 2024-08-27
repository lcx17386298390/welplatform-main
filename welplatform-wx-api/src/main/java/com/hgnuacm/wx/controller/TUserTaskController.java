package com.hgnuacm.wx.controller;

import com.hgnuacm.common.annotation.Log;
import com.hgnuacm.common.constant.Constants;
import com.hgnuacm.common.core.controller.BaseController;
import com.hgnuacm.common.core.domain.AjaxResult;
import com.hgnuacm.common.core.page.TableDataInfo;
import com.hgnuacm.common.enums.BusinessType;
import com.hgnuacm.common.utils.poi.ExcelUtil;
import com.hgnuacm.wx.domain.DtsTask;
import com.hgnuacm.wx.domain.TUserTask;
import com.hgnuacm.wx.domain.Task;
import com.hgnuacm.wx.service.ITUserTaskService;
import com.hgnuacm.wx.service.ITaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 用户任务关联Controller
 *
 * @author ruoyi
 * @date 2024-03-07
 */
@RestController
@RequestMapping("/wx/task")
public class TUserTaskController extends BaseController {
    @Autowired
    private ITUserTaskService tUserTaskService;
    @Autowired
    private ITaskService taskService;

    private static final Logger logger = LoggerFactory.getLogger(TUserTaskController.class);
    /**
     * 查询用户任务关联列表
     */
    @PreAuthorize("@ss.hasPermi('system:task:list')")
    @GetMapping("/list")
    public TableDataInfo list(TUserTask tUserTask) {
        startPage();
        List<TUserTask> list = tUserTaskService.selectTUserTaskList(tUserTask);
        return getDataTable(list);
    }

    /**
     * 导出用户任务关联列表
     */
    @PreAuthorize("@ss.hasPermi('system:task:export')")
    @Log(title = "用户任务关联", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TUserTask tUserTask) {
        List<TUserTask> list = tUserTaskService.selectTUserTaskList(tUserTask);
        ExcelUtil<TUserTask> util = new ExcelUtil<TUserTask>(TUserTask.class);
        util.exportExcel(response, list, "用户任务关联数据");
    }

    /**
     * 获取用户任务关联详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:task:query')")
    @GetMapping(value = "/{uid}")
    public AjaxResult getInfo(@PathVariable("uid") String uid) {
        return success(tUserTaskService.selectTUserTaskByUid(uid));
    }

    /**
     * 新增用户任务关联
     */
    @PreAuthorize("@ss.hasPermi('system:task:add')")
    @Log(title = "用户任务关联", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TUserTask tUserTask) {
        return toAjax(tUserTaskService.insertTUserTask(tUserTask));
    }

    /**
     * 修改用户任务关联
     */
    @PreAuthorize("@ss.hasPermi('system:task:edit')")
    @Log(title = "用户任务关联", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TUserTask tUserTask) {
        return toAjax(tUserTaskService.updateTUserTask(tUserTask));
    }

    /**
     * 删除用户任务关联
     */
    @PreAuthorize("@ss.hasPermi('system:task:remove')")
    @Log(title = "用户任务关联", businessType = BusinessType.DELETE)
    @DeleteMapping("/{uids}")
    public AjaxResult remove(@PathVariable String[] uids) {
        return toAjax(tUserTaskService.deleteTUserTaskByUids(uids));
    }

    /**
     * 用户id查询任务并计算任务总积分
     */
    @GetMapping(value = "/pointuid")
    public AjaxResult getInfoUserIdTask(@RequestParam String uid) {
        logger.info("【用户id查询任务并计算任务总积分】请求参数,uid:{}", uid);
//        用户id查询所有用户任务
        List<TUserTask> taskUserList = tUserTaskService.selectTUserTaskListByUserId(uid);
        List<DtsTask> taskList = new ArrayList<>();

        for (TUserTask tUserTask : taskUserList) {
            DtsTask dtsTask = new DtsTask();
            dtsTask.setTask(taskService.selectTaskByUid(tUserTask.getTaskId()));
            dtsTask.setStatus(tUserTask.getStatus().equals(Constants.TASK_FINISHED));
            taskList.add(dtsTask);
        }

        logger.info("【用户id查询任务并计算任务总积分】任务列表数量为,taskInfos:{}", taskList.size());
        return success(taskList);
    }
        /**
     * 根据任务id更新楼栋打卡列表
     * ***/
    @GetMapping("/building_tasks")
    public AjaxResult build_tasks(@RequestParam String uid,@RequestParam String author)
    {
        logger.info("【更新楼栋打卡列表】请求参数,uid:{} author:{}", uid,author);
        //根据用户id查询总任务
        List<TUserTask> taskUserList = tUserTaskService.selectTUserTaskListByUserId(uid);
        //logger.info("【请求开始】查询打卡列表,请求参数,taskUserList:{} ", taskUserList);
        List<DtsTask> taskList = new ArrayList<>();
        //在总任务中查询指定author的任务
        for(TUserTask tUserTask:taskUserList)
        {
            String Author=taskService.selectTaskByUid(tUserTask.getTaskId()).getAuthor();
            //logger.info("查询任务详细内容 Author:{}",Author);
            if(Objects.equals(Author, author))
            {
                DtsTask dtsTask = new DtsTask();
                dtsTask.setTask(taskService.selectTaskByUid(tUserTask.getTaskId()));
                dtsTask.setStatus(tUserTask.getStatus().equals(Constants.TASK_FINISHED));
                taskList.add(dtsTask);
            }
        }
        logger.info("【更新楼栋打卡列表】任务列表数量为,taskInfos:{}", taskList.size());
        return success(taskList);
    }

    private int calculateToalPoints(List<Task> taskInfos) {
        int totalPoints = 0;
        for(Task task:taskInfos){
            totalPoints+=task.getPoints();
        }
        return totalPoints;
    }



}
