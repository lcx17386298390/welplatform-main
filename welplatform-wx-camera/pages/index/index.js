// index.js
// 获取应用实例
const app = getApp()
var time = null
Page({
	data: {
    device: true,
    img: ''
	},

	onLoad() {

	},

	switchPhoto: function () {
		this.setData({
			device: !this.data.device
		})
	},

	firstPhoto: function () {
		let that = this
		let ctx = wx.createCameraContext()
		//拍照
		ctx.takePhoto({
			quality: 'high',
			success: (res) => {
				console.log('takePhoto ok:')
				console.log(res)
				console.log(res.tempImagePath)
				that.setData({
					src: res.tempImagePath
				})
				that.localhostimgesupdata(res.tempImagePath)
			},
			fail: (err) => {
				console.log('takePhoto err:')
				console.log(err)
			},
			complete: (res) => {
				//console.log('takePhoto res:')
				//console.log(res)
			}
    })
	},

	//定时器拍照
	setTime: function () {
		let that = this
		let ctx = wx.createCameraContext()
		time = setTimeout(function () {
				//console.log('拍照')
				//拍照
				ctx.takePhoto({
					quality: 'high',
					success: (res) => {
						// console.log('takePhoto ok:')
						// console.log(res)
						// console.log(res.tempImagePath)
						that.setData({
							src: res.tempImagePath
						})
						that.localhostimgesupdata(res.tempImagePath)
					},
					fail: (err) => {
						//console.log('takePhoto err:')
						//console.log(err)
					},
					complete: (res) => {
						//console.log('takePhoto res:')
						//console.log(res)
					}
				})
		}, 1000 * 3) //循环间隔 单位ms
	},

	//图片上传
	localhostimgesupdata: function (imgPath) {
		//console.log('图片上传')
		//console.log(imgPath)
		let that = this
		var uploadFile = this.uploadPhoto(imgPath).then(uploadRes => {
      //console.log('上传成功', uploadRes);
      // 可以在这里获取到fileID等信息
      let fileID = uploadRes.fileID;
      //console.log('File ID:', fileID);
      // 如果需要更新数据或UI，可以在这里调用setData
      that.setData({
        img: fileID // 假设你想显示上传后的图片
      });
      console.log(that.data.img);
      wx.hideLoading();
      wx.showToast({
        title: '上传图片成功',
        icon: 'success'
      })
      
      //后端逻辑
      //先转化临时地址
      wx.cloud.getTempFileURL({
        fileList: [that.data.img],
        success: res => {
          var imgUrl = res.fileList[0].tempFileURL;
          console.log(imgUrl);
          wx.request({
            url: 'http://81.70.59.80:9000/welplatform/wx/taskinfo/camera',
            data: {
              imageUrl: imgUrl,
              taskId: 1
            },
            method: 'POST',
            header: {
              'content-type': 'application/json'
            },
            success: function(res) {
              if (res.data.code == 200) {
                console.log(res);
                wx.hideLoading();
                wx.showToast({
                  title: res.data.data,
                  icon: 'success'
                })
                //服务端不做任何处理，用户端需做出相应
              } else {
                wx.hideLoading();
                wx.showToast({
                  title: res.data.data,
                  icon: 'error'
                })
              }
            }
          });
        }
    })
	})
  },
	stoptime: function () {
		console.log('定时停止')
		clearInterval(time)
  },
  uploadPhoto(filePath) {
    return wx.cloud.uploadFile({
      cloudPath: `${Date.now()}-${Math.floor(Math.random(0, 1) * 10000000)}.png`,
      filePath
    })
  },
	bindstop: function () {
		console.log('非正常停止')
	},
	binderror: function () {
		console.log('用户拒绝授权')
	},
	/**
	* 生命周期函数--监听页面显示
	*/
	onShow: function () {
		console.log('显示')
		//定时器
		// 　　this.setTime();
	},
	/**
	* 生命周期函数--监听页面隐藏
	*/
	onHide: function () {
		console.log('隐藏')
		clearInterval(time)
	},
	/**
	* 生命周期函数--监听页面卸载
	*/
	onUnload: function () {
		console.log('卸载')
		clearInterval(time)
	},
})
