// pages/ucenter/pointsexchange/payments.js
var api = require('../../../../config/api.js');
Page({

  /**
   * 页面的初始数据
   */
  data: {
    gift: [],
    userGift: {},
    userInfo: {}, // 保存用户信息
    userAddress: '', // 用户地址输入框的值
    remark: '', //用户备注
    giftId: '', // 添加 giftId 到页面数据中
    showImage: true,
    showInput: false, // 控制收货地址输入框显示和隐藏的变量
  },

  toggleInput: function () {
    this.setData({
      showImage: !this.data.showImage,
      showInput: !this.data.showInput
    });
  },

  stopPropagation: function (event) {
    // 阻止事件冒泡
    return false;
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    // 获取传递过来的 giftId
    var giftId = options.giftId;
    // 获取本地缓存中的用户信息
    var userInfo = wx.getStorageSync('userInfo');
    console.log('获取到的用户信息：', userInfo);
    this.setData({
      userInfo: userInfo,
    });
    // console.log('-------------------', giftId);
    this.getGift(giftId);
    this.setData({
      giftId: giftId,
    });
  },
  getGift(giftId) {
    var that = this;
    wx.request({
      url: api.WxGetGiftId + '/' + giftId,
      method: 'GET',
      header: {
        'content-type': 'application/json'
      },
      success: function (res) {
        // console.log(res.data.data);
        that.setData({
          gift: res.data.data
        })
        console.log(that.data.gift);
      }
    });
  },
  // 提交表单
  formSubmit: function (e) {
    var formData = e.detail.value;
    console.log(formData);
    var giftId = this.data.giftId; // 获取页面数据中的 giftId
    var userInfo = this.data.userInfo; // 获取页面数据中的 userInfo
    var userGift = {}; // 创建用户礼物对象
    userGift.giftId = giftId;
    userGift.userId = userInfo.userId;
    userGift.userAddress = formData.address; // 将用户地址添加到用户礼物对象中
    userGift.remark = formData.remark; // 将备注信息添加到用户礼物对象中
    wx.request({
      url: api.WxAddUserGift,
      method: 'POST',
      data: userGift,
      header: {
        'content-type': 'application/json'
      },
      success: function (res) {
        // 请求成功的处理逻辑
        console.log(res);
        // 显示兑换成功弹窗
        wx.showToast({
          title: '兑换成功',
          icon: 'success',
          duration: 2000 // 弹窗持续时间
        });
      },
      fail: function (error) {
        console.log(error);
      }
    })
  }
})