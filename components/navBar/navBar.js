Component({
  properties: {},
  data: {
    navigationTop: 0, // 导航栏顶部高度
    navigationHei: 20, // 导航栏高度
    paddingLeft: 0, // 导航栏做内边距
    imgArrow: "http://m.jqtianxia.cn/rubbish/common/icon-arrow-left.png", // 返回箭头
    paddingTopNum: wx.getSystemInfoSync().statusBarHeight  + 7 //这里为啥要+7，是因为小程序的胶囊按钮距离手机浏览器之间还有7个像素的间距，所以是为了让导航能够和胶囊按钮齐平
  },
  ready: function () {
    // 状态栏高度
    const {screenWidth,statusBarHeight} = wx.getSystemInfoSync();
    // 胶囊按钮
    const {height,top,right} = wx.getMenuButtonBoundingClientRect();
    // 左边内边距
    const paddingLeft = screenWidth - right;
    const navigationHei = (top - statusBarHeight) * 2 + height;
    this.setData({
      navigationTop: statusBarHeight,
      navigationHei,
      paddingLeft
    })
  },
  methods: {
    back: function () {
      wx.navigateBack({
        delta: 1,
      })
    }
  }
})