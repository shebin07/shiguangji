// component/Calendar/Calendar.js
Page({

  /**
   * 页面的初始数据
   */
  data: {

    hide_calendar: true,

    week_title: ['日', '一', '二', '三', '四', '五', '六'],
    to_year:null,
    to_month:null,
    to_day:null,
    cur_year:"----",
    cur_month:"--",
    cur_day:null,
    days:[],
    empty_days:0,
  },


  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (e) {
    //获取当前年月  
    if(this.data.cur_year=="----"){
      var date = new Date();
      console.log("here")
      this.setData({

        //当前点击的日期
        cur_year: date.getFullYear(),
        cur_month: date.getMonth() + 1,
        cur_day: date.getDate(),

        //今天的日期
        to_year: date.getFullYear(),
        to_month: date.getMonth() + 1,
        to_day: date.getDate(),

        days: this.getMonthDays(date.getFullYear(), date.getMonth())
      })
    }

    this.calculateEmptyDays(this.data.cur_year, this.data.cur_month);
    this.calculateDays(this.data.cur_year, this.data.cur_month);

    console.log("[Calendar][onLoad]cur_year:",this.data.cur_year)
    console.log("[Calendar][onLoad]cur_month:", this.data.cur_month)
    console.log("[Calendar][onLoad]cur_day:", this.data.cur_day)
    console.log("[Calendar][onLoad]days:", this.data.days)
    console.log("[Calendar][onLoad]empty_days:", this.data.empty_days)
    

  },
  // 获取当月共多少天
  getMonthDays: function (year, month) {
    if(month==0){
      year = year-1;
      month=12;
    }else if(month==13){
      year=year+1;
      month=1;
    }
    return new Date(year, month, 0).getDate()
  },
  // 获取当月第一天星期几
  getFirstDayOfWeek: function (year, month) {
    return new Date(Date.UTC(year, month - 1, 1)).getDay();
  },
  
  // 计算当月1号前空了几个格子，把它填充在days数组的前面
  calculateEmptyDays: function (year, month) {
    var that = this;
    //计算每个月时要清零
    that.setData({ days: [] });
    const firstDayOfWeek = this.getFirstDayOfWeek(year, month);
    if (firstDayOfWeek > 0) {
      for (let i = 0; i < firstDayOfWeek; i++) {
        var date = null
        that.data.days.push(date);
      }
      this.setData({
        days: that.data.days,
        empty_days:firstDayOfWeek
      });
    } else {//清空
      this.setData({
        days: []
      });
    }
  },
  // 绘制当月天数占的格子，并把它放到days数组中
  calculateDays: function (year, month) {
    var that = this;
    const thisMonthDays = this.getMonthDays(year, month);
    for (let i = 1; i <= thisMonthDays; i++) {
      var date = i
      that.data.days.push(date);
    }
    this.setData({
      days: that.data.days
    });
  },
  //天的圆圈被点击
  onDayClicked:function(e){
    console.log("[Calendar][onDayClicked]days[index]:", this.data.days[e.currentTarget.dataset.index])
    //记录点击日的日期参数
    this.setData({
      cur_day:this.data.days[e.currentTarget.dataset.index]
    })
    this.onFocusedDayChanged();
  },

  onMonthPreClicked:function(e){
    var new_month = this.data.cur_month;
    var new_year = this.data.cur_year;
    if(this.data.cur_month==1){
      new_month= 12;
      new_year = new_year-1;
    }else {
      new_month = new_month-1;
    }
    this.setData({
      cur_month:new_month,
      cur_year:new_year,
      cur_day:1
    })
    //更新每月天数
    this.calculateEmptyDays(this.data.cur_year, this.data.cur_month);
    this.calculateDays(this.data.cur_year, this.data.cur_month);

    this.onFocusedDayChanged();
  },
  onMonthAftClicked: function (e) {
    var new_month = this.data.cur_month;
    var new_year = this.data.cur_year;
    if (this.data.cur_month == 12) {
      new_month = 1;
      new_year = new_year + 1;
    } else {
      new_month = new_month + 1;
    }
    this.setData({
      cur_month: new_month,
      cur_year: new_year,
      cur_day: 1
    })
    //更新每月天数
    this.calculateEmptyDays(this.data.cur_year, this.data.cur_month);
    this.calculateDays(this.data.cur_year, this.data.cur_month);

    this.onFocusedDayChanged();
  },
  //上一天
  onDayPreClicked: function (e) {
    var new_month = this.data.cur_month;
    var new_year = this.data.cur_year;
    var new_day = this.data.cur_day;
    if (new_day == 1) {
      if (new_month==1){
        new_year = new_year-1;
        new_month=12;
        new_day = this.getMonthDays(new_year,new_month,0)
      }else {
        new_month=new_month-1;
        new_day = this.getMonthDays(new_year, new_month, 0)
      }
    } else {
      new_day = new_day - 1;
    }
    this.setData({
      cur_month: new_month,
      cur_year: new_year,
      cur_day: new_day
    })
    //更新每月天数
    this.calculateEmptyDays(this.data.cur_year, this.data.cur_month);
    this.calculateDays(this.data.cur_year, this.data.cur_month);
    this.onFocusedDayChanged();

  },
  //下一天
  onDayAftClicked: function (e) {
    var new_month = this.data.cur_month;
    var new_year = this.data.cur_year;
    var new_day = this.data.cur_day;
    if (new_day == this.getMonthDays(new_year, new_month, 0)) {
      if (new_month == 12) {
        new_year = new_year +1 ;
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
    //更新每月天数
    this.calculateEmptyDays(this.data.cur_year, this.data.cur_month);
    this.calculateDays(this.data.cur_year, this.data.cur_month);

    this.onFocusedDayChanged();
  },
  //收起日历
  closeCalendar:function(e){
    this.setData({
      hide_calendar:!this.data.hide_calendar
    })
    this.triggerEvent("onCalendarClose",this.data.hide_calendar)
  },
  //当前选中日期变化了
  onFocusedDayChanged:function(){
    console.log("[Calendar][onFocusedDayChanged]焦点日期变化为：", this.data.cur_year, "/", this.data.cur_month, "/", this.data.cur_day)
    this.triggerEvent("onFocusedDayChanged",{year:this.data.cur_year,month:this.data.cur_month,day:this.data.cur_day})
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