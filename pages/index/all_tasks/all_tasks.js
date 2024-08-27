var api = require('../../../config/api')
Page({

  onLoad: function() {
    var that = this;
    var uid = wx.getStorageSync('userInfo').userId;
    wx.request({
      url: api.WxGetUserTaskList,
      data:{
        uid: uid
      },
      method: 'GET', // 请求方法，可以是 GET、POST 等
      success: (res) => {
        // 请求成功的回调函数，可以在这里处理响应数据
        //console.log(res.data.data); // 响应数据可以通过 res.data 获取
        // 将响应数据传递到页面的数据中
        that.setData({
          tasks: res.data.data
        });
      },
      fail: (error) => {
        // 请求失败的回调函数，可以在这里处理错误
        console.log("回调失败");
      }
    });
  },
  onPullDownRefresh: function() {
    // 这里实现刷新逻辑，例如请求后端数据
    //console.log('用户正在进行下拉刷新');
    // 模拟加载数据过程
    setTimeout(() => {
      // 数据加载完成后，调用wx.stopPullDownRefresh停止下拉刷新
      wx.stopPullDownRefresh();
      wx.showToast({
        title: '刷新成功',
        icon: 'success',
        duration: 1000
      });
    }, 1000);
  },
  data: {
    tasks: [

    ]
  },
  viewDetails: function(e) {
    // 根据点击的任务项ID导航到详情页面
    console.log(e);
    const taskId = e.currentTarget.dataset.id;
    wx.navigateTo({
      url: `/pages/task/details/detail?uid=${taskId}`
    });
  }
});
