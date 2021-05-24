// pages/index/index.js
var util = require('../../utils/util.js')
var app = getApp()

// 设置可以滚动的日历
Page({
  data: {
    eventList: [],
    canvasHeight: 250,
  },
  addEvent: function () {
    wx.navigateTo({
      url: '/pages/event/edit'
    })
  },

  // 初始化事件列表，已增的倒计时时间的显示
  initEventList: function () {
    var myEvents = util.getMyEvents()
    var eventList = []
    for (var i = 0; i < myEvents.length; i++) {
      var event = {}
      var myEvent = myEvents[i]
      event["id"] = myEvent.id
      event["name"] = myEvent.name
      event["d"] = parseInt(myEvent.d)
      event["date"] = util.formatDate(new Date(event.d))
      event["urlParams"] = "i=" + event.id + "&d=" + event.d + "&n=" + encodeURI(event.name)
      eventList.push(event)
    }
    this.setData({ eventList: eventList })
  },
  onLoad: function () {
    var now = new Date()
    this.setData({ calendar: { year: now.getFullYear(), month: (now.getMonth() < 6 ? 0 : 6) } })
    this.initEventList()
    try {
      this.drawCalendar(0, 0)
    } catch (ex) { }
  },
  onPullDownRefresh: function () {
    this.onLoad()
    wx.stopPullDownRefresh()
  }

})
