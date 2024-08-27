var api = require('../../../config/api')
Page({
    data: {
      items: [null, null, null, null, null],
      rankList: [],
      currentDate:'',
      backgroundImageUrl_1: 'https://miimhub.com/ranking/%E7%9A%87%E5%86%A0_7.png?e=1713454386&token=wBkLtV20GRjR14oQ9Y5FRFoXcMn38yeqc8t_qA4Q:hhGEmVxZ0DYTCibv0mdh8HUN6ws=',
      backgroundImageUrl_2:'https://miimhub.com/ranking/%E6%8E%92%E8%A1%8C%E6%A6%9C1.png?e=1713454395&token=wBkLtV20GRjR14oQ9Y5FRFoXcMn38yeqc8t_qA4Q:6NFjem49YkZzZspL4NzFVbBwDe4=',
      backgroundImageUrl_3:'https://miimhub.com/ranking/%E6%8E%92%E8%A1%8C%E6%A6%9C2.png?e=1713454401&token=wBkLtV20GRjR14oQ9Y5FRFoXcMn38yeqc8t_qA4Q:yD5gRwL4851uqGbfCFRCJHtg6CI=',
      backgroundImageUrl_4:'https://miimhub.com/ranking/%E6%8E%92%E8%A1%8C%E6%A6%9C3.png?e=1713454408&token=wBkLtV20GRjR14oQ9Y5FRFoXcMn38yeqc8t_qA4Q:0WJlAFBanWih3p2SJ6TwvM29q-E=',
    },
    onLoad() {
      this.updateDate();
      this.loadRankList();
    },
    // 获取实时日期
    updateDate(){
      const date=new Date();
      const year=date.getFullYear();
      const month=(date.getMonth()+1).toString().padStart(2,'0');
      const day=date.getDate().toString().padStart(2,'0');
      const formattedDate=`${year}.${month}.${day}`;
      this.setData({
        currentDate:formattedDate,
      });
      setTimeout(() => {
        this.updateDate();
      }, 10000);
    },

    onShareAppMessage() {
      return {};
    },
    loadRankList(){
      var that = this;
      wx.request({
        url: api.WxGetRankList,
        method: 'GET',
        header: {
          'content-type': 'application/json'
        },
        success:function(res){
          //console.log(res);
          that.setData({
            rankList: res.data.data
          })
          //console.log(that.data.rankList);
        }
        }
      )
    }
  });


