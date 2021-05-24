// component/TabPage/TabPage.js
Component({
  /**
   * 组件的属性列表
   */
  
  // 启用插槽
  options: {
    multipleSlots: true
  },
  properties: {

  },

  /**
   * 组件的初始数据
   */
  data: {
    open_calendar:false,
    show_page_month:false,
    show_page_week: false,
    show_page_day: true,
    
    tabMonth: "tabpage_tab_comp_1",
    tabWeek: "tabpage_tab_comp_1",
    tabDay: "tabpage_tab_comp_1_focused"
  },

  onFocusedDayChanged:function(){

  },
  /**
   * 组件的方法列表
   */
  methods: {
    changeToPageMonth:function(){
      console.log("[TabPage][changeToPageMonth]点击标签:月");
      console.log("[TabPage][changeToPageMonth]切换标签:月");
      this.setData({ 
        show_page_month: true,
        show_page_week:false,
        show_page_day:false,
        tabMonth: "tabpage_tab_comp_1_focused",
        tabWeek: "tabpage_tab_comp_1",
        tabDay: "tabpage_tab_comp_1"
      });
    },
     changeToPageWeek: function () {
       console.log("[TabPage][changeToPageWeek]点击标签:周");
       console.log("[TabPage][changeToPageWeek]切换标签:周");
       this.setData({
         show_page_month: false,
         show_page_week: true,
         show_page_day: false,
         tabMonth: "tabpage_tab_comp_1",
         tabWeek: "tabpage_tab_comp_1_focused",
         tabDay: "tabpage_tab_comp_1"
       });
    },
     changeToPageDay: function () {
       console.log("[TabPage][changeToPageDay]点击标签:日");
       console.log("[TabPage][changeToPageDay]切换标签:日");
       this.setData({
         show_page_month: false,
         show_page_week: false,
         show_page_day: true,
         tabMonth: "tabpage_tab_comp_1",
         tabWeek: "tabpage_tab_comp_1",
         tabDay: "tabpage_tab_comp_1_focused"
       });
    },
    refreshCalendar:function(){
      this.selectComponent("#myCalendar").onLoad();
    },

    onCalendarCloseChange:function(e){
      this.setData({
        open_calendar:!this.data.open_calendar
      })
      console.log("[TabPage][onCalendarCloseChange]切换事项样式从而改变空白高度")
    },
    onFocusedDayChanged:function(e){
      this.triggerEvent("onFocusedDayChanged",e.detail)
      console.log("[TabPage][onFocusedDayChanged]e.detail:",e.detail)

    }
  }
})
