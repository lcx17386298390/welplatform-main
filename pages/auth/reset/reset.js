// pages/auth/reset/reset.js
var api = require('../../../config/api.js');
var check = require('../../../utils/check.js');
import Message from 'tdesign-miniprogram/message/index';
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    email: '',
    code: '',
    password: '',
    confirmPassword: ''
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
  startReset(){
    var that = this;
    if (this.data.email.length == 0) {
      // wx.showModal({
      //   title: '错误信息',
      //   content: '邮箱不能为空',
      //   showCancel: false
      // });
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
    
    if (this.data.password.length < 1 || this.data.confirmPassword.length < 1) {
      // wx.showModal({
      //   title: '错误信息',
      //   content: '请输入密码',
      //   showCancel: false
      // });
      Message.error({
        context: this,
        offset: [20, 32],
        duration: 5000,
        content: '请输入密码',
      });
      return false;
    }

    if(this.data.password !== this.data.confirmPassword){
      // wx.showModal({
      //   title: '错误信息',
      //   content: '两次输入的密码不一致',
      //   showCancel: false
      // });
      Message.error({
        context: this,
        offset: [20, 32],
        duration: 5000,
        content: '两次输入的密码不一致',
      });
      return false;
    }
    wx.request({
      url: api.AuthResetPwd,
      data: {
        email: this.data.email,
        code: this.data.code,
        password: this.data.password
      },
      method: 'POST',
      header: {
        'content-type': 'application/json'
      },
      success: function(res) {
        console.log(res);
        if (res.data.code == 200) {
         
          // wx.switchTab({
          //   url: '/pages/auth/accountLogin/accountLogin'
          // });
          wx.navigateBack({
            delta:1,
          })
          
          // wx.showModal({
          //   title: '成功',
          //   content: '重置成功',
          //   showCancel: false
          // });
          Message.success({
            context: this,
            offset: [20, 32],
            duration: 5000,
            content: '重置成功',
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
  sendCode(){
    let that = this;

    if (this.data.email.length == 0) {
      // wx.showModal({
      //   title: '错误信息',
      //   content: '邮箱不能为空',
      //   showCancel: false
      // });
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
            content:  res.data.msg,
          });
        }
      }
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
  },
  bindCodeInput: function(e) {

    this.setData({
      code: e.detail.value
    });
  },
  clearInput: function(e) {
    switch (e.currentTarget.id) {
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
  }
})