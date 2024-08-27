// pages/ucenter/pointsexchange/pointsexchange.js
var api = require('../../../config/api.js');

Page({

  /**
   * 页面的初始数据
   */
  data: {
    giftList: [],
    backgroundImageUrl_1: 'https://miimhub.com/pointsexchange/1.png?e=1713455230&token=wBkLtV20GRjR14oQ9Y5FRFoXcMn38yeqc8t_qA4Q:o5jnk3Rv6csNMO1yduPgjp_PEv0=',
    backgroundImageUrl_2: 'https://miimhub.com/pointsexchange/2.png?e=1713455235&token=wBkLtV20GRjR14oQ9Y5FRFoXcMn38yeqc8t_qA4Q:1Y5lCMiWjb1hs31_YPz4eF_kShs=',
    backgroundImageUrl_4: 'https://miimhub.com/pointsexchange/4.png?e=1713455241&token=wBkLtV20GRjR14oQ9Y5FRFoXcMn38yeqc8t_qA4Q:tSoOZXmoMkS7qk9i5Mb2gCIkjW8=',
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    this.getGiftList();
  },
  navigateToPaymentPage:function(event) {
        // 获取点击的礼物的 giftId
        var giftId = event.currentTarget.dataset.giftid;
        // console.log('-------------------', event.currentTarget.dataset.giftid);
        wx.navigateTo({
      url: '/pages/ucenter/pointsexchange/payments/payments?giftId=' + giftId,
    });
  },
  getGiftList(){
    var that = this;
    wx.request({
      url: api.WxGetGiftList,
      method:'GET',
      header: {
        'content-type': 'application/json'
      },
      success:function(res){
        // console.log('-------------------');
        // console.log(res.data.rows);
        // console.log('-------------------');
        that.setData({
          giftList: res.data.rows 
        })
        // console.log(that.data.giftList); 
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

  }
})