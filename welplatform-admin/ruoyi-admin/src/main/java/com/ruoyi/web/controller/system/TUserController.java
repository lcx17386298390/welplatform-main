package com.ruoyi.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.R;
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
import com.ruoyi.system.domain.TUser;
import com.ruoyi.system.service.ITUserService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户管理Controller
 * 
 * @author ruoyi
 * @date 2024-03-29
 */
@RestController
@RequestMapping("/welplatform/user")
public class TUserController extends BaseController
{
    @Autowired
    private ITUserService tUserService;

    private static final Logger logger = LoggerFactory.getLogger(TUserController.class);

    /**
     * 查询用户管理列表
     */
    @PreAuthorize("@ss.hasPermi('welplatform:user:list')")
    @GetMapping("/list")
    public TableDataInfo list(TUser tUser)
    {
        startPage();
        List<TUser> list = tUserService.selectTUserList(tUser);
        return getDataTable(list);
    }

    /**
     * 导出用户管理列表
     */
    @PreAuthorize("@ss.hasPermi('welplatform:user:export')")
    @Log(title = "用户管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TUser tUser)
    {
        List<TUser> list = tUserService.selectTUserList(tUser);
        ExcelUtil<TUser> util = new ExcelUtil<TUser>(TUser.class);
        util.exportExcel(response, list, "用户管理数据");
    }

    /**
     * 获取用户管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('welplatform:user:query')")
    @GetMapping(value = "/{uid}")
    public AjaxResult getInfo(@PathVariable("uid") String uid)
    {
        return success(tUserService.selectTUserByUid(uid));
    }

    /**
     * 新增用户管理
     */
    @PreAuthorize("@ss.hasPermi('welplatform:user:add')")
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TUser tUser)
    {
        return toAjax(tUserService.insertTUser(tUser));
    }

    /**
     * 修改用户管理
     */
    @PreAuthorize("@ss.hasPermi('welplatform:user:edit')")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TUser tUser)
    {
        return toAjax(tUserService.updateTUser(tUser));
    }

    /**
     * 删除用户管理
     */
    @PreAuthorize("@ss.hasPermi('welplatform:user:remove')")
    @Log(title = "用户管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{uids}")
    public AjaxResult remove(@PathVariable String[] uids)
    {
        return toAjax(tUserService.deleteTUserByUids(uids));
    }

    /**
     * 获取认证人数未认证人数
     * @return
     */
    @GetMapping("/getAuthCnt")
    public R getAuthCnt(){
        logger.info("【请求开始】获取认证未认证人数");
        List<TUser> userList = tUserService.getUserList();
        int[] cnt = new int[2];
        int hasAuth = 0;
        int notAuth = 0;
        for (TUser user : userList) {
            if(user.getAdmissionStatus().equals(Constants.HAS_AUTH)){
                hasAuth++;
            }else{
                notAuth++;
            }
        }
        cnt[0] = hasAuth;
        cnt[1] = notAuth;
        return R.ok(cnt);
    }
}
