// pages/task/finish/finish.js
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
    task:{},
    taskType: {},
    content: '',
    idCard: '',
    userId: '',
    imgList: [],
    imgMaxNumber: 1,
    imageUrl: []
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    var that = this;
    var uid = options.uid;
    //console.log(options);
    var userId = wx.getStorageSync('userInfo').userId;
    //获取任务信息
    //TODO 可以一次性获取，提交时可以将任务类型参数上传
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
        //console.log(res);
        that.setData({
            task: res.data.data
        });
      }
      })

      //获取任务类型
      wx.request({
        url: api.WxGetTaskType,
        data: {
            taskId: uid
        },
        method: 'GET',
        header: {
          'content-type': 'application/json'
        },
        success:function(res){
          //console.log(res);
          that.setData({
            taskType: res.data.data
          });
        //console.log(that.data.taskType);
        }
        })
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
  ViewImage(e) {
    wx.previewImage({
      urls: this.data.imgList,
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
  bindContentInput: function(e) {
    this.setData({
      content: e.detail.value
    });
    //console.log(this.data.content);
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
        imageUrl: resultImageUrls[0]
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
  },
  submitCheck(){
    var that = this;
    var userId = wx.getStorageSync('userInfo').userId;
    if(that.data.content.length < 1 || that.data.imgList.length < 1){
      Message.error({
        context: this,
        offset: [20, 32],
        duration: 5000,
        content: '请输入打卡内容并上传照片',
      });
      return false;
    }
    Toast({
      context: this,
      selector: '#t-toast',
      message: '上传中...',
      theme: 'loading',
      direction: 'column',
      duration: 5000
    });
    wx.cloud.getTempFileURL({
      fileList: [that.data.imageUrl],
      success: res => {
        var imgUrl = res.fileList[0].tempFileURL;
        console.log(imgUrl);
        console.log(userId);
        wx.request({
          url: api.WxTaskFinish,
          data: {
            content: this.data.content,
            userId: userId,
            imageUrl: imgUrl,
            taskId: this.data.task.uid
          },
          method: 'POST',
          header: {
            'content-type': 'application/json'
          },
          success: function(res) {
            console.log(res);
            if (res.data.code == 200) {
              Message.success({
                context: this,
                offset: [20, 32],
                duration: 5000,
                content: '打卡成功',
              });

              wx.navigateBack({
                delta: 1
              });
            } else {
              Message.error({
                context: this,
                offset: [20, 32],
                duration: 5000,
                content: res.data.msg,
              });
            }
          }
        });
  }
})
}
})