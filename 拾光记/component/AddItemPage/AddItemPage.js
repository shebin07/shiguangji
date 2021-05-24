// component/AddItemPage/AddItemPage.js
Component({
  /**
   * 组件的属性列表
   */
  properties: {
    Item: {
      type: Object,
      value: {
        liName: {
          type: String,
          value: null
        },
        liId: {
          type: Number,
          value: -1
        },
        liFinish: {
          type: Boolean,
          value: false
        },
        liType: {
          type: String,
          value: "day"
        },
        liInfo: {
          type: String,
          value: ""
        },
        liImportant:{
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
    liTypes: ["month", "week", "day"],
    liImportants: ["普通", "重要", "非常重要"],
    liTypeIndex:0,
    liImportantIndex: 0,
    liName:null,
    liId:-1,
    liFinish: false,
    liType:"day",
    liInfo:""
  },
  //切换时刷新
  attached: function (e) {
    if (this.properties.Item != null){
      if (this.properties.Item.liId != -1 || this.properties.Item.liId != null){
        console.log("[addItemPage][attached]接收参数Item", this.properties.Item)
       
        this.setData({
          liId: this.properties.Item.liId,
          liName: this.properties.Item.liName,
          liType: this.properties.Item.liType,
          liInfo: this.properties.Item.liInfo,
          liFinish: this.properties.Item.liFinish,
          liImportantIndex: this.properties.Item.liImportant,
        })

        this.data.liTypeIndex = (this.properties.Item.liType == 'month' ? 0 : (this.properties.Item.liType == 'week' ? 1 : 2));
      }
    }
  },
  /**
   * 组件的方法列表
   */
  methods: {
    onTypePickerChange: function (e) {
      this.setData({
        liTypeIndex: e.detail.value,
        liType: this.data.liTypes[e.detail.value],
      })
      console.log('[addItemPage][onTypePickerChange]type类型picker改变：改变为', this.data.liType)
    },
    //重要新picker改变
    onImportantPickerChange:function(e){
      this.setData({
        liImportantIndex: e.detail.value,
      })
      console.log('[addItemPage][onImportantPickerChange]important类型picker改变：改变为', this.data.liImportants[this.data.liImportantIndex])
    },

    onNameInputFocused:function(e){

    },
    onNameInputBlured: function (e) {
      this.setData({
        liName: e.detail.value
      })
      console.log('[addItemPage][onNameInputBlured]NameInput输入：输入内容为：', e.detail.value)
    },
    onInfoInputFocused: function (e) {
    },
    onInfoInputBlured: function (e) {
      this.setData({
        liInfo: e.detail.value
      })
      console.log('[addItemPage][onInfoInputBlured]InfoInput输入：输入内容为：', e.detail.value)
    },
    onClearInfo:function(){
      var newItem = this.properties.Item;
      if (this.properties.Item!=null){
        if (this.properties.Item.liId != -1) {
          newItem.liId = -1;
        }
      }
      this.setData({
        liName: null,
        liInfo: null,
        liType: "day",
        liId: -1,
        liFinish: false,
        Item:newItem,
        liTypeIndex:0,
        liImportantIndex:0
      })
      console.log("[addItemPage][onClearInfo]退出页面，清空变量",this.properties.Item)
    },
    onChangeNo: function () {
      this.onClearInfo();
      console.log('[addItemPage][onChangeNo]变更事项：取消修改，不保存')
      this.triggerEvent('onChangeOver')
     
    },
    onChangeYes: function (e) {
      if(this.data.liName==null){
        wx.showToast({
          title: '请输入事项名称',
          icon: 'none',
          duration: 1000,
          mask: true
        })
      }else{

        console.log('[addItemPage][onChangeYes]变更事项：确定修改，保存', { liId: this.data.liId, liName: this.data.liName, liType: this.data.liType, liInfo: this.data.liInfo,liImportant:this.data.liImportantIndex })
        this.triggerEvent('onChangeOver', { liId: this.data.liId, liName: this.data.liName, liType: this.data.liType, liInfo: this.data.liInfo, liFinish: this.data.liFinish, liImportant: this.data.liImportantIndex })
        this.onClearInfo();
        if(this.properties.Item!=null){
          wx.showToast({
            title: '事项修改成功！',
            icon: 'success',
            duration: 1000,
            mask: true
          })
        }else{
          wx.showToast({
            title: '事项添加成功！',
            icon: 'success',
            duration: 1000,
            mask: true
          })
        }
        
      }
    },
    
  },
})
