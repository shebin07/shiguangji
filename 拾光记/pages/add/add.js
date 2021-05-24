// pages/add/add.js

var plugin = requirePlugin("WechatSI")
let manager = plugin.getRecordRecognitionManager()
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    src:"/img/icon/mic1.png",
    content: null,
    index:null,
    text: '这里显示您的语音录入内容',
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (e) {
    console.log("[add][onLoad]e=", e)
    var memoInfo = e.mInfo;
    var mindex = e.index;
    console.log("[add][onLoad]memoInfo=", memoInfo)
    console.log("[add][onLoad]index=", mindex)
    if (mindex>=0 && mindex!=null) {
      this.setData({
        content:memoInfo,
        index:mindex
      })
    } else {
      this.setData({
        content:""
      })
    }
    //语音输入功能

    manager.onRecognize = function (res) {
      console.log("current result", res.result)
    };
    // 识别结束事件
    manager.onStop = (res) => {
      let text = res.result;
      if (text == "" || text == null) {
        wx.showToast({
          title: '识别内容为空,请说清楚',
        })
        this.setData({
          text: '这里显示您的语音录入内容',
        })
      } else {
        if (text.indexOf('小青') != -1) {
          text = '小青在呢，主人有什么吩咐';
        }
        if(this.data.content==null){
          this.setData({
            content:""
          })
        }
        var newcontent = '' + this.data.content + text;
        console.log("newcontent=",newcontent)
        this.setData({
          text: '',
          content:newcontent
        });
      }
    };
    //
    //识别错误时间
    manager.onError = function (res) {
      var text = '其他错误';
      if (res.retcode == -30001 || res.retcode == -30012) {
        text = '右上角进行设置';
        that.setData({
          text: '这里显示您的语音录入内容',
        })
      }
      wx.showToast({
        title: text,
        icon: 'none',
        duration: 3000,
      })
    }
  },
  textareaInput:function(e){
    this.setData({
      content: e.detail.value
    });
  },
  //开始录音
  postData: function () {
    manager.start({ duration: 30000, lang: "zh_CN" })
    this.setData({
      text: '正在聆听...',
      src:"/img/icon/mic2.png"
    });
    manager.onStart = function (res) {
      //console.log("成功开始录音识别", res)
    }
  },
  stopData: function () {
    manager.stop();
    this.setData({
      src: "/img/icon/mic1.png"
    });
  },



  //textarea完成输入
  submit:function(e){
    if (e.detail.value.textarea==""||e.detail.value.textarea==null) {
      console.log('不能为空');
      return;
    }

    console.log("[add][submit]e=", e)
    this.setData({
      content:e.detail.value.textarea
    })
    console.log("[add][submit]content=",this.data.content)
    this.sure();
  },

  /**
   * input change事件
   */
  change(e) {
    var val = e.detail.value;
    this.setData({
      content: val
    });
  },

  /**
   * cancel 事件
   */
  cancel() {
    wx.navigateBack();
  },

  sure:function() {
    
    var pages = getCurrentPages();
    var currPage = pages[pages.length - 1];   //当前页面
    var prevPage = pages[pages.length - 2];  //上一个页面
    var that = this;
    console.log("[add][sure]content=", this.data.content)
    //直接调用上一个页面对象的setData()方法，把数据存到上一个页面中去
    //修改操作
    var promise = new Promise(function (resolve, reject){
      if (that.data.index != null) {
        prevPage.setData({
          mindex: that.data.index,
          fmInfo: that.data.content,
          needUpdate: true
        });
        resolve();
      } else {//插入操作
        prevPage.setData({
          fmInfo: that.data.content,
          needUpdate: true
        });
        resolve();
      }
    })
      promise.then(res => {
      wx.navigateBack({
        delta: 1
      });
    })
      console.log("[add][sure]content=", this.data.content)
  }
})
