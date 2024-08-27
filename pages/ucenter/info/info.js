// pages/ucenter/info/info.js
var api = require('../../../config/api.js');
var util = require('../../../utils/util.js');
var user = require('../../../utils/user.js');
var app = getApp();
import Message from 'tdesign-miniprogram/message/index';
Page({

  /**
   * 页面的初始数据
   */
  data: {
    userInfoList: [],
    userInfo: {},
    hasLogin: false,
    avatarUrl: '',
    nickname: ''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    var that = this;
    let userInfo = wx.getStorageSync('userInfo');
    var userId = userInfo.userId;
    // 假设你有一个后端API从哪里你可以获取用户信息
    wx.request({
      url: api.WxGetUserInfo, // 你的后端API URL
      data: {
        userId: userId
      },
      method: 'POST',
      success: function (res) {
        console.log(res);

        if (res.data.code == 200) {
          let users = res.data.data;
          let userInfoArray = Object.keys(users).map(key => {
            return {
              name: key,
              detail: users[key]
            };
          });
          that.setData({
            userInfoList: userInfoArray
          });
          // that.setData({
          //   userInfoList: res.data.data
          // })
        }

      }

    });
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
    let that = this;
    //获取用户的登录信息
    let userInfo = wx.getStorageSync('userInfo');
    console.log(userInfo);
    this.setData({
      userInfo: userInfo,
      hasLogin: true,
      avatarUrl: userInfo.avatarUrl,
      nickname: userInfo.nickName
    });
  },
  /**
   * 退出登录
   */
  exitLogin: function () {
    wx.showModal({
      title: '',
      confirmColor: '#b4282d',
      content: '退出登录？',
      success: function (res) {
        if (!res.confirm) {
          return;
        }

        util.request(api.AuthLogout, {}, 'POST');
        app.globalData.hasLogin = false;
        app.globalData.hasAuth = false;
        wx.removeStorageSync('token');
        wx.removeStorageSync('userInfo');
        wx.reLaunch({
          url: '/pages/index/index'
        });
      }
    })
  },
  onChooseAvatar(e) {
    var that = this
    const {
      avatarUrl
    } = e.detail
    //console.log(avatarUrl);
    let cloudPath = `${Date.now()}-${Math.floor(Math.random(0, 1) * 10000000)}.png`;
    wx.cloud.uploadFile({
      cloudPath,
      filePath: avatarUrl
    }).then((res) => {
      console.log(res);
      let fileID = res.fileID;
      var fileList = new Array();
      fileList[0] = fileID;
      wx.cloud.getTempFileURL({
        fileList: fileList,
        success: res => {
          this.setData({
            avatarUrl: res.fileList[0].tempFileURL
          })
        }
      })
      // wx.showToast({
      //   title: '上传图片成功',
      //   icon: 'success'
      // })
      Message.success({
        context: this,
        offset: [20, 32],
        duration: 5000,
        content: '上传图片成功',
      });
    }).catch(() => {
      wx.hideLoading()
      // wx.showToast({
      //   title: '上传图片错误',
      //   icon: 'error'
      // })
      Message.error({
        context: this,
        offset: [20, 32],
        duration: 5000,
        content: '上传图片错误',
      });
    })
  },
  saveData(e) {
    var that = this
    var userId = wx.getStorageSync('userInfo').userId;
    this.setData({
      nickname: e.detail.value.nickname
    })
    // Toast.loading({
    //   mask: true,
    //   message: '保存中...'
    // });
    wx.request({
      url: api.AuthSaveData,
      data: {
        nickName: that.data.nickname,
        avatarUrl: that.data.avatarUrl,
        userId: userId
      },
      method: 'POST',
      header: {
        'content-type': 'application/json'
      },
      success: function (res) {
        console.log(res);
        if (res.data.code == 200) {
          // wx.showToast({
          //   title: '保存成功',
          //   icon: 'success'
          // })
          Message.success({
            context: this,
            offset: [20, 32],
            duration: 5000,
            content: '保存成功',
          });
          let userInfo = wx.getStorageSync('userInfo');
          userInfo.avatarUrl = that.data.avatarUrl;
          userInfo.nickName = that.data.nickname;
          //console.log(userInfo.nickName);
          wx.setStorageSync('userInfo', userInfo);
          wx.switchTab({
            url: '/pages/ucenter/index/index'
          });
        } else {
          util.showErrorToast(res.data.msg);
        }
      }
    });
  },
  bindNickNameInput: function (e) {
    this.setData({
      nickname: e.detail.value
    });
  }
})