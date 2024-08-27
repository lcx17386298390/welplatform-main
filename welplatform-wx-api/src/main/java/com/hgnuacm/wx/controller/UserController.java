package com.hgnuacm.wx.controller;

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
import com.hgnuacm.common.annotation.Log;
import com.hgnuacm.common.core.controller.BaseController;
import com.hgnuacm.common.core.domain.AjaxResult;
import com.hgnuacm.common.enums.BusinessType;
import com.hgnuacm.wx.domain.User;
import com.hgnuacm.wx.service.UserService;
import com.hgnuacm.common.utils.poi.ExcelUtil;
import com.hgnuacm.common.core.page.TableDataInfo;

/**
 * 用户Controller
 * 
 * @author violet
 * @date 2024-01-20
 */
@RestController
@RequestMapping("/welplatform/user")
public class UserController extends BaseController
{
    @Autowired
    private UserService userService;

    /**
     * 查询用户列表
     */
    @PreAuthorize("@ss.hasPermi('welplatform-user:user:list')")
    @GetMapping("/list")
    public TableDataInfo list(User user)
    {
        startPage();
        List<User> list = userService.selectUserList(user);
        return getDataTable(list);
    }

    /**
     * 导出用户列表
     */
    @PreAuthorize("@ss.hasPermi('welplatform-user:user:export')")
    @Log(title = "用户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, User user)
    {
        List<User> list = userService.selectUserList(user);
        ExcelUtil<User> util = new ExcelUtil<User>(User.class);
        util.exportExcel(response, list, "用户数据");
    }

    /**
     * 获取用户详细信息
     */
    @PreAuthorize("@ss.hasPermi('welplatform-user:user:query')")
    @GetMapping(value = "/{uid}")
    public AjaxResult getInfo(@PathVariable("uid") String uid)
    {
        return success(userService.selectUserByUid(uid));
    }

    /**
     * 新增用户
     */
    @PreAuthorize("@ss.hasPermi('welplatform-user:user:add')")
    @Log(title = "用户", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody User user)
    {
        return toAjax(userService.insertUser(user));
    }

    /**
     * 修改用户
     */
    @PreAuthorize("@ss.hasPermi('welplatform-user:user:edit')")
    @Log(title = "用户", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody User user)
    {
        return toAjax(userService.updateUser(user));
    }

    /**
     * 删除用户
     */
    @PreAuthorize("@ss.hasPermi('welplatform-user:user:remove')")
    @Log(title = "用户", businessType = BusinessType.DELETE)
	@DeleteMapping("/{uids}")
    public AjaxResult remove(@PathVariable String[] uids)
    {
        return toAjax(userService.deleteUserByUids(uids));
    }
}
