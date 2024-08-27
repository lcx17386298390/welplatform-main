// location_check_in/location_check_in.js
const util = require('../../../utils/util')
const app = getApp()
const api = require("../../../config/api")  // 根据实际项目自己配置
import Message from 'tdesign-miniprogram/message/index';
import Toast from 'tdesign-miniprogram/toast/index';
// 实例化API核心类
const qqmapsdk = app.globalData.qqmapsdk

Page({

  /**
   * 页面的初始数据
   */
  data: {
    markers: '',
    poi: {
      latitude: '',
      longitude: ''
    },
    addressName: '',
    time: '',
    timer: '',
    timer2: '',  // 用来每个一段时间自动刷新一次定位
    canClick: true,
    task:{},
    taskType:{}
  },
 
  getAddress(e) {
    var that = this;
    qqmapsdk.reverseGeocoder({
      //位置坐标，默认获取当前位置，非必须参数
      // 成功后的回调
      success: function(res) {
        // console.log(res);
        that.setData({
          addressName: res.result.address
        })
        var res = res.result;
        var mks = [];
        //当get_poi为0时或者为不填默认值时，检索目标位置，按需使用
        mks.push({ // 获取返回结果，放到mks数组中
          title: res.address,
          id: 0,
          latitude: res.location.lat,
          longitude: res.location.lng,
          iconPath: '/static/images/zcxj/myPosition.png', // 图标路径
          width: 21,
          height: 28,
        });
        that.setData({ // 设置markers属性和地图位置poi，将结果在地图展示
          markers: mks,
          poi: {
            latitude: res.location.lat,
            longitude: res.location.lng
          }
        });
      },
      fail: function(error) {
        console.error(error);
      },
      complete: function(res) {
        //console.log(res);
      }
    })
  },
  getTime: function () {
    let that = this
    let time = that.data.time
    that.setData({
      timer: setInterval(function () {
        time = util.formatTime(new Date())
        that.setData({
          time: time.substr(-8)
        });
        if (time == 0) {
          // 页面跳转后，要把定时器清空掉，免得浪费性能
          clearInterval(that.data.timer)
        }
      }, 1000)
    })
  },
  rePosition: function () {
    //console.log('用户点了重新定位')
    Message.success({
      context: this,
      offset: [20, 32],
      duration: 5000,
      content: '重新定位成功',
    });

    this.getAddress()
  },
  checkIn: function () {
    this.setData({
      canClick: false
    })
    //console.log('用户点击了签到')

    var that = this
    var nowTime = util.formatTime(new Date())
    wx.showModal({
      title: '请确认打卡信息',
      // content: '请确认待整改项已整改完毕！',
      content: `地点：${this.data.addressName}\n时间：${nowTime}`,  // 开发者工具上没有换行，真机调试时会有的
      confirmText: '确认',
      success (res) {
        if (res.confirm) {
          //console.log('用户点击确定')
          // 调起签到接口
          that.realyCheckIn()
          
        } else if (res.cancel) {
          //console.log('用户点击取消')
          that.setData({
            canClick: true
          })
        }
      }
    })
  },
  realyCheckIn: function() {
    var that = this
    var userId = wx.getStorageSync('userInfo').userId;
    //console.log(that.data.poi);
    //检测是否匹配地点经度纬度

    wx.request({
      url: api.WxTaskPosFinish,
      data: {
        latitude: that.data.poi.latitude,
        longitude: that.data.poi.longitude,
        taskId: that.data.task.uid,
        userId: userId
      },
      method: "POST",
      header: {
        'content-type': 'application/json'
      },
      success: function (res) {
        console.log(res)
        if(res.data.code == 200){

          that.setData({
            canClick: false
          })

          // wx.navigateBack({
          //   delta: 2
          // })
        }else{
          Toast({
            context: this,
            selector: '#t-toast',
            message: res.data.msg,
            theme: 'error',
            direction: 'column',
          });
        }
      },
      fail: function(res) {
        that.setData({
          canClick: true
        })
      },
      complete: function () {
        wx.showToast({
          title: '打卡成功！',
          icon: 'success',
          duration: 2000,
          complete: function(){
            wx.navigateBack({
              delta: 2  // 回退两层页面
            })
          }
        })
      }
    })

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this
    that.getTime()
    that.getAddress()
    that.setData({
      canClick: true, // 允许用户点击，防止多次提交
      timer2: setInterval(function () {
        that.getAddress()
      }, 20000)  // 每20秒刷新一次定位
    })


    var uid = options.uid;
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
        //console.log(res.data.data);
        that.setData({
            task: res.data.data.task
        });
      }
    })

  },

})