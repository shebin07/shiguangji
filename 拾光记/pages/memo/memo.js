var util = require('../../utils/util');

// pages/memo/memo.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    memos: [],
    mindex:null,
    fmInfo:null,
    needUpdate:false
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.initData();
    console.log("[memo][onLoad]this.mindex=", this.data.mindex)
    console.log("[memo][onLoad]this.fmInfo=", this.data.fmInfo)
  },

  /**
  * 生命周期函数--监听页面显示
  */
  onShow: function () {
    if(this.data.needUpdate==true){
      console.log("[memo][onShow]mInfo:",this.data.mInfo);
      if(this.data.mindex!=null){
        this.updateData();
      }else{
        this.insertData();
      }
        this.setData({
          needUpdate:false,
          index:null,
        })
    }
    console.log("[memo][onShow]fmInfo=",this.data.fmInfo)
    console.log("[memo][onShow]mindex=", this.data.mindex)
  },

  /**
   * 编辑事件
   */
  edit:function(e) {
    console.log("[memo][edit]e", e);
    var memoItem = e.currentTarget.dataset.memoitem
    var index = e.currentTarget.dataset.index;
    console.log("[memo][edit]memoItem",memoItem);
    console.log("[memo][edit]index", index);

    // 跳转 navigateTo
    wx.navigateTo({
      url: '../add/add?index='+index+'&mInfo='+ memoItem.mInfo
    })
  },

  /**
   * 添加事件
   */
  add() {
    wx.navigateTo({
      url: '../add/add',
    })
  },
  delete:function(e){
    var that = this;
    wx.showModal({
      title: '删除',
      content: '删除该项备忘录？',
      success(res) {
        if (res.confirm) {
          console.log('用户点击确定')
          console.log("e=",e);
          that.deleteData(e.currentTarget.dataset.memoitem.mId);
        } else if (res.cancel) {
          console.log('用户点击取消')
        }
      }
    })
    
  },
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
 * 处理初始化页面列表数据
 */
  //初始化数据
initData: function (e) {
  var myuid = getApp().globalData.uid; 
  var that=this;
  wx.request({
    url: getApp().globalData.url+"/shiguangji_test2_war/memorest/optu/" + myuid,
    method: 'GET',
    data: {

    },
    header: {
      //'content-type': 'application/x-www-form-urlencoded',// 默认值
      "content-type": "multipart/form-data"
    },
    success(res) {
      if (res.data.dataZone != null) {
        that.setData({
          memos: res.data.dataZone.obj
        })
        console.log("[home][onRequestGetAllItems]读取成功", res.data.dataZone.obj)
      } else {
        console.log("[home][onRequestGetAllItems]读取失败", res.data.dataZone)
      }
    },
  })
},
//插入memo记录
insertData:function(e){
  var that =this;
  var uid=getApp().globalData.uid;
  console.log("[memo][insertData]数据库插入数据，被读取用户id", uid)
  console.log("[memo][insertData]mInfo)", this.data.mInfo)
  wx.request({
    url: getApp().globalData.url+"/shiguangji_test2_war/memorest/opt/",
    enctype: "multipart/form-data",
    method: 'POST',
    data: {
      'mUid': uid,
      'mInfo': this.data.fmInfo,
    },
    header: {
      'content-type': 'application/x-www-form-urlencoded',// 默认值
      //"content-type": "multipart/form-data"
    },
    success(res) {
      if (res.data.statusCode == 200) {
        that.initData();
        console.log("[memo][insertData]插入成功:", res)
      } else {
        console.log("[memo][insertData]插入失败:", res)
      }
    },
  })
  this.initData();
},
//删除memo事项
deleteData:function(e){
  var that =this;
  console.log("[memo][deleteDate]从后端删除e", e)
  wx.request({
    url: getApp().globalData.url+"/shiguangji_test2_war/memorest/opt/" + e,
    method: 'DELETE',
    data: {
    },
    header: {
      'content-type': 'application/x-www-form-urlencoded',// 默认值
      // "content-type":"multipart/form-data"
    },
    success(res) {
      if (res.data.statusCode == 200) {
        console.log("[home][onRequestDeleteItem]删除成功", e.detail)
        that.initData();
      } else {
        console.log("[home][onRequestDeleteItem]删除失败", e.detail)
      }
    }
  })
  this.initData();
},

  //更新数据
  updateData: function (e) {
    var uid = getApp().globalData.uid;
    var that = this;
    wx.request({
      url: getApp().globalData.url+"/shiguangji_test2_war/memorest/opt/",
      method: 'PUT',
      data: {
        mId: this.data.memos[this.data.mindex].mId,
        mUid: uid,
        mInfo: this.data.fmInfo,
      },
      header: {
        'content-type': 'application/x-www-form-urlencoded',// 默认值
        //"content-type": "multipart/form-data"
      },
      success(res) {
        if (res.data.statusCode == 200) {
          console.log("[memo][updateData]修改成功:", res)
          that.initData();
        } else {
          console.log("[memo][updateData]修改失败:", res)
        }
      },
    })
  }
})