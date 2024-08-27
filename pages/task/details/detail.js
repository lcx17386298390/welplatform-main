// pages/task/details/detail.js
var api = require('../../../config/api')
Page({

  /**
   * 页面的初始数据
   */
  data: {
    task:{},
    taskType:{},
    status:''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
      var that = this;
      var uid = options.uid;
      //console.log(options);
      var userId = wx.getStorageSync('userInfo').userId;
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
        }
      })

      //获取任务类型
      wx.request({
        url: api.WxGetTaskType,
        data: {
            taskId: uid
        },
        method: 'GET',
        header: {
          'content-type': 'application/json'
        },
        success:function(res){
          //console.log(res);
          that.setData({
            taskType: res.data.data
          });
        //console.log(that.data.taskType);
        }
        })
  },

})