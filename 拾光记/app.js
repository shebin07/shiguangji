// app.js
App({
  onLaunch() {
    this.initLaunch();
  },
  
  onLoad: function (options) {
   
  },
  initLaunch:function(){
    // 展示本地存储能力
    const logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)
    var that = this;

    wx.showModal({
      title: '登录授权',
      content: '请问是否允许使用微信信息登录？',
      success: function (e) {
        if (wx.getUserProfile) {
          that.globalData.canIUseGetUserProfile = true;
        }
        canIUse: wx.canIUse('button.open-type.getUserInfo');
        canIUseOpenData: wx.canIUse('open-data.type.userAvatarUrl') && wx.canIUse('open-data.type.userNickName')
        // 如需尝试获取用户信息可改为false
        if (e.confirm) {
          //登录
          wx.login({
            success: function (res) {
              console.log("success:", res)
              that.onRequestLogin(res);
             // console.log("111111111111111111111111111111111+uid", that.globalData.uid)
            },
            fail: function (res) {
              console.log("fail:", res)
            },
            complete: function (res) {
              console.log("complete:", res)
              that.onLoginComplete(res);
            },
          })
        } else if (e.cancel) {
          that.globalData.uid = -1;
        }
      },
      fail: function (e) {
        that.globalData.canIUseGetUserProfile = false;
        canIUse: false;
        canIUseOpenData: false;
      }
    })
},
  
onRequestLogin: function(res){
      var that = this;
      // 登录
      console.log("[app][onRequestLogin]res=", res)
   
      if (res.code) {
        var APPID = "wx3f864af2e45ade45";
        var APPSecret = "67c6ff99b6052a84839a224bf7c8974f";
        var code = res.code
        //发起网络请求
        wx.request({
          url: this.globalData.url+'/shiguangji_test2_war/userrest/login/' + code + "$" + APPID + "$" + APPSecret,
          data: {
          },
          method: 'POST',
          dataType: 'json',
          success: function (res) {
            var myres = res;
            that.onRequestGetUserInfo().then(function () {
              if (myres.data.uid == -1) {
                console.log("开始插入新用户")
                that.onRequestAddUser(myres.data.openid);
              } else {
                //设置uid
                that.globalData.uid = myres.data.uid;
                that.globalData.checkLogin=true;
                if (that.checkLoginReadyCallback) {
                  that.checkLoginReadyCallback(res);
                }
              }
            })
          }
        })
      } else {
        console.log('[app][onLaunch]登录失败！' + res.errMsg)
        return
      }   
},

//当获取到用户的openid和用户信息之后，赋值uid或者创建新用户
onLoginComplete:function(res){
  console.log("[app][onLoginComplete]")
},
//获取用户名和密码
  onRequestGetUserInfo:function(){
   var that = this;
    return new Promise(function (resolve, reject) {
   wx.getUserInfo({
     success: function (res) {
      console.log("[app][getUserInfo]用户名称：", res.userInfo.nickName)
       that.globalData.userInfo.nickName = res.userInfo.nickName,
       that.globalData.userInfo.avatarUrl = res.userInfo.avatarUrl,
         //console.log("avaterUrl=", that.globalData.userInfo.avatarUrl)
       resolve(res)
     }
   })
  }) 
 },
  onRequestAddUser: function (res) {
    console.log(res);
    var that = this;
    wx.request({
      url: this.globalData.url+"/shiguangji_test2_war/userrest/opt/",
      method: 'POST',
      data: {
        openId: res,
        username:that.globalData.userInfo.nickName,
        password:""          
      },
      header: {
        'content-type': 'application/x-www-form-urlencoded',// 默认值
        //"content-type": "multipart/form-data"
      },
      success(res) {
        that.globalData.uid = res;
        console.log("[app][onRequestAddUser]插入成功，res=",res)
      }
    })
  },

  globalData: {
    checkLogin:false,
    canIUseGetUserProfile:false,
    canIUseOpenData:false,
    canIUse:false,
    userInfo: {
      nickName:"请登录",
      avatarUrl: "img/icon/me_before.png"
    },
    uid:-2,
    url:"http://172.19.33.129:8088"
  }
})
