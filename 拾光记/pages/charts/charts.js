// pages/charts/charts.js
const format = require("../../utils/util2.js");
let Charts = require('./../../utils/wxcharts.js');
Page({

  /**
   * 页面的初始数据
   */
  data: {
    cur_day:null,
    cur_month:null,
    cur_year:null,
    day_all:0,
    day_finish:0,
    week_all: 0,
    week_finish: 0,
    month_all: 0,
    month_finish: 0,
    page_hidden_day:false,
    page_hidden_week:true,
    page_hidden_month:true,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    if (this.data.cur_year == null && this.data.cur_month == null&&this.data.cur_day == null){
      this.initDate();
    }
    this.requestData().then(res=>{
      this.creatCharts();
    })
    console.log("this.data=", this.data)
  },
  initDate(){
    //var today = format.formatTime(new Date(), 'Y-M-D');
    var today = new Date();
    var year = today.getFullYear();
    var month = today.getMonth() + 1;
    var day = today.getDate();

    var that = this;
    this.setData({
      cur_day: day,
      cur_month: month,
      cur_year: year
    })
  },
  onDayPageClicked:function(){
    this.setData({
      page_hidden_day:false,
      page_hidden_week: true,
      page_hidden_month: true,
    })
    this.creatDayCharts();
  },

  onWeekPageClicked: function () {
    this.setData({
      page_hidden_day: true,
      page_hidden_week: false,
      page_hidden_month: true,
    })
    this.creatWeekCharts();
  },
  onMonthPageClicked: function () {
    this.setData({
      page_hidden_day: true,
      page_hidden_week: true,
      page_hidden_month: false,
    })
    this.creatMonthCharts();
  },
  onPrePageClicked:function(){
    var new_month = this.data.cur_month;
    var new_year = this.data.cur_year;
    var new_day = this.data.cur_day;
    if (new_day == 1) {
      if (new_month == 1) {
        new_year = new_year - 1;
        new_month = 12;
        new_day = this.getMonthDays(new_year, new_month, 0)
      } else {
        new_month = new_month - 1;
        new_day = this.getMonthDays(new_year, new_month, 0)
      }
    } else {
      new_day = new_day - 1;
    }
    this.setData({
      cur_year:new_year,
      cur_month:new_month,
      cur_day:new_day
    })
    this.requestData().then(res => {
      this.creatCharts();
    })
  },
  onAftPageClicked:function(){
    var new_month = this.data.cur_month;
    var new_year = this.data.cur_year;
    var new_day = this.data.cur_day;
    if (new_day == this.getMonthDays(new_year, new_month, 0)) {
      if (new_month == 12) {
        new_year = new_year + 1;
        new_month = 1;
        new_day = 1;
      } else {
        new_month = new_month + 1;
        new_day = 1;
      }
    } else {
      new_day = new_day + 1;
    }
    this.setData({
      cur_month: new_month,
      cur_year: new_year,
      cur_day: new_day
    })
    this.requestData().then(res => {
      this.creatCharts();
    })
  },
  // 获取当月共多少天
  getMonthDays: function (year, month) {
    if (month == 0) {
      year = year - 1;
      month = 12;
    } else if (month == 13) {
      year = year + 1;
      month = 1;
    }
    return new Date(year, month, 0).getDate()
  },
  //创建图表
  creatCharts: function () {
    console.log("create charts")
      this.creatDayCharts();
      this.creatWeekCharts();
      this.creatMonthCharts();
  },
  creatDayCharts: function () {
    var res = wx.getSystemInfoSync();
    var windowWidth = res.windowWidth;
    var windowHeight = res.windowHeight;
    new Charts({
      animation: true,
      canvasId: 'day',
      type: 'ring',
      extra: {
        ringWidth: 20,
        pie: {
          offsetAngle: 0
        }
      },
      title: {
        name: ((this.data.day_finish / this.data.day_all) * 100).toFixed(2) + "%",
        color: '#7cb5ec',
        fontSize: 20
      },
      subtitle: {
        name: 'day',
        color: '#666666',
        fontSize: 15
      },
      series: [{
        name: '完成',
        data: this.data.day_finish,
        stroke: false
      }, {
        name: '未完成',
        data: this.data.day_all - this.data.day_finish,
        stroke: false
      }],
      disablePieStroke: true,
      width: windowWidth,
      height: windowHeight/2,
      dataLabel: false,
      legend: true,
      padding: 0
    })
  },
  creatWeekCharts: function () {
    var res = wx.getSystemInfoSync();
    var windowWidth = res.windowWidth;
    var windowHeight = res.windowHeight;
    new Charts({
      animation: true,
      canvasId: 'week',
      type: 'ring',
      extra: {
        ringWidth: 20,
        pie: {
          offsetAngle: 0
        }
      },
      title: {
        name: ((this.data.week_finish / this.data.week_all) * 100).toFixed(2) + "%",
        color: '#7cb5ec',
        fontSize: 20
      },
      subtitle: {
        name: 'week',
        color: '#666666',
        fontSize: 15
      },
      series: [{
        name: '完成',
        data: this.data.week_finish,
        stroke: false
      }, {
        name: '未完成',
        data: this.data.week_all - this.data.week_finish,
        stroke: false
      }],
      disablePieStroke: true,
      width: windowWidth,
      height: windowHeight / 2,
      dataLabel: false,
      legend: true,
      padding: 0
    })
  },
  creatMonthCharts: function () {
    var res = wx.getSystemInfoSync();
    var windowWidth = res.windowWidth;
    var windowHeight = res.windowHeight;
    new Charts({
      animation: true,
      canvasId: 'month',
      type: 'ring',
      extra: {
        ringWidth: 20,
        pie: {
          offsetAngle: 0
        }
      },
      title: {
        name: ((this.data.month_finish / this.data.month_all) * 100).toFixed(2) + "%",
        color: '#7cb5ec',
        fontSize: 20
      },
      subtitle: {
        name: 'month',
        color: '#666666',
        fontSize: 15
      },
      series: [{
        name: '完成',
        data: this.data.month_finish,
        stroke: false
      }, {
        name: '未完成',
        data: this.data.month_all - this.data.month_finish,
        stroke: false
      }],
      disablePieStroke: true,
      width: windowWidth,
      height: windowHeight / 2,
      dataLabel: false,
      legend: true,
      padding: 0
    })
  },
  /**
   * 向后端请求数据
   */
requestData:function(){
    var myuid = getApp().globalData.uid;
  var date = this.data.cur_year + "-" + this.data.cur_month + "-"+this.data.cur_day;
    var that = this;
    return new Promise(function(resolve,reject){
      wx.request({
        url: getApp().globalData.url +"/shiguangji_test2_war/listitemrest/optn/" + myuid + "/" + date,
        method: 'GET',
        data: {},
        header: {
          //'content-type': 'application/x-www-form-urlencoded',// 默认值
          "content-type": "multipart/form-data"
        },
        success(res) {
          if (res.data.dataZone != null) {
            that.setData({
              day_all: res.data.dataZone.dayAll,
              day_finish: res.data.dataZone.dayFinish,
              week_all: res.data.dataZone.weekAll,
              week_finish: res.data.dataZone.weekFinish,
              month_all: res.data.dataZone.monthAll,
              month_finish: res.data.dataZone.monthFinish,
            })
            console.log("[charts]][requestData]读取成功", res.data.dataZone)
            resolve();
          } else {
            console.log("charts][requestData]读取失败", res.data.dataZone)
          }
        },
      })
    })
    
  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})