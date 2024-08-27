// pages/auth/login/login.js
var api = require('../../../config/api.js');
var util = require('../../../utils/util.js');
var user = require('../../../utils/user.js');
var app = getApp();
import Toast from 'tdesign-miniprogram/toast/index';
import Message from 'tdesign-miniprogram/message/index';
Page({

  /**
   * 页面的初始数据
   */
  data: {
    loginImage: 'https://miimhub.com/login/login-sure.png?e=1713405183&token=wBkLtV20GRjR14oQ9Y5FRFoXcMn38yeqc8t_qA4Q:FOMnxQ0PROywXKSitHHljqs8Vu8=',
    loginImage2: 'https://miimhub.com/login/login-sure2.png?e=1713405191&token=wBkLtV20GRjR14oQ9Y5FRFoXcMn38yeqc8t_qA4Q:4fS0v9q8zU3JjsBpEpmUcbm3zUc=',
    bgmImage: 'https://miimhub.com/login/loginbgm1.jpg?e=1713405198&token=wBkLtV20GRjR14oQ9Y5FRFoXcMn38yeqc8t_qA4Q:rw48hKx6uM6GgOswF1b3ukfWZ0I=',
    canIUseGetUserProfile: false,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    if (wx.getUserProfile) {
      this.setData({
        canIUseGetUserProfile: true
      })
    }
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

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload() {

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

  },
  getUserProfile(e) {
    Toast({
      context: this,
      selector: '#t-toast',
      message: '加载中...',
      theme: 'loading',
      direction: 'column',
    });
    // 推荐使用wx.getUserProfile获取用户信息，开发者每次通过该接口获取用户个人信息均需用户确认
    // 开发者妥善保管用户快速填写的头像昵称，避免重复弹窗
    wx.getUserProfile({
      desc: '完善用户信息',
      success: (res) => {
        user.checkLogin().catch(() => {
          // 自定义加载图标
          user.loginByWeixin(res.userInfo).then(res => {
            //console.log(res);
            app.globalData.hasLogin = true;
            if (res.data.userInfo.admissionStatus == "1") {
              app.globalData.hasAuth = true;
            }
            wx.navigateBack({
              delta: 1
            })
            Message.success({
              context: this,
              offset: [20, 32],
              duration: 5000,
              content: '登录成功',
            });
          }).catch((err) => {
            console.log(err);
            app.globalData.hasLogin = false;
            Message.error({
              context: this,
              offset: [20, 32],
              duration: 5000,
              content: '微信登录失败',
            });
            //console.log(1);
          });
        });
      },
      fail: (res) => {
        app.globalData.hasLogin = false;
        //util.showErrorToast('微信登录失败');
        //console.log(2);
        Message.error({
          context: this,
          offset: [20, 32],
          duration: 5000,
          content: '微信登录失败',
        });
      }
    });
  },
  wxLogin: function (e) {
    if (e.detail.userInfo == undefined) {
      app.globalData.hasLogin = false;
      //util.showErrorToast('微信登录失败');
      //console.log(3);
      Message.error({
        context: this,
        offset: [20, 32],
        duration: 5000,
        content: '微信登录失败',
      });
      return;
    }
    user.checkLogin().catch(() => {

      user.loginByWeixin(e.detail.userInfo).then(res => {
        app.globalData.hasLogin = true;
        wx.navigateBack({
          delta: 1
        })
      }).catch((err) => {
        app.globalData.hasLogin = false;
        //util.showErrorToast('微信登录失败');
        //console.log(4);
        Message.error({
          context: this,
          offset: [20, 32],
          duration: 5000,
          content: '微信登录失败',
        });
      });

    });
  },
  accountLogin: function () {
    wx.navigateTo({
      url: "/pages/auth/accountLogin/accountLogin"
    });
  }

})