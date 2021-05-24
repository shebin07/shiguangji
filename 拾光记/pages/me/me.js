// pages/me/me.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    //判断小程序的API，回调，参数，组件等是否在当前版本可用。
    hasUserInfo: false,
    userInfo: {},
    canIUse: false,
    canIUseOpenData:false,
    canIUseGetUserProfile: false,
    timeForDelay: 0,
   
  },

  /**
   * 生命周期函数--监听页面加载
   */
  
  onLoad() {
    console.log("[me][onLoad]startOnLoad")
   
    this.setData({
      canIUseGetUserProfile: getApp().globalData.canIUseGetUserProfile,
      canIUse: getApp().globalData.canIUse,
      canIUseOpenData: getApp().globalData.canIUseOpenData,
      userInfo: getApp().globalData.userInfo
    })
    console.log("[me][onLoad]canIUserGetUserProfile:",this.data.canIUseGetUserProfile)
    console.log("[me][onLoad]canIUseOpenData:", this.data.canIUseOpenData)
    console.log("[me][onLoad]canIUse:", this.data.canIUse)
  },
  
  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {
  },
  
  getUserProfile(e) {
    // 推荐使用wx.getUserProfile获取用户信息，开发者每次通过该接口获取用户个人信息均需用户确认，开发者妥善保管用户快速填写的头像昵称，避免重复弹窗
    wx.getUserProfile({
      desc: '展示用户信息', // 声明获取用户个人信息后的用途，后续会展示在弹窗中，请谨慎填写
      success: (res) => {
        console.log("[me][getUserProfile]res",res)
        this.setData({
          userInfo: res.userInfo,
          hasUserInfo: true
        })
        console.log("[me][getUserProfile]userinfo:",userInfo)
      }
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

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
    this.onRefresh();
  },
  onRefresh() {
    //在当前页面显示导航条加载动画
    wx.showNavigationBarLoading();
    //显示 loading 提示框。需主动调用 wx.hideLoading 才能关闭提示框
    wx.showLoading({
      title: '刷新中...',
    })
    this.onLoad();
    wx.hideLoading();
    wx.hideNavigationBarLoading();
    wx.stopPullDownRefresh();
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

  },
  onChartsClicked:function(){
    wx.navigateTo({
      url: "../charts/charts",
    })
  },
  onHelpClicked:function(){
    wx.navigateTo({
      url:"../help/help",
    })
  },
  onFeedBackClicked: function () {
    wx.navigateTo({
      url: "../feedback/feedback",
    })
  },
  onTomatoClicked:function(){
    wx.navigateTo({
      url: "../tomato/tomato",
    })
  },
  onCountdownClicked: function () {
    wx.navigateTo({
      url: "../index/index",
    })
  }, 
  openClock:function(){
    wx.navigateTo({
      url: "../clock/clock",
    })
  }
})