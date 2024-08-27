// pages/ucenter/index/index.js、
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
    userInfo: {
      nickName: '点击登录',
      avatarUrl: 'https://miimhub.com/UI/avatar.png?e=1713403423&token=wBkLtV20GRjR14oQ9Y5FRFoXcMn38yeqc8t_qA4Q:HDbPkUm9bCfvJ7Con6dmWP4JWN0=',
      
    },
    character: {
      head: 'https://miimhub.com/character/gHead.png?e=1713454808&token=wBkLtV20GRjR14oQ9Y5FRFoXcMn38yeqc8t_qA4Q:AOMm1IHk46jxoRygZ9cq9KGhE4c=',
      body: 'https://miimhub.com/character/gBody.png?e=1713454823&token=wBkLtV20GRjR14oQ9Y5FRFoXcMn38yeqc8t_qA4Q:HQuxKj0k10MDYf9ijzTKEUUP06Y=',
      eye: 'https://miimhub.com/character/eye2.png?e=1713454842&token=wBkLtV20GRjR14oQ9Y5FRFoXcMn38yeqc8t_qA4Q:-nBdnqy8s8BlhlneYZ4WspRl1Fc=',
      lefthand: 'https://miimhub.com/character/leftHand.png?e=1713454855&token=wBkLtV20GRjR14oQ9Y5FRFoXcMn38yeqc8t_qA4Q:dAG_Eptb9qzll3FLJK7pW4IFRyQ=',
      righthand: 'https://miimhub.com/character/rightHand.png?e=1713454865&token=wBkLtV20GRjR14oQ9Y5FRFoXcMn38yeqc8t_qA4Q:i6TQxZvFHoyOfwc4q7T4HQ072VQ=',
    },
    closet: [
      { part: 'head', pic: 'https://miimhub.com/character/gHead.png?e=1713454808&token=wBkLtV20GRjR14oQ9Y5FRFoXcMn38yeqc8t_qA4Q:AOMm1IHk46jxoRygZ9cq9KGhE4c=', url: 'https://miimhub.com/character/gHead.png?e=1713454808&token=wBkLtV20GRjR14oQ9Y5FRFoXcMn38yeqc8t_qA4Q:AOMm1IHk46jxoRygZ9cq9KGhE4c=' },
      { part: 'body', pic: 'https://miimhub.com/character/gBody.png?e=1713454823&token=wBkLtV20GRjR14oQ9Y5FRFoXcMn38yeqc8t_qA4Q:HQuxKj0k10MDYf9ijzTKEUUP06Y=', url: 'https://miimhub.com/character/gBody.png?e=1713454823&token=wBkLtV20GRjR14oQ9Y5FRFoXcMn38yeqc8t_qA4Q:HQuxKj0k10MDYf9ijzTKEUUP06Y=' },
      { part: 'body', pic: 'https://miimhub.com/character/gBody2.png?e=1713454875&token=wBkLtV20GRjR14oQ9Y5FRFoXcMn38yeqc8t_qA4Q:qWNCzd4OqlpeX4ZZVva5xobHUos=', url: 'https://miimhub.com/character/gBody.png?e=1713454885&token=wBkLtV20GRjR14oQ9Y5FRFoXcMn38yeqc8t_qA4Q:Xkr7LlkmE03rnZHx6W5OkYb-rE0=' },
      { part: 'lefthand', pic: 'https://miimhub.com/character/leftHand.png?e=1713454916&token=wBkLtV20GRjR14oQ9Y5FRFoXcMn38yeqc8t_qA4Q:yKDJPtEsHiWk3XThh1RYgT7uRz8=', url: 'https://miimhub.com/character/leftHand.png?e=1713454916&token=wBkLtV20GRjR14oQ9Y5FRFoXcMn38yeqc8t_qA4Q:yKDJPtEsHiWk3XThh1RYgT7uRz8=' },
      { part: 'lefthand', pic: 'https://miimhub.com/character/leftHand2.png?e=1713454934&token=wBkLtV20GRjR14oQ9Y5FRFoXcMn38yeqc8t_qA4Q:wRSbrSpWq0RGIVcGtpbflQNOw9w=', url: 'https://miimhub.com/character/leftHand.png?e=1713454916&token=wBkLtV20GRjR14oQ9Y5FRFoXcMn38yeqc8t_qA4Q:yKDJPtEsHiWk3XThh1RYgT7uRz8=' },
      { part: 'lefthand', pic: 'https://miimhub.com/character/leftHand3.png?e=1713454941&token=wBkLtV20GRjR14oQ9Y5FRFoXcMn38yeqc8t_qA4Q:KY83GeTfMBoumG34yOkFnGSj_Fk=', url: 'https://miimhub.com/character/leftHand.png?e=1713454916&token=wBkLtV20GRjR14oQ9Y5FRFoXcMn38yeqc8t_qA4Q:yKDJPtEsHiWk3XThh1RYgT7uRz8=' },
      { part: 'righthand', pic: 'https://miimhub.com/character/rightHand.png?e=1713454952&token=wBkLtV20GRjR14oQ9Y5FRFoXcMn38yeqc8t_qA4Q:8W6OMulo5LIOWFdHoeE6oedZCzY=', url: 'https://miimhub.com/character/rightHand.png?e=1713454952&token=wBkLtV20GRjR14oQ9Y5FRFoXcMn38yeqc8t_qA4Q:8W6OMulo5LIOWFdHoeE6oedZCzY=' },
      { part: 'righthand', pic: 'https://miimhub.com/character/rightHand2.png?e=1713454966&token=wBkLtV20GRjR14oQ9Y5FRFoXcMn38yeqc8t_qA4Q:5wixoTXT2ZApOsiP_RZ-idFNumE=', url: 'https://miimhub.com/character/rightHand.png?e=1713454952&token=wBkLtV20GRjR14oQ9Y5FRFoXcMn38yeqc8t_qA4Q:8W6OMulo5LIOWFdHoeE6oedZCzY=' },
      { part: 'righthand', pic: 'https://miimhub.com/character/rightHand4.png?e=1713454979&token=wBkLtV20GRjR14oQ9Y5FRFoXcMn38yeqc8t_qA4Q:lEXXOGTzRRHrwEyTbsoS63o00F0=', url: 'https://miimhub.com/character/rightHand.png?e=1713454952&token=wBkLtV20GRjR14oQ9Y5FRFoXcMn38yeqc8t_qA4Q:8W6OMulo5LIOWFdHoeE6oedZCzY=' },
      { part: 'eye', pic: 'https://miimhub.com/character/eye2.png?e=1713454995&token=wBkLtV20GRjR14oQ9Y5FRFoXcMn38yeqc8t_qA4Q:oh9B7FWZxbQWiW3bEayvTObyh-w=', url: 'https://miimhub.com/character/eye2.png?e=1713454995&token=wBkLtV20GRjR14oQ9Y5FRFoXcMn38yeqc8t_qA4Q:oh9B7FWZxbQWiW3bEayvTObyh-w=' },
      { part: 'eye', pic: 'https://miimhub.com/character/eye2.png?e=1713455016&token=wBkLtV20GRjR14oQ9Y5FRFoXcMn38yeqc8t_qA4Q:mOJLqYO5PPJiXyIseWWM2v6PN24=', url: 'https://miimhub.com/character/eye2.png?e=1713454995&token=wBkLtV20GRjR14oQ9Y5FRFoXcMn38yeqc8t_qA4Q:oh9B7FWZxbQWiW3bEayvTObyh-w=' },
      { part: 'eye', pic: 'https://miimhub.com/character/eye3.png?e=1713455024&token=wBkLtV20GRjR14oQ9Y5FRFoXcMn38yeqc8t_qA4Q:im7O0ugyeyZRVIC2Wf5OFAbL_9A=', url: 'https://miimhub.com/character/eye2.png?e=1713454995&token=wBkLtV20GRjR14oQ9Y5FRFoXcMn38yeqc8t_qA4Q:oh9B7FWZxbQWiW3bEayvTObyh-w=' },
      { part: 'eye', pic: 'https://miimhub.com/character/eye4.png?e=1713455031&token=wBkLtV20GRjR14oQ9Y5FRFoXcMn38yeqc8t_qA4Q:MyCuiA5M3kFVzHDnsbJxm8O0F5o=', url: 'https://miimhub.com/character/eye2.png?e=1713454995&token=wBkLtV20GRjR14oQ9Y5FRFoXcMn38yeqc8t_qA4Q:oh9B7FWZxbQWiW3bEayvTObyh-w=' },
      // 其他衣橱物品
    ],
    hasLogin: false,
    hasAuth: false,
    MyMenus: [
      { url: "/pages/ucenter/info/info", pic: 'https://miimhub.com/pageUI/medical-record.png?e=1713455056&token=wBkLtV20GRjR14oQ9Y5FRFoXcMn38yeqc8t_qA4Q:sgoKNnk9YFoeaaBeg81rb1shV6Y=', name: "基本信息" },
      { url: "/pages/ucenter/pointsexchange/pointexchange", pic:'https://miimhub.com/pageUI/presents.png?e=1713455073&token=wBkLtV20GRjR14oQ9Y5FRFoXcMn38yeqc8t_qA4Q:vBG4oPWB-RkIVX3el--N743xG_4=', name: "兑换礼品" },
      { url: "/pages/ucenter/auth/auth", pic:"https://miimhub.com/pageUI/authentication.png?e=1713455084&token=wBkLtV20GRjR14oQ9Y5FRFoXcMn38yeqc8t_qA4Q:sKUU7jk01H6OoXJrB3hrKaqlAm0=",name:"身份认证"},
      { url: "/pages/index/all_tasks/all_tasks", pic:'https://miimhub.com/pageUI/to-do-list.png?e=1713455092&token=wBkLtV20GRjR14oQ9Y5FRFoXcMn38yeqc8t_qA4Q:-uIPaY6fgwKdWNvtACw89pvoHos=', name: "你的任务" },
      // { url: "", pic: 'http://sa9lkj8tk.hn-bkt.clouddn.com/%E6%B5%8B%E8%AF%95%E7%81%B02?e=1710319667&token=wBkLtV20GRjR14oQ9Y5FRFoXcMn38yeqc8t_qA4Q:idxXg8f9sB3PtxyGW1-kFJ8pNCk=', name: "其他" },
      // { url: "", pic: 'http://sa9lkj8tk.hn-bkt.clouddn.com/%E6%B5%8B%E8%AF%95%E7%81%B02?e=1710319667&token=wBkLtV20GRjR14oQ9Y5FRFoXcMn38yeqc8t_qA4Q:idxXg8f9sB3PtxyGW1-kFJ8pNCk=', name: "问题反馈" }
      ],
  },
  changePart: function(e) {
    const part = e.currentTarget.dataset.part;
    const pic = e.currentTarget.dataset.pic;
  
    // 更新人物部位的图片
    this.setData({
      [`character.${part}`]: pic,
    });
  },
  
  /**
   * 页面跳转
  */
 goPages:function(e){
  //  console.log(e);
    console.log("登录状态为" + this.data.hasLogin);
    console.log("认证状态为" + this.data.hasAuth);
    if (!(this.data.hasLogin)) {
      wx.navigateTo({
        url: "/pages/auth/login/login"
      });
    }
    if(app.globalData.hasAuth && e.currentTarget.dataset.url == "/pages/ucenter/auth/auth"){
      Message.warning({
        context: this,
        offset: [20, 32],
        duration: 5000,
        content: '您已认证成功',
      });
      return;
    }
    if(!app.globalData.hasAuth && e.currentTarget.dataset.url == "/pages/ucenter/info/info"){
      wx.navigateTo({
        url: "/pages/ucenter/auth/auth"
      });
    }
    wx.navigateTo({
      url: e.currentTarget.dataset.url
    });
  },
  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {
    if(app.globalData.hasAuth){
      this.getUserPoints();
    }
  //获取用户的登录信息
    if (app.globalData.hasLogin) {
      let userInfo = wx.getStorageSync('userInfo');
      //console.log(userInfo);
      this.setData({
        userInfo: userInfo,
        hasLogin: true
      });
    if(app.globalData.hasAuth){
      this.setData({
        hasAuth: true
      })
    }
  }
  },
  goLogin() {
    if (!this.data.hasLogin) {
      wx.navigateTo({
        url: "/pages/auth/login/login"
      });
    }
  },
  getUserPoints(){
    let userInfo = wx.getStorageSync('userInfo');
    var userId = userInfo.userId;
    wx.request({
      url: api.WxGetUserPoint,
      data:{
        userId: userId
      },
      method: 'GET',
      header: {
        'content-type': 'application/json'
      },
      success: function(res){
        if(res.data.code == 200){
          //console.log(res);
          userInfo.points = res.data.data;
          wx.setStorageSync('userInfo', userInfo);
        }
        else{
          Message.error({
            context: this,
            offset: [20, 32],
            duration: 3000,
            content: res.data.msg,
          });
        }
      }
    })
  }
})