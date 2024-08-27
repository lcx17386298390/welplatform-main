var app = getApp();
var amapFile = require('../../libs/amap-wx.js');
Page({
  data: {
    latitude: null,
    longitude: null,
    markers: [],
    distance: '',
    polyline: [],
    idList:[]
  },
  onLoad: function (options) {
    var _this = this;
    _this.setData({
      longitude: app.globalData.longitude,
      latitude: app.globalData.latitude
    })
    _this.routing(options);
    wx.getFuzzyLocation({
      type: 'gcj02',
      success: function (res) {
        app.globalData.latitude = res.latitude;
        app.globalData.longitude = res.longitude;
        _this.setData({
          latitude: res.latitude,
          longitude: res.longitude
        });
        _this.routing(options);
      },
      fail: function () {
        console.log("定位失败")
        wx.showModal({
          title: '无法使用该功能',
          content: '请点击右上角在“关于校园导览”设置中给予定位权限',
          showCancel: false,
          success: function (res) {
            wx.navigateBack({
              delta: 1
            })
            return;
          }
        })
      }
    })
  },
  routing: function (options){
    var _this = this;
    let distance = Math.abs(_this.data.longitude - options.longitude) + Math.abs(_this.data.latitude - options.latitude)
    console.log(distance);
    var myAmapFun = new amapFile.AMapWX({ key: require('../../config.js').key });
    let routeData = {
      origin: options.longitude + ',' + options.latitude,
      destination: _this.data.longitude + ',' + _this.data.latitude,
      success: function (data) {
        var points = [];
       // console.log(data);
        if (data.paths && data.paths[0] && data.paths[0].steps) {
          var steps = data.paths[0].steps;
          for (var i = 0; i < steps.length; i++) {
            var poLen = steps[i].polyline.split(';');
            for (var j = 0; j < poLen.length; j++) {
              points.push({
                longitude: parseFloat(poLen[j].split(',')[0]),
                latitude: parseFloat(poLen[j].split(',')[1])
              })
            }
          }
        }
        _this.setData({
          markers: [{
            id: "1",
            "width": "25",
            "height": "35",
            iconPath: "/static/images/mapicon_end.png",
            latitude: options.latitude,
            longitude: options.longitude
          }, {
            id: "2",
            "width": "25",
            "height": "35",
            iconPath: "/static/images/mapicon_start.png",
            latitude: _this.data.latitude,
            longitude: _this.data.longitude
          }],
          polyline: [{
            points: points,
            color: "#0091ff",
            width: 6
          }]
        });
        if (data.paths[0] && data.paths[0].distance) {
          _this.setData({
            distance: data.paths[0].distance + '米'
          });
        }
      },
      fail: function (info) {
      }
    }
    if (distance < 0.85) {
      // getWalkingRoute 步行
      myAmapFun.getWalkingRoute(routeData)
    } else {
      // getDrivingRoute 驾车
      myAmapFun.getDrivingRoute(routeData)
    }
  }
})