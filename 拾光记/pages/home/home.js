// pages/home/home.js

Page({
  
  /**
   * 页面的初始数据
   */
  data: {
    c_true:true,
    c_false:false,
    readable:false,
    date:null,
    saveTrue:true,
    uid:null,
    Item: [],
    hasInfo: false,
    addpage_isShow:false,
    changepage_isShow: false,
    checkbox_clicked: false,
    changeitem_index:null,
    update_clicked:false,//全局点击时，用来区分addItemPage是否已经打开

    tabName1: "月",
    tabName2: "周",
    tabName3: "日",

    menuName1:"备忘录",
    menuName2:"事项",
    menuName3:"我的",

    liTypes:["month","week","day"],

    day_all: 0,
    day_finish: 0,
    week_all: 0,
    week_finish: 0,
    month_all: 0,
    month_finish: 0,
   
  },
  

   /**
   * 生命周期函数--监听页面加载
   */
  onLoad:function(options){
    let that = this;
    //判断onLaunch是否执行完毕
    if (getApp().globalData.checkLogin) {
      that.setData({
        readable: true
      })
      console.log("2222222222222222222222222222222222222+uid", getApp().globalData.uid)
      //请求当天数据
      //while(getApp().globalData.uid==-2){}
      this.onRequestGetAllItems().then((res) => {
        //console.log("请求的数据:", res);
        this.setData({
          Item: res.data.dataZone.obj
        })
        console.log("[home][onLoad]success");
      })
      console.log("onLoad执行结束")
    } else {
      getApp().checkLoginReadyCallback = res => {
        that.setData({
          readable: true
        })
        console.log("2222222222222222222222222222222222222+uid", getApp().globalData.uid)
        //请求当天数据
        //while(getApp().globalData.uid==-2){}
        this.onRequestGetAllItems().then((res) => {
          //console.log("请求的数据:", res);
          this.setData({
            Item: res.data.dataZone.obj
          })
          console.log("[home][onLoad]success");
        }).catch((res) => {
          console.log("[home][onLoad]fail:", res);
        })
        console.log("onLoad执行结束")
      };
    }
    this.selectComponent("#myTabPage").refreshCalendar();
   
  },
  //插入事项
  addItem: function () {
    if (getApp().globalData.uid == -1 || getApp().globalData.uid ==null){
      //console.log("[home][addItem]uid",getApp().globalData.uid)
      console.log("[home][addItem]uid2", this.data.uid)
      wx.showToast({
        icon:'none',
        title: '请登录！',
      })
    }else{
      this.setData({
        addpage_isShow: true,
        update_clicked: false
      });
      console.log("[home][addItem]uid2", this.data.uid)
      console.log("[home][addItem]点击按钮：添加事项");
    }
    
  },
  //添加事项
  onChangeOver: function (e) {
   
    if(e.detail!=null){
      if(this.data.changeitem_index!=null){
        console.log("[home][onChangeOver]Update Yes::Item:", newItem)
        var newItem = e.detail;
        this.onRequestUpdateItem(newItem);
      }else{
        console.log("[home][onChangeOver]Insert Yes::Item:", newItem)
        var newItem = e.detail;
        this.onRequestInsertItem(newItem);
      }
    }
    this.setData({
      addpage_isShow: false,
      changepage_isShow: false,
      changeitem_index: null,
      update_clicked: false
    })
    console.log("[home][onChangeOver]添加事项：添加事项页关闭",e.detail)
   
  },
  //修改事项
  changeItem: function (e) {
    if (this.data.changepage_isShow == false && this.data.addpage_isShow == false) {
      this.setData({
        changeitem_index: e.detail,
        update_clicked: true 
      });
      console.log("[home][changeItem]点击按钮：修改事项下标", e.detail, ",事项：", this.data.Item[e.detail]);
    } else if (this.data.changepage_isShow == true || this.data.addpage_isShow == true) {
      this.triggerEvent("onChangeNo")
      console.log("[home][changeItem]点击空白：放弃修改事项")
    }

  },
  //删除事项
  deleteItem:function(e){
    console.log("[home][changeBox]长按删除事项，删除Item的liId为：",e.detail)
    this.onRequestDeleteItem(e);
    this.onLoad();
  },
  changeBox: function (e) {
    this.setData({
      checkbox_clicked:true
    })
    var newItem = e.detail
    console.log("0:")
    this.onRequestUpdateItemFinish(newItem);
    console.log("[home][changeBox]点击checkbox，切换Finish状态为：",e.detail.liFinish)
  },
  //点击空白隐藏
  onOuterClicked:function(e){
    console.log("[home][onOuterClicked]data.addpage_isShow/changepage_isShow/update_clicked/checkbox_clicked:",this.data.addpage_isShow, "/", this.data.changepage_isShow, "/", this.data.update_clicked, "/", this.data.checkbox_clicked);
    if(this.data.checkbox_clicked==true){
        this.setData({
          checkbox_clicked:false,
          changepage_isShow: false,
          addpage_isShow: false,
          changeitem_index: null,
          update_clicked: false
        })
    }else {
      
      if (this.data.addpage_isShow == true || this.data.changepage_isShow == true) {
        console.log("[home][onOuterClicked]隐藏,addpage:", this.data.addpage_isShow, ",changepage_isShow:", this.data.changepage_isShow)
        this.setData({
          changepage_isShow: false,
          addpage_isShow: false,
          changeitem_index: null,
          update_clicked: false,
          checkbox_clicked: false
        })
      } else if (this.data.checkbox_clicked == true) {
        this.setData({
          checkbox_clicked: false
        })
      } else if (this.data.addpage_isShow == false && this.data.changepage_isShow == false && this.data.update_clicked == true) {
        this.setData({
          changepage_isShow: true
        })
      }
    }
    
},
  //当焦点日期变化
  onFocusedDayChanged:function(e){
    console.log("[home][onFocusedDayChanged]year/month/day", e.detail.year, "-", e.detail.month, "-",e.detail.day)
    this.setData({
      date:e.detail.year+"-"+e.detail.month+"-"+e.detail.day
    })
    this.onLoad();
  },
  //专门用于发送到下一天的更新函数
  sendItemToNext: function (e) {
    this.onRequestUpdateItemTime(e.detail);
    //this.onLoad();
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
  methods: {
  },



  //后端请求数据，该用户的当日事项
  onRequestGetAllItems: function () {
    var that = this;
    this.setData({
      uid: getApp().globalData.uid
    });
    var myuid = this.data.uid;
    var url = getApp().globalData.url;
    console.log("[home][onRequestGetAllItems]读取数据库，被读取用户id", this.data.uid)

    //初始化当天日期
    const format = require("../../utils/util2.js");
    var today = (this.data.date == null ? format.formatTime(new Date(), 'Y-M-D'):this.data.date);
    console.log("[home][onRequestGetAllItems]today:",today)
   //var today = new Date();
    this.setData({
       date: today
    })
    console.log("[home][onRequestGetAllItems]date:", this.data.date)
    console.log("[home][onRequestGetAllItems]today:", today)

    //从数据库读取item数据
    return new Promise(function (resolve, reject) {
      wx.request({
        url: url +"/shiguangji_test2_war/listitemrest/opte/" + myuid+"/"+today,
        method: 'GET',
        data: {
         
        },
        header: {
          //'content-type': 'application/x-www-form-urlencoded',// 默认值
          "content-type": "multipart/form-data"
        },
        success(res) {
          if (res.data.dataZone !=null) {
            resolve(res)
            that.setData({
              Item: res.data.dataZone.obj
            })
            console.log("[home][onRequestGetAllItems]读取成功", res.data.dataZone.obj)
            that.onRequestNums();
          } else {
            console.log("[home][onRequestGetAllItems]读取失败", res.data.dataZone)
            that.onRequestNums();
          }
        },
      })
    })
    
  },

  //后端更新数据，用户修改数据
  onRequestUpdateItem: function (e) {
    // console.log("[home][onRequestUpdateItem]e.detail:", e.detail)
    console.log("1")
    var that = this;
    const format = require("../../utils/util2.js");
    var today = (this.data.date == null ? format.formatTime(new Date(), 'Y-M-D') : this.data.date);
    //var today = new Date();
    this.setData({
      date: today
    })
    console.log("[home][onRequestUpdateItem]更新数据库，被读取用户id", this.data.uid)
    wx.request({
      url: getApp().globalData.url+"/shiguangji_test2_war/listitemrest/opt/",
      method: 'PUT',
      data: {
        'liId': e.liId,
        'liUid': this.data.uid,
        'liName': e.liName,
        'liInfo': e.liInfo,
        'liType': e.liType,
        'liImportant': e.liImportant,
        'liFinish': e.liFinish,
        'LiMyDate': this.data.date
      },
      header: {
        'content-type': 'application/x-www-form-urlencoded',// 默认值
        //"content-type": "multipart/form-data"
      },
      success(res) {
        if (res.data.statusCode == 200) {
          that.onLoad();
          console.log("[home][onRequestUpdateItem]修改成功:", res)
        } else {
          console.log("[home][onRequestUpdateItem]修改失败:", res)
        }
      },
    })
  },
  //后端更新数据，只有更新添加事件的功能，发送到下一周期
  onRequestUpdateItemTime: function (e) {
    // console.log("[home][onRequestUpdateItemTime]e.detail:", e.detail)
    console.log("1")
    var that = this;
    const format = require("../../utils/util2.js");
    var today = (this.data.date == null ? format.formatTime1(new Date(), 'Y-M-D') : this.data.date);
    //var today = new Date();
    this.setData({
      date: today
    })
  
    console.log("[home][onRequestUpdateItemTime]更新数据库，被读取用户id", this.data.uid)
    wx.request({
      url: getApp().globalData.url+"/shiguangji_test2_war/listitemrest/optet/",
      method: 'PUT',
      data: {
        'liId': e.liId,
        'liMyDate': this.data.date,
        'liType': e.liType
      },
      header: {
        'content-type': 'application/x-www-form-urlencoded',// 默认值
        //"content-type": "multipart/form-data"
      },
      success(res) {
        if (res.data.statusCode == 200) {
          
          that.onLoad();
          console.log("[home][onRequestUpdateItemTime]修改成功:", res)
        } else {
          console.log("[home][onRequestUpdateItemTime]修改失败:", res)
        }
      },
    })
  },
  //后端更新数据，只有更新完成的功能
  onRequestUpdateItemFinish: function (e) {
    // console.log("[home][onRequestUpdateItem]e.detail:", e.detail)
    // console.log("[home][onRequestUpdateItem]e:", e)

   var that = this;
    console.log("[home][onRequestUpdateItemFinish]更新数据库-->事项完成，被读取用户id", this.data.uid)
    wx.request({
      url: getApp().globalData.url+"/shiguangji_test2_war/listitemrest/opt/",
      method: 'PUT',
      data: {
        'liId': e.liId,
        'liFinish': e.liFinish
      },
      header: {
        'content-type': 'application/x-www-form-urlencoded',// 默认值
        //"content-type": "multipart/form-data"
      },
      success(res) {
        if (res.data.statusCode == 200) {
          that.onLoad();
          console.log("[home][onRequestUpdateItem]修改成功:", res)
        } else {
          that.onLoad();
          console.log("[home][onRequestUpdateItem]修改失败:", res)
        }
      },
    })
  },
  //往后端插入事项
  onRequestInsertItem: function (e) {
    var that = this;
    console.log("[home][onRequestInsertItem]数据库插入数据，被读取用户id", this.data.uid)
    wx.request({
      url: getApp().globalData.url+"/shiguangji_test2_war/listitemrest/opt/",
      enctype: "multipart/form-data",
      method: 'POST',
      data: {
        'liId': e.liId,
        'liUid': this.data.uid,
        'liName': e.liName,
        'liInfo': e.liInfo,
        'liType': e.liType,
        'liImportant': e.liImportant,
        'liFinish': e.liFinish,
        'liMyDate': this.data.date
      },
      header: {
        'content-type': 'application/x-www-form-urlencoded',// 默认值
        //"content-type": "multipart/form-data"
      },
      success(res) {
        if (res.data.statusCode == 200) {
          console.log("[home][onRequestInsertItem]插入成功:", res)
          that.onLoad();
        } else {
          console.log("[home][onRequestInsertItem]插入失败:", res)
        }
      },
    })
  },
  //从后端删除事项
  onRequestDeleteItem: function (e) {
    var that = this;
    console.log("[home][onRequestDeleteItem]从后端删除", e.detail)
    wx.request({
      url: getApp().globalData.url+"/shiguangji_test2_war/listitemrest/opt/" + e.detail,
      method: 'DELETE',
      data: {
      },
      header: {
        'content-type': 'application/x-www-form-urlencoded',// 默认值
        // "content-type":"multipart/form-data"
      },
      success(res) {
        if (res.data.statusCode == 200) {
          that.onLoad();
          console.log("[home][onRequestDeleteItem]删除成功", e.detail)
        } else {
          console.log("[home][onRequestDeleteItem]删除失败", e.detail)
        }
      }
    })
    that.onLoad();
  },
  //向后端获取item数量
  onRequestNums: function () {
    var that = this;
    var date = this.data.date
    var myuid = this.data.uid;

    console.log("[home][onRequestNums]date:", this.data.date)
      wx.request({
        url: getApp().globalData.url+"/shiguangji_test2_war/listitemrest/optn/" + myuid + "/" + date,
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
            console.log("[charts]][onRequestNums]读取成功", res.data.dataZone)
          } else {
            console.log("charts][onRequestNums]读取失败", res.data.dataZone)
          }
        },
      })
  },
})