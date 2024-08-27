// pages/ucenter/auth/auth.js
import Message from 'tdesign-miniprogram/message/index';
import Toast from 'tdesign-miniprogram/toast/index';
var api = require('../../../config/api.js');
var util = require('../../../utils/util.js');
var user = require('../../../utils/user.js');
var app = getApp();

Page({

  /**
   * 页面的初始数据
   */
  data: {
    name: '',
    idCard: '',
    userId: '',
    imgList: [],
    imgMaxNumber: 1,
    imageUrl: [],
    imgAvatarList: [],
    imgAvatarMaxNumber: 1,
    imageAvatarUrl: [],
    nameImage:'https://miimhub.com/login/login-sure2.png?e=1713449615&token=wBkLtV20GRjR14oQ9Y5FRFoXcMn38yeqc8t_qA4Q:nkDMlclBYhbyqnnrd5Bnyx5wH58=',
    idImage:'https://miimhub.com/login/login-sure2.png?e=1713449615&token=wBkLtV20GRjR14oQ9Y5FRFoXcMn38yeqc8t_qA4Q:nkDMlclBYhbyqnnrd5Bnyx5wH58=',
    textBar:'https://miimhub.com/pageUI/add-status.png?e=1713449643&token=wBkLtV20GRjR14oQ9Y5FRFoXcMn38yeqc8t_qA4Q:VOuxcWKEXZL9tV5lfVLkBMaH66U=',
    addPhoto:'https://miimhub.com/pageUI/add-photo.png?e=1713449649&token=wBkLtV20GRjR14oQ9Y5FRFoXcMn38yeqc8t_qA4Q:fJt9r6AJCmb42OgKtj6gG6yEcLc=',
    loginBtn:'https://miimhub.com/pageUI/bgm.png?e=1713449655&token=wBkLtV20GRjR14oQ9Y5FRFoXcMn38yeqc8t_qA4Q:PsMcES0HsLqQrKxkfJE2_8tIPaU=',
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {
    let userInfo = wx.getStorageSync('userInfo');
    this.data.userId = userInfo.userId;
    //console.log(this.data.userId);
  },

  bindNameInput: function(e) {
    this.setData({
      name: e.detail.value
    });
  },
  bindIdCardInput: function(e) {

    this.setData({
      idCard: e.detail.value
    });
  },
  clearInput: function(e) {
    switch (e.currentTarget.id) {
      case 'clear-username':
        this.setData({
          name: ''
        });
        break;
      case 'clear-password':
        this.setData({
          idCard: ''
        });
        break;
    }
  },
  authIdCard: function() {
    var that = this;
    //console.log(111111);
    //console.log("身份证号:" + this.data.idCard + "姓名" + this.data.name + "照片地址" + this.data.imgList)
    //检测是否输入身份证号码和姓名
    if (this.data.name.length < 1 || this.data.idCard.length < 1 || this.data.imgList.length < 1 || this.data.imgAvatarList.length < 1) {
      // wx.showModal({
      //   title: '错误信息',
      //   content: '请输入姓名和身份证号码并上传录取通知书和个人头像图片',
      //   showCancel: false
      // });
      Message.error({
        context: this,
        offset: [20, 32],
        duration: 5000,
        content: '请输入姓名和身份证号码并上传录取通知书和个人头像图片',
      });
      return false;
    }
    //console.log(JSON.stringify(this.data.imgList))
    //console.log(this.data.imgList[0]);
    var imgUrl;
    var avatarUrl;
    var fileList = [this.data.imageUrl,this.data.imageAvatarUrl];
    //fileList[0] = this.data.imageUrl;
    //fileList[1] = this.data.imageAvatarUrl;
    //console.log(fileList);
    //console.log(fileList);
    Toast({
      context: this,
      selector: '#t-toast',
      message: '认证中...',
      theme: 'loading',
      direction: 'column',
      duration: 5000
    });
    wx.cloud.getTempFileURL({
      fileList: [that.data.imageUrl[0],that.data.imageAvatarUrl[0]],
      success: res => {
        // get temp file URL
        //console.log(res);
        //console.log(res.fileList[0]);
        imgUrl = res.fileList[0].tempFileURL;
        avatarUrl = res.fileList[1].tempFileURL;
        //console.log(res.fileList);
        wx.request({
          url: api.AuthIdCard,
          data: {
            name: this.data.name,
            idCard: this.data.idCard,
            userId: this.data.userId,
            imgUrl: imgUrl,
            avatarUrl: avatarUrl
          },
          method: 'POST',
          header: {
            'content-type': 'application/json'
          },
          success: function(res) {
            console.log(res);
            if (res.data.code == 200) {
              // wx.showToast({
              //   title: '认证成功',
              //   icon: 'success'
              // })
              Message.success({
                context: this,
                offset: [20, 32],
                duration: 5000,
                content: '认证成功',
              });
              // this.setData({
              //认证成功
              app.globalData.hasAuth = true;

              //更新userInfo
              var userInfo = wx.getStorageSync('userInfo');
              userInfo.school = res.data.data.school;
              userInfo.studentId = res.data.data.studentId;
              userInfo.major = res.data.data.major;
              userInfo.name = res.data.data.name;
              userInfo.idCard = res.data.data.idCard;
              userInfo.gender = res.data.data.gender;
              userInfo.admissionStatus = res.data.data.admissionStatus;
              userInfo.photo = res.data.data.photo;


              wx.setStorageSync('userInfo', userInfo);
              //console.log(wx.getStorageSync('userInfo'));
              // });
              wx.switchTab({
                url: '/pages/ucenter/index/index'
              });
            } else {
              // this.setData({
              // });
              // util.showErrorToast(res.data.msg);
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
      fail: err => {
        // handle error
        console.log(err);
        Message.error({
          context: this,
          offset: [20, 32],
          duration: 5000,
          content: err,
        });
      }
    })
    //console.log(imgUrl);
  },
  
  ChooseImage() {
    wx.chooseImage({
      count: this.data.imgMaxNumber, //默认9
      sizeType: ['original', 'compressed'], //可以指定是原图还是压缩图，默认二者都有
      sourceType: ['album','camera'], //从相册选择
      success: (res) => {
        console.log("上传图片成功" + res.tempFilePaths);
        if (this.data.imgList.length != 0) {
          this.setData({
            imgList: this.data.imgList.concat(res.tempFilePaths)
          })
        } else {
          this.setData({
            imgList: res.tempFilePaths
          })
        }
        this.uploadImgs();
      }
    });
  },
  ChooseAvatarImage(){
    wx.chooseImage({
      count: this.data.imgAvatarMaxNumber, //默认9
      sizeType: ['original', 'compressed'], //可以指定是原图还是压缩图，默认二者都有
      sourceType: ['album','camera'], //从相册选择
      success: (res) => {
        console.log("上传图片成功" + res.tempFilePaths);
        if (this.data.imgAvatarList.length != 0) {
          this.setData({
            imgAvatarList: this.data.imgAvatarList.concat(res.tempFilePaths)
          })
        } else {
          this.setData({
            imgAvatarList: res.tempFilePaths
          })
        }
        this.uploadAvatarImgs();
      }
    });
  },
  ViewImage(e) {
    wx.previewImage({
      urls: this.data.imgList,
      current: e.currentTarget.dataset.url
    });
  },
  ViewAvatarImage(e) {
    wx.previewImage({
      urls: this.data.imgAvatarList,
      current: e.currentTarget.dataset.url
    });
  },
  DelImg(e) {
    wx.showModal({
      title: '确定删除这张图片吗？',
      cancelText: '再看看',
      confirmText: '确定',
      success: res => {
        if (res.confirm) {
          this.data.imgList.splice(e.currentTarget.dataset.index, 1);
          this.setData({
            imgList: this.data.imgList
          })
        }
      }
    })
  },
  DelAvatarImg(e) {
    wx.showModal({
      title: '确定删除这张图片吗？',
      cancelText: '再看看',
      confirmText: '确定',
      success: res => {
        if (res.confirm) {
          this.data.imgAvatarList.splice(e.currentTarget.dataset.index, 1);
          this.setData({
            imgAvatarList: this.data.imgAvatarList
          })
        }
      }
    })
  },
  uploadPhoto(filePath) {
    return wx.cloud.uploadFile({
      cloudPath: `${Date.now()}-${Math.floor(Math.random(0, 1) * 10000000)}.png`,
      filePath
    })
  },

  uploadImgs() {
    var that = this;
    // wx.showLoading({
    //   title: '上传中'
    // })
    Toast({
      context: this,
      selector: '#t-toast',
      message: '上传中...',
      theme: 'loading',
      direction: 'column',
      duration: 500
    });
    const uploadTask = this.data.imgList.map(item => this.uploadPhoto(item))
    Promise.all(uploadTask).then(result => {
      console.log("上传结果", result)
      let resultImageUrls = [];
      for (const file of result) {
        resultImageUrls.push(file.fileID);
      }
      console.log("上传后的图片云存储路径", resultImageUrls);
      wx.hideLoading();
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
      this.setData({
        imageUrl: resultImageUrls
      })
      //console.log(resultImageUrls[0]);
      //console.log("云图片地址" + this.data.imageUrl);
    }).catch((err) => {
      wx.hideLoading()
      // wx.showToast({
      //   title: '上传图片错误',
      //   icon: 'error'
      // })
      Message.error({
        context: this,
        offset: [20, 32],
        duration: 5000,
        content: err.msg
      });
    })
  },
  uploadAvatarImgs() {
    var that = this;
    // wx.showLoading({
    //   title: '上传中'
    // })
    Toast({
      context: this,
      selector: '#t-toast',
      message: '上传中...',
      theme: 'loading',
      direction: 'column',
      duration: 500
    });
    const uploadTask = this.data.imgAvatarList.map(item => this.uploadPhoto(item))
    Promise.all(uploadTask).then(result => {
      console.log("上传结果", result)
      let resultImageUrls = [];
      for (const file of result) {
        resultImageUrls.push(file.fileID);
      }
      console.log("上传后的图片云存储路径", resultImageUrls)
      wx.hideLoading();
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
      this.setData({
        imageAvatarUrl: resultImageUrls
      })
      //console.log(resultImageUrls[0]);
      //console.log("云图片地址" + this.data.imageUrl);
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
  }
})