var markersData = [];
var amapFile = require('../../libs/amap-wx.130.js');
import {Message} from 'tdesign-miniprogram/message/index';
var app = getApp();
var api = require('../../config/api')
var user = require('../../utils/user')
Page({
  data: {
    items: [null, null, null],
    value: '',
    menuItems: [
    {text: '校园导航' , imgSrc: 'https://miimhub.com/pageUI/daohang.png?e=1713454220&token=wBkLtV20GRjR14oQ9Y5FRFoXcMn38yeqc8t_qA4Q:ISeWswIxqiZtzAo_wAug-Yq1ygU=',url:"/pages/map/index"},
    {text: '校园简介' , imgSrc: 'https://miimhub.com/pageUI/campus.png?e=1713454227&token=wBkLtV20GRjR14oQ9Y5FRFoXcMn38yeqc8t_qA4Q:8a16WLUToquri_NjOC7B0yFcY4A=',url:"/pages/map/details"},
    {text: '积分排行榜' , imgSrc: 'https://miimhub.com/pageUI/rank.png?e=1713454235&token=wBkLtV20GRjR14oQ9Y5FRFoXcMn38yeqc8t_qA4Q:dzXaMqIWwtHVqi_CvIjxvruo4yI=',url:"/pages/index/ranking/ranking"},
    ],
    hasLogin: false,
    hasAuth: false,
    images: app.globalData.introduce.images,
    shortName: app.globalData.introduce.shortName,
    mapCopyright: app.globalData.introduce.mapCopyright,
    imgCDN: app.imgCDN,
    current: 0, // 注意，TDesign 的 current 是从 0 开始的
    autoplay: true,
    duration: 500,
    interval: 5000,
    userInfo: {},
    swiperList: [
      {
        value: 'https://miimhub.com/UI/bb.png?e=1713402169&token=wBkLtV20GRjR14oQ9Y5FRFoXcMn38yeqc8t_qA4Q:jf-eTDF5TaDYlsYJtQU82CVoE_U=',
      },
      {
        value: 'https://miimhub.com/UI/bb.png?e=1713402169&token=wBkLtV20GRjR14oQ9Y5FRFoXcMn38yeqc8t_qA4Q:jf-eTDF5TaDYlsYJtQU82CVoE_U=',
      },
      {
        value: 'https://miimhub.com/UI/bb.png?e=1713402169&token=wBkLtV20GRjR14oQ9Y5FRFoXcMn38yeqc8t_qA4Q:jf-eTDF5TaDYlsYJtQU82CVoE_U=',
      },
    ],
    userTaskList:[],
    top3UserTasks:[]
  },
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
  imageLoadError(e) {
      console.log('图片加载失败', e);
  },
  navigateToTask() {
      wx.navigateTo({
        url: '/pages/index/all_tasks/all_tasks'
      });
  },
  navigateToPage(e){
    const url = e.currentTarget.dataset.url;
    wx.navigateTo({
      url: url   //去完成对应任务
    });
  },
  onLoad: function() {
    // 页面加载时的逻辑
    // wx.setNavigationBarTitle({
    //   title: app.globalData.introduce.name
    // })
    this.loadUserTask();
  },
  loadUserTask:function(){
    var that = this;
    var hasAuth = app.globalData.hasAuth;
    var hasLogin = app.globalData.hasLogin;
    if(hasAuth && hasLogin){
      var uid = wx.getStorageSync('userInfo').userId;
      wx.request({
        url: api.WxGetUserTaskList,
        data: {
            uid: uid
        },
        method: 'GET',
        header: {
          'content-type': 'application/json'
        },
        success:function(res){
          //console.log(res);

          that.setData({
            userTaskList: res.data.data,
            top3UserTasks: res.data.data.slice(0, 3) // 更新前3个任务
          });
          //console.log(that.data.userTaskList);
          
        }
        }
      )
    }
  },
  onShow: function(){
    //获取用户的登录信息
    if (app.globalData.hasLogin) {
      let userInfo = wx.getStorageSync('userInfo');
      //console.log(userInfo.userId);
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
  console.log("登录" + app.globalData.hasLogin + "认证" + app.globalData.hasAuth);
  this.loadUserTask();
  },

  onChange(e) {
    console.log(e.detail.value);
  },
 
  onShareAppMessage: function (res) {
    if (res.from === 'button') {
      // 来自页面内转发按钮
      console.log(res.target)
    }
    return {
      title: app.globalData.introduce.name + ' - 校园导览',
      path: '/pages/index',
      imageUrl: app.imgCDN + app.globalData.introduce.share,
      success: function (res) {
        // 转发成功
      },
      fail: function (res) {
        // 转发失败
      }
    }
  },
  // 点击跳转积分排行榜页面
  navigateToRankingPage: function() {
    wx.navigateTo({
      url: '/pages/index/ranking/ranking'
    });
  },

    // 点击跳转搜索地图页面
    navigateToSearchPage: function() {
      wx.navigateTo({
        url: '/pages/map/search'
      });
    }
})
