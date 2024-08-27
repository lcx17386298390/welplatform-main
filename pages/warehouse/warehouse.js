// pages/warehouse/warehouse.js
Page({
  data: {
    items: [
      {id: 1, name: '道具1', image: 'https://miimhub.com/taskUI/ball.png?e=1713404906&token=wBkLtV20GRjR14oQ9Y5FRFoXcMn38yeqc8t_qA4Q:TZ0gy0yGyZdAmh6nosQYYLw2oRM=' },
      { id: 2, name: '道具2', image: 'https://miimhub.com/taskUI/calculate.png?e=1713404914&token=wBkLtV20GRjR14oQ9Y5FRFoXcMn38yeqc8t_qA4Q:JaP3WwjTDPREva0V3es8YdbIu5g=' },
      { id: 3, name: '道具3', image: 'https://miimhub.com/taskUI/paint.png?e=1713404922&token=wBkLtV20GRjR14oQ9Y5FRFoXcMn38yeqc8t_qA4Q:u2K8yEoXRwlLVhjt3hfzJMn43qo=' },
      { id: 4, name: '道具4', image: '/static/images/item4.png' },
      { id: 5, name: '道具5', image: '/static/images/item5.png' },
      { id: 6, name: '道具6', image: '/static/images/item6.png' },
    ], // 原始物品列表
    pagedItems: [], // 当前页面显示的物品
    currentPage: 0, // 当前页码，从0开始
    itemsPerPage: 4, // 每页显示的物品数量
  },

  onLoad: function () {
    // 假设 this.data.items 已经在这里或其他地方被赋值
    this.updatePagedItems();
  },

  updatePagedItems: function () {
    const start = this.data.currentPage * this.data.itemsPerPage;
    const end = start + this.data.itemsPerPage;
    this.setData({
      pagedItems: this.data.items.slice(start, end),
    });
  },

  onPrevPage: function () {
    if (this.data.currentPage > 0) {
      this.setData({
        currentPage: this.data.currentPage - 1,
      }, () => {
        this.updatePagedItems();
      });
    }
  },

  onNextPage: function () {
    if ((this.data.currentPage + 1) * this.data.itemsPerPage < this.data.items.length) {
      this.setData({
        currentPage: this.data.currentPage + 1,
      }, () => {
        this.updatePagedItems();
      });
    }
  },
});
