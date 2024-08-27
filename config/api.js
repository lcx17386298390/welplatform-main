// 本机开发API地址
var WxApiRoot = 'https://welplatform.com/welplatform/wx';


module.exports = {
  AuthLoginByWeixin: WxApiRoot + '/auth/login_by_weixin', //微信登录
  AuthLogout: WxApiRoot + '/auth/logout', //账号登出
  AuthRegisterCaptcha: WxApiRoot + '/auth/regCaptcha', //验证码
  AuthRegister: WxApiRoot + '/auth/register', //账号注册
  AuthLoginByAccount: WxApiRoot + "/auth/login", //账号登录
  AuthIdCard: WxApiRoot + "/auth/idCard", //身份证验证
  AuthStatusCheck: WxApiRoot + "/auth/auth", //检测是否已认证
  AuthSaveData: WxApiRoot + "/auth/saveData", //保存用户信息
  AuthResetPwd: WxApiRoot + "/auth/resetPwd", //用户重置密码

  WxGetUserInfo: WxApiRoot + "/user/getInfo", //获取用户信息

  WxGetRankList: WxApiRoot + "/point/ranking", //获取用户积分排行榜

  WxGetUserTaskList: WxApiRoot + "/task/pointuid", //获取用户任务列表
  WxGetTaskInfo: WxApiRoot + "/taskinfo/detail", //获得任务具体信息
  WxTaskFinish: WxApiRoot + "/taskinfo/finish", //打卡完成
  WxGetTaskType: WxApiRoot + "/taskinfo/type", //获取任务类型
  WxTaskPosFinish: WxApiRoot + "/taskinfo/position", //位置地点打卡
  WxTaskCameraFinish: WxApiRoot + "/taskinfo/camera", //摄像头打卡


  WxGetBuildingTaskList: WxApiRoot + "/task/building_tasks", //获取用户对应教学楼的任务列表
  WxGetUserPoint: WxApiRoot + "/point/info", //获取用户积分

  WxGetGiftList: WxApiRoot + "/gift/giftlist", //获取礼品列表
  WxGetGiftId: WxApiRoot + "/gift", //单个礼品
  WxAddUserGift:WxApiRoot+"/usergift"//添加购买用户购买记录
};