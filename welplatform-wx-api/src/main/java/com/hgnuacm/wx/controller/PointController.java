package com.hgnuacm.wx.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;

import com.hgnuacm.common.core.domain.R;
import com.hgnuacm.common.utils.StringUtils;
import com.hgnuacm.common.utils.poi.ExcelUtil;
import com.hgnuacm.wx.domain.DtsUserPoint;
import com.hgnuacm.wx.domain.Point;
import com.hgnuacm.wx.domain.User;
import com.hgnuacm.wx.service.IPointService;
import com.hgnuacm.wx.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.hgnuacm.common.annotation.Log;
import com.hgnuacm.common.core.controller.BaseController;
import com.hgnuacm.common.core.domain.AjaxResult;
import com.hgnuacm.common.enums.BusinessType;
import com.hgnuacm.common.core.page.TableDataInfo;

/**
 * 积分Controller
 * 
 * @author violet
 * @date 2024-01-20
 */
@RestController
@RequestMapping("/wx/point")
public class PointController extends BaseController
{
    @Autowired
    private IPointService pointService;

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(PointController.class);
    /**
     * 查询积分列表
     */
    @PreAuthorize("@ss.hasPermi('welplatform-point:point:list')")
    @GetMapping("/list")
    public TableDataInfo list(Point point)
    {
        startPage();
        List<Point> list = pointService.selectPointList(point);
        return getDataTable(list);
    }

    /**
     * 导出积分列表
     */
    @PreAuthorize("@ss.hasPermi('welplatform-point:point:export')")
    @Log(title = "积分", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Point point)
    {
        List<Point> list = pointService.selectPointList(point);
        ExcelUtil<Point> util = new ExcelUtil<Point>(Point.class);
        util.exportExcel(response, list, "积分数据");
    }

    /**
     * 获取积分详细信息
     */
    @PreAuthorize("@ss.hasPermi('welplatform-point:point:query')")
    @GetMapping(value = "/{uid}")
    public AjaxResult getInfo(@PathVariable("uid") String uid)
    {
        return success(pointService.selectPointByUid(uid));
    }

    /**
     * 新增积分
     */
    @PreAuthorize("@ss.hasPermi('welplatform-point:point:add')")
    @Log(title = "积分", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Point point)
    {
        return toAjax(pointService.insertPoint(point));
    }

    /**
     * 修改积分
     */
    @PreAuthorize("@ss.hasPermi('welplatform-point:point:edit')")
    @Log(title = "积分", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Point point)
    {
        return toAjax(pointService.updatePoint(point));
    }

    /**
     * 删除积分
     */
    @PreAuthorize("@ss.hasPermi('welplatform-point:point:remove')")
    @Log(title = "积分", businessType = BusinessType.DELETE)
	@DeleteMapping("/{uids}")
    public AjaxResult remove(@PathVariable String[] uids)
    {
        return toAjax(pointService.deletePointByUids(uids));
    }

    /**
     * 计算积分排行榜
     * @return
     */
    @GetMapping("/ranking")
    public R loadPointRanking(){
        //logger.info("【请求开始】计算积分排行榜");
        List<Point> pointList = pointService.selectAllPoint();
        List<DtsUserPoint> userPointList = new ArrayList<>();

        for(Point point : pointList){
            User user = userService.selectUserByUid(point.getUserId());
            String userName = user.getUserName();
            DtsUserPoint dtsUserPoint = new DtsUserPoint(userName,point.getPoints(),user.getAvatar());
            userPointList.add(dtsUserPoint);
        }


        logger.info("【请求结束】计算积分排行榜完成");
        return R.ok(userPointList);
    }

    /**
     * 动态更新用户积分
     * @param userId
     * @return
     */
    @GetMapping("/info")
    public R getUserPointInfo(@RequestParam String userId){
        if(StringUtils.isEmpty(userId)){
            logger.info("[积分查询]未查询到用户");
            return R.fail("[积分查询]未查询到用户");
        }
        Point point = pointService.selectPointByUserId(userId);

        return R.ok(point.getPoints());
    }
}
