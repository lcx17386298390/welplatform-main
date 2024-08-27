# welplatform
大学新生智慧迎新平台

###
1.执行npm i tdesign-miniprogram -S --production 
###
2.npm i @vant/weapp -S --production 
###
3.将 app.json 中的 "style": "v2" 去除，小程序的新版基础组件强行加上了许多样式，难以覆盖，不关闭将造成部分组件样式混乱。 
###
4.打开微信开发者工具，点击 工具 -> 构建 npm，并勾选 使用 npm 模块 选项，构建完成后，即可引入组件。
###
5.地图在真机测试时准确，调试时用的虚拟ip定位会有误差400-500m!
###
6.本地运行环境变量还需搭载ALIBABA_CLOUD_ACCESS_KEY_ID,ALIBABA_CLOUD_ACCESS_KEY_SECRET,详情见https://help.aliyun.com/zh/viapi/use-cases/general-image-marking-1?spm=a2c4g.11186623.0.i12
###
7.在https://lbs.qq.com/dev/console/application/mine,增加自己的key,详情见https://lbs.qq.com/miniProgram/jsSdk/jsSdkGuide/jsSdkOverview
###
8.后台管理系统修改/welplatform-admin -> ruoyi-admin -> resources -> application-druid.yml 前台改wx-api -> application.yml
###
9.后台管理系统前端详情看ruoyi-ui/readme
###
10.t_student表中一定要有对应t_user表中关于idCard的模拟数据，不然基本信息无法正常显示全部