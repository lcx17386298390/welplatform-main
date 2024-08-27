/**
 * 用户相关服务
 */
const util = require('../utils/util.js');
const api = require('../config/api.js');
import Toast from 'tdesign-miniprogram/toast/index';
var app = getApp();

/**
 * Promise封装wx.checkSession
 */
function checkSession() {
  return new Promise(function(resolve, reject) {
    wx.checkSession({
      success: function() {
        resolve(true);
      },
      fail: function() {
        reject(false);
      }
    })
  });
}

/**
 * Promise封装wx.login
 */
function login() {
  return new Promise(function(resolve, reject) {
    wx.login({
      success: function(res) {
        //console.log(res);
        if (res.code) {
          resolve(res);
        } else {
          reject(res);
        }
      },
      fail: function(err) {
        reject(err);
      }
    });
  });
}

/**
 * 调用微信登录
 */
function loginByWeixin(userInfo) {
  let shareUserId = wx.getStorageSync('shareUserId');
  if (!shareUserId || shareUserId =='undefined'){
    shareUserId = 1;
  }
  
  return new Promise(function(resolve, reject) {
    return login().then((res) => {
     
      //登录远程服务器
      util.request(api.AuthLoginByWeixin, {
        code: res.code,
        userInfo: userInfo,
        shareUserId: shareUserId
      }, 'POST').then(res => {
        //console.log(res);

        if (res.code === 200) {
          //存储用户信息
          wx.setStorageSync('userInfo', res.data.userInfo);
          wx.setStorageSync('token', res.data.token);
          //console.log("success");
          console.log(wx.getStorageSync('userInfo'));
          resolve(res);
        } else {
          reject(res);
        }
      }).catch((err) => {
        reject(err);
      });
    }).catch((err) => {
      reject(err);
    })
  });
}

/**
 * 判断用户是否登录
 */
function checkLogin() {
  return new Promise(function(resolve, reject) {
    if (wx.getStorageSync('userInfo') && wx.getStorageSync('token')) {
      checkSession().then(() => {
        resolve(true);
      }).catch(() => {
        reject(false);
      });
    } else {
      reject(false);
    }
  });
}

function checkAuth(){
  return new Promise(function(resolve, reject) {
    let userInfo = wx.getStorageSync('userInfo');
    var userId = userInfo.userId;
    util.request(api.AuthStatusCheck,{
        userId: userId
      },'POST').then(res => {
        //console.log(res.code);
        if (res.code === 200) {
          resolve(res);
        } else {
          reject(res);
        }
      }).catch((err) => {
        reject(err);
      });
  });
}

module.exports = {
  loginByWeixin,
  checkLogin,
  checkAuth
};