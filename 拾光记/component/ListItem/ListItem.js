// component/ListItem.js
Component({
  /**
   * 组件的属性列表
   */
  properties: {
    index:null,
    Item:{
      type:Object,
      value:{
        liName:{
          type:String,
          value: "default_liname"
        },
        liId:{
          type:Number,
          value:-1
        },
        liFinish:{
          type:Boolean,
          value:false
        },
        liType:{
          type:String,
          value: "day"
        },
        liInfo:{
          type:String,
          value:null
        }, 
        liImportant: {
          type: Number,
          value: -1
        }
      }
    }
  },

  /**
   * 组件的初始数据
   */
  data: {
  },
  
  /**
   * 组件的方法列表
   */
  
  methods: {
   
    onBoxClicked:function(e){
      
      var newItem=this.properties.Item;
      newItem.liFinish = !this.properties.Item.liFinish;

      this.setData({
        Item:newItem
      }),
        console.log("[ListItem][onBoxClicked]点击checkbox:checkbox状态切换为" + this.properties.Item.liFinish);
      this.triggerEvent('onBoxClicked', this.properties.Item)
    },
    
    onItemClicked:function(e){
      if (getApp().globalData.uid == -1) {
        wx.showToast({
          icon: 'none',
          title: '请登录！',
        })
      } else {
      //传回当前事项的id给父组件
      console.log("[ListItem][onItemClicked]当前被点击listitem index",this.properties.index)
      this.triggerEvent('onItemClicked',this.properties.index)
      }
    },
    //删除事项
    longPressed:function(e){
      var that = this
      wx.showActionSheet({
        itemList: ['发送到下一周期', '删除事项'],
        success: function (res) {
          console.log(JSON.stringify(res))
          console.log(res.tapIndex) // 用户点击的按钮，从上到下的顺序，从0开始
          if(res.tapIndex==0){
            console.log("发送到下一周期")
            that.triggerEvent("sendToNext",that.properties.Item)
          }else if(res.tapIndex==1){
            wx.showModal({
              title: '提示',
              content: '确定要删除此事项吗？',
              success: function (res) {
                if (res.confirm) {
                  console.log('[ListItem][longPressed]点击确定删除');
                  console.log("[ListItem][longPressed]删除当前事项")
                  that.triggerEvent('onItemDelete', that.properties.Item.liId)
                } else if (res.cancel) {
                  console.log('[ListItem][longPressed]点击取消删除');
                  return false;
                }
              }
            })
          }
        },
        fail: function (res) {
          console.log(res.errMsg)
        }
      })

      
      
    }
  }
})
