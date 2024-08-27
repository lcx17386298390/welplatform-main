// pages/adventure/adventure.js
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    score: 0,
    visible: false,
    taskImage: 'https://miimhub.com/UI/taskbgm.png?e=1713403035&token=wBkLtV20GRjR14oQ9Y5FRFoXcMn38yeqc8t_qA4Q:sSfqxYpOrEMiyzRtCi58tLkgd0k=',
    rewardImage: 'https://miimhub.com/taskUI/taskbgm2.png?e=1713403052&token=wBkLtV20GRjR14oQ9Y5FRFoXcMn38yeqc8t_qA4Q:w8kfvwSMg3mAIhLn205iPOs0EDE=',
    currentTask: null,
    tasks: [{
        title: '第一天：了解学校',
        content: '任务目标：了解学校以及所报专业相关信息。',
        completed: false,
        reward: 10,
      },
      {
        title: '第二天：入校准备',
        content: '任务目标：拍照上传准备好的生活用品',
        completed: false,
        reward: 10,
      },
      {
        title: '第一天：了解学校',
        content: '任务目标：了解学校以及所报专业相关信息。',
        completed: false,
        reward: 10,
      },
    ],
    currentTaskIndex: 0,
    rewardPopupVisible: false,
    reward: 0,
    userInfo: {},
    hasLogin: false,
    hasAuth: false,
    showTutorial: true,
    highlight: 'caiDan', // 初始高亮区域
    step: 0, // 步骤计数
    caiDanTop: 0, // 道具提示框的top位置
    caiDanRight: 0, // 道具提示框的right位置
    buildingTop: 0, // 建筑提示框的top位置
    buildingLeft: 0,
    fiveTop: 0,
    fiveLeft: 0,
    scoreTop: 0,
    scoreLeft: 0,
  },
  goPages: function (e) {
    //  console.log(e);
    console.log("登录状态为" + this.data.hasLogin);
    console.log("认证状态为" + this.data.hasAuth);
    if (!(this.data.hasLogin)) {
      wx.navigateTo({
        url: "/pages/auth/login/login"
      });
    }

    if (app.globalData.hasAuth && e.currentTarget.dataset.url == "/pages/ucenter/auth/auth") {
      Message.warning({
        context: this,
        offset: [20, 32],
        duration: 5000,
        content: '您已认证成功',
      });
      return;
    }
    if (!app.globalData.hasAuth && e.currentTarget.dataset.url == "/pages/ucenter/info/info") {
      wx.navigateTo({
        url: "/pages/ucenter/auth/auth"
      });
    }
    wx.navigateTo({
      url: e.currentTarget.dataset.url
    });
  },
  goforit() {
    const {
      tasks,
      currentTaskIndex
    } = this.data;
    const currentTask = tasks[currentTaskIndex];

    if (!currentTask.completed) {
      wx.navigateTo({
        url: `/pages/map/index?index=${currentTaskIndex}`,
      });
    } else {
      this.handlePopup({
        visible: false
      });
      this.showReward(currentTask.reward);
    }

    // 更新任务完成状态
    tasks[currentTaskIndex].completed = true;
    this.setData({
      tasks: tasks
    });
  },

  showReward(reward) {
    const newScore = this.data.score + reward;
    this.setData({
      reward: reward,
      rewardPopupVisible: true,
      visible: false, // 关闭任务弹窗
      score: newScore,
    });
  },
  handlePopup() {
    const { tasks, currentTaskIndex } = this.data;
    let nextTaskIndex = currentTaskIndex; // 默认显示当前任务
  
    // 找到下一个未完成的任务索引
    for (let i = currentTaskIndex + 1; i < tasks.length; i++) {
      if (!tasks[i].completed) {
        nextTaskIndex = i;
        break;
      }
    }
  
    this.setData({
      currentTask: tasks[nextTaskIndex], // 显示下一个未完成的任务
      currentTaskIndex: nextTaskIndex,
      visible: true,
    });
  },
  
  onVisibleChange(e) {
    this.setData({
      visible: e.detail.visible
    });
  },
  onClose() {
    this.setData({
      visible: false
    });
  },
  closeReward() {
    this.setData({
      rewardPopupVisible: false
    });
  },
  completeTask(reward) {
    let {
      currentTaskIndex,
      tasks
    } = this.data;
    let currentTask = tasks[currentTaskIndex];
    currentTask.completed = true;
    tasks[currentTaskIndex] = currentTask;

    // 更新积分并显示奖励页面
    this.setData({
      tasks: tasks,
      score: this.data.score + reward, // 更新总积分
    });

    this.showReward(reward);

    // 设置下一个任务
    if (currentTaskIndex + 1 < tasks.length) {
      this.setData({
        currentTaskIndex: currentTaskIndex + 1,
      });
    }
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.checkTutorialStatus();
    this.setPositions();
    this.animation = wx.createAnimation({
      duration: 500,
      timingFunction: 'ease-in-out',
    });
    // 如果页面被传递了参数，例如通过 query
    if (options && options.inputValue) {
      this.setData({
        inputValue: options.inputValue // 设置文本框默认值为传递的参数
      });
    }
  },
  checkTutorialStatus: function () {
    // 从本地存储中读取引导状态
    var that = this;
    wx.getStorage({
      key: 'tutorialCompleted',
      success: function (res) {
        // 如果用户已完成引导，则不再显示
        if (res.data) {
          that.setData({
            showTutorial: false
          });
        }
      },
      fail: function () {
        // 如果没有找到标记，则显示引导 
        that.setData({
          showTutorial: true
        });
      }
    });
  },
  completeTutorial: function () {
    // 用户点击“完成”后调用此方法
    this.setData({
      showTutorial: false
    });
    // 设置本地存储标记为true
    wx.setStorage({
      key: 'tutorialCompleted',
      data: true
    });
  },
  nextStep: function () {
    let newHighlight = '';
    let nextStep = this.data.step + 1;

    // 根据当前步骤设置高亮区域和步骤计数
    switch (nextStep) {
      case 1:
        newHighlight = 'building';
        break;
      case 2:
        newHighlight = 'five';
        break;
      case 3:
        newHighlight = 'score';
        break;
      default:
        this.completeTutorial();
        return;
    }

    // 更新数据绑定，触发界面更新
    this.setData({
      highlight: newHighlight,
      step: nextStep
    });
  },
  setPositions: function () {
    // 这里需要根据实际图片的尺寸和布局来调整位置值
    // 假设红框位置在图片中的具体位置已知
    this.setData({
      propsTop: 20, // 示例值，实际情况要调整
      propsRight: 30, // 示例值，实际情况要调整
      buildingTop: 50, // 示例值，实际情况要调整
      buildingLeft: 60, // 示例值，实际情况要调整
      five: 20,
      fiveLeft: 20,
      scoreTop: 20,
      scoreLeft: 20,
    });
  },
  later() {

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
    let {
      tasks,
      currentTaskIndex
    } = this.data;
    let currentTask = tasks[currentTaskIndex];
    if (currentTask.completed) {
      this.showReward(currentTask.reward);
    }
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
          hasAuth: true,
          score: userInfo.points
        })
      }
    }
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