// pages/auth/accountLogin/accountLogin.js
var api = require('../../../config/api.js');
var util = require('../../../utils/util.js');
var user = require('../../../utils/user.js');
var app = getApp();
import Toast from 'tdesign-miniprogram/toast/index';
Page({

  /**
   * 页面的初始数据
   */
  data: {
    username: '',
    password: '',
    code: '',
    loginErrorCount: 0,
    loginBtnClass: ''
  },

  bindUsernameInput: function(e) {

    this.setData({
      username: e.detail.value
    });
  },
  bindPasswordInput: function(e) {

    this.setData({
      password: e.detail.value
    });
  },
  bindCodeInput: function(e) {

    this.setData({
      code: e.detail.value
    });
  },
  clearInput: function(e) {
    switch (e.currentTarget.id) {
      case 'clear-username':
        this.setData({
          username: ''
        });
        break;
      case 'clear-password':
        this.setData({
          password: ''
        });
        break;
      case 'clear-code':
        this.setData({
          code: ''
        });
        break;
    }
  },
  accountLogin: function() {
    var that = this;

    if (this.data.password.length < 1 || this.data.username.length < 1) {
      wx.showModal({
        title: '错误信息',
        content: '请输入用户名和密码',
        showCancel: false
      });
      return false;
    }
    
    wx.request({
      url: api.AuthLoginByAccount,
      data: {
        username: that.data.username,
        password: that.data.password
      },
      method: 'POST',
      header: {
        'content-type': 'application/json'
      },
      success: function(res) {
        console.log(res);
        if (res.data.code == 200) {
          // Toast({
          //   context: this,
          //   selector: '#t-toast',
          //   message: '登录成功',
          //   theme: 'success',
          //   direction: 'column',
          // });
          that.setData({
            loginErrorCount: 0
          });
          app.globalData.hasLogin = true;
          if(res.data.data.userInfo.admissionStatus == "1"){
            app.globalData.hasAuth = true;
          }
          wx.setStorageSync('userInfo', res.data.data.userInfo);
          wx.setStorage({
            key: "token",
            data: res.data.data.token,
            success: function() {
              wx.switchTab({
                url: '/pages/ucenter/index/index'
              });
            }
          });
        } else {
          that.setData({
            loginErrorCount: that.data.loginErrorCount + 1
          });
          app.globalData.hasLogin = false;
          //util.showErrorToast('账户登录失败');
          Toast({
            context: this,
            selector: '#t-toast',
            message: '登录失败',
            theme: 'error',
            direction: 'column',
          });
        }
      }
    });
  }
})