// pages/auth/register/register.js
var api = require('../../../config/api.js');
var check = require('../../../utils/check.js');
import Message from 'tdesign-miniprogram/message/index';
var app = getApp();
var util = require('../../../utils/util.js');
Page({

  /**
   * 页面的初始数据
   */
  data: {
    username: '',
    password: '',
    confirmPassword: '',
    email: '',
    code: ''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {

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
  sendCode: function() {
    let that = this;

    if (this.data.email.length == 0) {
      Message.error({
        context: this,
        offset: [20, 32],
        duration: 5000,
        content: '邮箱不能为空',
      });
      return false;
    }

    if (!check.isValidEmail(this.data.email)) {
      // wx.showModal({
      //   title: '错误信息',
      //   content: '邮箱输入不正确',
      //   showCancel: false
      // });
      Message.error({
        context: this,
        offset: [20, 32],
        duration: 5000,
        content: '邮箱输入不正确',
      });
      return false;
    }

    wx.request({
      url: api.AuthRegisterCaptcha,
      data: {
        email: this.data.email
      },
      method: 'POST',
      header: {
        'content-type': 'application/json'
      },
      success: function(res) {
        console.log(res);
        if (res.data.code == 200) {
          // wx.showModal({
          //   title: '发送成功',
          //   content: '验证码已发送',
          //   showCancel: false
          // });
          Message.success({
            context: this,
            offset: [20, 32],
            duration: 5000,
            content: '验证码已发送',
          });
        } else {
          // wx.showModal({
          //   title: '错误信息',
          //   content: res.data.msg,
          //   showCancel: false
          // });
          Message.error({
            context: this,
            offset: [20, 32],
            duration: 5000,
            content: res.data.msg,
          });
        }
      }
    });
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
  bindConfirmPasswordInput: function(e) {

    this.setData({
      confirmPassword: e.detail.value
    });
  },
  bindEmailInput: function(e) {

    this.setData({
      email: e.detail.value
    });
    //console.log(this.data.email);
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
      case 'clear-confirm-password':
        this.setData({
          confirmPassword: ''
        });
        break;
      case 'clear-mobile':
        this.setData({
          email: ''
        });
        break;
      case 'clear-code':
        this.setData({
          code: ''
        });
        break;
    }
  },
  startRegister: function() {
    var that = this;

    if (this.data.password.length < 6 || this.data.username.length < 6) {
      // wx.showModal({
      //   title: '错误信息',
      //   content: '用户名和密码不得少于6位',
      //   showCancel: false
      // });
      Message.error({
        context: this,
        offset: [20, 32],
        duration: 5000,
        content: '用户名和密码不少于6位',
      });
      return false;
    }

    if (this.data.password != this.data.confirmPassword) {
      // wx.showModal({
      //   title: '错误信息',
      //   content: '确认密码不一致',
      //   showCancel: false
      // });
      Message.error({
        context: this,
        offset: [20, 32],
        duration: 5000,
        content: '确认密码不一致',
      });
      return false;
    }

    if (this.data.email.length == 0 || this.data.code.length == 0) {
      // wx.showModal({
      //   title: '错误信息',
      //   content: '邮箱和验证码不能为空',
      //   showCancel: false
      // });
      Message.error({
        context: this,
        offset: [20, 32],
        duration: 5000,
        content: '邮箱和验证码不能为空',
      });
      return false;
    }

    if (!check.isValidEmail(this.data.email)) {
      // wx.showModal({
      //   title: '错误信息',
      //   content: '邮箱输入不正确',
      //   showCancel: false
      // });
      Message.error({
        context: this,
        offset: [20, 32],
        duration: 5000,
        content: '邮箱输入不正确',
      });
      return false;
    }

    wx.login({
      success: function(res) {
        console.log(res);
        if (!res.code) {
          // wx.showModal({
          //   title: '错误信息',
          //   content: '注册失败',
          //   showCancel: false
          // });
          Message.error({
            context: this,
            offset: [20, 32],
            duration: 5000,
            content: '用户已登录',
          });
        }
        that.requestRegister(res.code);
      }
    });
  },
  requestRegister: function(wxCode) {
    let that = this;
    wx.request({
      url: api.AuthRegister,
      data: {
        username: that.data.username,
        password: that.data.password,
        email: that.data.email,
        code: that.data.code,
        wxCode: wxCode
      },
      method: 'POST',
      header: {
        'content-type': 'application/json'
      },
      success: function(res) {
        console.log(res);
        if (res.data.code == 200) {
          app.globalData.hasLogin = true;
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
          // wx.showModal({
          //   title: '错误信息',
          //   content: res.data.msg,
          //   showCancel: false
          // });
          Message.error({
            context: this,
            offset: [20, 32],
            duration: 5000,
            content: 'res.data.msg',
          });
        }
      }
    });
  }

})