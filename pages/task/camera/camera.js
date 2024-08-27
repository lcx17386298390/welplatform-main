// pages/task/camera/camera.js
var api = require('../../../config/api')
Page({

  /**
   * 页面的初始数据
   */
  data: {
    task:{},
    status:'',
    uid: '',
    userId: '',
    statusTimer: null, // 用于保存定时器
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    var that = this;
    var userId = wx.getStorageSync('userInfo').userId;
    that.setData({
      uid: options.uid,
      userId: userId
    })
    this.GetStatus();
  },

  GetStatus(){
    var that = this;
    var uid = that.data.uid;
    var userId = that.data.userId;
    wx.request({
      url: api.WxGetTaskInfo,
      data: {
          taskId: uid,
          userId: userId
      },
      method: 'GET',
      header: {
        'content-type': 'application/json'
      },
      success:function(res){
        //console.log(res);
        that.setData({
            task: res.data.data.task,
            status: res.data.data.status
        });
        console.log("当前任务状态" + that.data.status);
      }
    })
  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {
    var that = this;
    // 设置定时器，每隔3秒执行一次GetStatus
    that.statusTimer = setInterval(function () {
      that.GetStatus();
    }, 3 * 1000);
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide() {
    // 页面隐藏时清除定时器
    if (this.statusTimer) {
      clearInterval(this.statusTimer);
      this.statusTimer = null;
    }
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload() {
    // 页面卸载时清除定时器
    if (this.statusTimer) {
      clearInterval(this.statusTimer);
      this.statusTimer = null;
    }
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {

  }
})