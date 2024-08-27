var api = require('../../../config/api')
Page({
  onLoad: function() {
    var that = this;
    var uid = wx.getStorageSync('userInfo').userId;
    wx.request({
      url: api.WxGetBuildingTaskList,
      data:{
        uid: uid,
        author:"博学楼"
      },
      method: 'GET', // 请求方法，可以是 GET、POST 等
      header: {
        'content-type': 'application/json'
      },
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
        console.log(error);
      }
    });
  },
  data: {
    activeView: null,
    pageView:false,
    tasks: [
      
    ],
    dialogTaskId: null, 
    confirmBtn: { content: '知道了', variant: 'base' },
    cancelBtn: { content: '去完成', variant: 'base', bindtap: 'goToComplete' } ,
    goBtn: {content: '去完成' , variant : 'go'},
    showMultiTextAndTitle: false,
  },
  onTap: function() {
    this.setData({
      activeView:null,
      pageView:false,
    });
  },
  showIntroduce: function() {
    this.setData({
      activeView: "introduce", 
    });
  },
  showTaskList: function() {
    this.setData({
      activeView: "tasklist", // 显示任务列表
      pageView:false,
    });
  },
  showNotice: function() {
    this.setData({
      activeView: "notice" 
    });
  },

  preventClose: function(e) {
    e.stopPropagation(); // 阻止事件冒泡
  },

  sortTasks: function() {
    const tasks = this.data.tasks.sort((a, b) => a.status === '已完成' ? 1 : -1);
    this.setData({ tasks });
  },
  showDialog(e) {
    console.log("showDialog called"); // 确认方法被调用
    const taskId = e.currentTarget.dataset.id;
    console.log("Task ID:", taskId); // 输出点击的任务ID
    const task = this.data.tasks.find(task => task.id === taskId);
    console.log("Found task:", task); // 输出找到的任务对象
    if (task) {
      this.setData({
        dialogTaskId: taskId,
        showMultiTextAndTitle: true,
        dialogTitle: task.title,
        dialogDescription: task.description
      }, () => {
        console.log("No task found with ID:", taskId);
      });
    }
  },
  
  
  goToComplete() {
    const taskId = this.data.dialogTaskId;
    wx.navigateTo({
      url: '/pages/index/index.wxml' + taskId,
    });
  },
  closeDialog() {
    this.setData({ showMultiTextAndTitle: false });
  },
  updateTotalProgress: function() {
    const totalTasks = this.data.tasks.length;
    const completedTasks = this.data.tasks.filter(task => task.status === '已完成').length;
    const totalProgress = (completedTasks / totalTasks) * 100;
    this.setData({ totalProgress });
  },

  
  viewDetails: function(e) {
    // 根据点击的任务项ID导航到详情页面
    console.log(e);
    const taskId = e.currentTarget.dataset.id;
    wx.navigateTo({
      url: `/pages/task/details/detail?uid=${taskId}`
    });
  },
  onReady() {
    // ...
  },
  onShow() {
    // ...
  },
  onHide() {
    // ...
  },
  onUnload() {
    // ...
  },
  onPullDownRefresh() {
    // ...
  },
  onReachBottom() {
    // ...
  },
  onShareAppMessage() {
    // ...
  }
})
