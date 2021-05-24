package team.sgj.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import team.sgj.domain.*;
import team.sgj.service.ListItemService;
import team.sgj.service.UserService;
import team.sgj.utils.FileUploadUtil;
import team.sgj.utils.MessageAndData;
import team.sgj.utils.StringToDateConvert;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@Controller
@RequestMapping("/listitemrest")
public class ListItenRestController {
    @Autowired
    private ListItemService itemService;

    @ResponseBody
    @RequestMapping(value = "/opt/{id}", method = RequestMethod.GET)
    public MessageAndData optSelectPrimaryKey(@PathVariable("id") Integer id) {
        ListItem obj = itemService.selectByPrimaryKey(id);
        return MessageAndData.success("查询成功").add("obj", obj);
    }

    @ResponseBody
    @RequestMapping(value = "/optu/{uid}", method = RequestMethod.GET)
    public MessageAndData optSelectUserId(@PathVariable("uid") Integer id) {
        List<ListItem> obj = itemService.selectByUserId(id);
        return MessageAndData.success("查询成功").add("obj", obj);
    }
    @ResponseBody
    @RequestMapping(value = "/optn/{uid}/{day}", method = RequestMethod.GET)
    public MessageAndData optSelectNum(@PathVariable("uid") Integer uid,@PathVariable("day") String day) {
        List<ListItem> objs = (List<ListItem>) this.optSelectExample(uid,day).getDataZone().get("obj");
        System.out.println("objs="+objs);
        int day_all=0,day_finish=0,week_all=0,week_finish=0,month_all=0,month_finish=0;
        ListItem temp;
        for(int i=0;i<objs.size();++i){
            temp = objs.get(i);
            String type = temp.getLiType();
            boolean finish = temp.getLiFinish();
            if(type.equals("day")){
                day_all++;
                if(temp.getLiFinish()== true){
                    day_finish++;
                    System.out.println("day_finish++");
                }
            }else if(type.equals("week")){
                week_all++;
                if(temp.getLiFinish()== true){
                    week_finish++;
                    System.out.println("week_finish++");
                }
            }else if(type.equals("month")){
                month_all++;
                if(temp.getLiFinish()== true){
                    month_finish++;
                    System.out.println("month_finish++");
                }
            }
        }
        MessageAndData mess = MessageAndData.success("查询成功");
        mess.add("dayAll", day_all);
        mess.add("dayFinish", day_finish);
        mess.add("weekAll", week_all);
        mess.add("weekFinish", week_finish);
        mess.add("monthAll", month_all);
        mess.add("monthFinish", month_finish);
        System.out.println("day,week,month="+day_all+" "+day_finish+", "+week_all+" "+week_finish+", "+month_all+" "+month_finish);
        return mess;
    }

    @ResponseBody
    @RequestMapping(value = "/opte/{uid}/{day}", method = RequestMethod.GET)
    public MessageAndData optSelectExample(@PathVariable("uid") Integer uid,@PathVariable("day") String day) {
        //Integer uid = Integer.parseInt(param.substring(0,param.indexOf("&")-1));
        Locale.setDefault(Locale.getDefault());
        String day1 = day;
        String day2 = day.concat(" 23:59:59");
        Calendar calendar = new GregorianCalendar();

        //System.out.println("day="+day);
        //System.out.println("day1="+day1);
        //System.out.println("day2="+day2);
        StringToDateConvert dateCvt = new StringToDateConvert();

        SimpleDateFormat f1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat f2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date today1;
        Date today2;
        Date today0;

        //System.out.println("today1="+today1);
        //System.out.println("today2="+today2);

        ListItemExample example1 = new ListItemExample();
        ListItemExample example2 = new ListItemExample();
        ListItemExample example3 = new ListItemExample();
        ListItemExample.Criteria criteria1 = example1.createCriteria();
        ListItemExample.Criteria criteria2 = example2.createCriteria();
        ListItemExample.Criteria criteria3 = example3.createCriteria();

        List<Integer> uidList = new ArrayList<Integer>(1);
        List<String> litypeList = new ArrayList<String>(1);
        uidList.add(uid);

        criteria1.andLiUidIn(uidList);
        criteria2.andLiUidIn(uidList);
        criteria3.andLiUidIn(uidList);
        //查找日数据
        litypeList.add("day");
        System.out.print("litypeList="+litypeList+"\n");
        criteria1.andLiTypeIn(litypeList);
        try {
            today1= f1.parse(day1);
        } catch (ParseException e) {today1=null;}
        try {
            today2= f2.parse(day2);
        } catch (ParseException e) {today2=null;}

        criteria1.andLiAddTimeBetween(today1,today2);
        example1.setOrderByClause("li_important desc");
        List<ListItem> obj = itemService.selectByExample(example1);

        System.out.print("day:today1 today2:"+today1+" "+today2+"\n");

        //查找周数据
        litypeList.clear();
        litypeList.add("week");
        criteria2.andLiTypeIn(litypeList);
        int dayWeek = calendar.get(Calendar.DAY_OF_WEEK);
        //周第一天
        //calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setTime(today1);
        calendar.set(Calendar.DAY_OF_WEEK, 1);
        today0=calendar.getTime();
        //周最后一天
        //calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setTime(today1);
        calendar.set(Calendar.DAY_OF_WEEK, 1);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 6);
        calendar.add(calendar.DATE,1);
        calendar.add(Calendar.SECOND, -1);
        today2=calendar.getTime();
        //赋值查询
        criteria2.andLiAddTimeBetween(today0,today2);
        example2.setOrderByClause("li_important desc");
        obj.addAll(itemService.selectByExample(example2));
        System.out.print("week:today0 today2:"+today0+today2+"\n");

        //查找月数据
        litypeList.clear();
        litypeList.add("month");

        criteria3.andLiTypeIn(litypeList);
        // 获取前月的第一天
        //calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setTime(today1);

        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        today0 = calendar.getTime();
        // 获取前月的最后一天
        //calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setTime(today1);

        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        calendar.add(calendar.DATE,1);
        calendar.add(Calendar.SECOND, -1);
        today2 = calendar.getTime();

        System.out.print("month:today0 today2:"+today0+" "+today2+"\n");
        criteria3.andLiAddTimeBetween(today0,today2);
        example3.setOrderByClause("li_important desc");
        obj.addAll(itemService.selectByExample(example3));

        System.out.print("objs:"+obj+"\n");
        return MessageAndData.success("查询成功").add("obj", obj);
    }


    @ResponseBody
    @RequestMapping(value = "/optet", method = RequestMethod.PUT)
    public MessageAndData optUpdateTime(ListItem obj) throws IOException {


        if(obj.getLiMyDate()!=null){
            SimpleDateFormat f1 = new SimpleDateFormat("yyyy-MM-dd");
            Date today1;
            try {
                today1= f1.parse(obj.getLiMyDate());
            } catch (ParseException e) {
                today1=null;
            }

            Locale.setDefault(Locale.getDefault());
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(today1);
            System.out.print("getLiType()="+obj.getLiType()+"/n");
            System.out.print("if true?="+(obj.getLiType()=="day")+"/n");
            if(obj.getLiType().equals("day")){
                calendar.add(calendar.DATE,1); //把日期往后增加一天,整数  往后推,负数往前移动
                today1=calendar.getTime();
                System.out.println("match day");
                System.out.println("match day:after="+today1);
            }else if(obj.getLiType().equals("week")){
                int dayWeek = calendar.get(Calendar.DAY_OF_WEEK);
                calendar.add(calendar.DATE,7-dayWeek+2);
                today1=calendar.getTime();
                System.out.println("match week");
                System.out.println("match day:after="+today1);
            }else if(obj.getLiType().equals("month")){
                calendar.add(calendar.MONTH,1);
                today1=calendar.getTime();
                System.out.println("match month");
                System.out.println("match day:after="+today1);
            }

            obj.setLiAddTime(today1);

        }

        ListItem newobj = new ListItem();
        newobj.setLiId(obj.getLiId());
        newobj.setLiAddTime(obj.getLiAddTime());
        int i = itemService.updateByPrimaryKeySelective(newobj);
        if (i > 0) {
            return MessageAndData.success("成功修改" + i + "条日期");
        } else {
            return MessageAndData.error("修改失败");
        }
    }



    @ResponseBody
    @RequestMapping(value = "/opt", method = RequestMethod.POST)
    public MessageAndData optInsert(ListItem obj) throws IOException {
        //如果没有新的上传文件则不触发上传操作和改名操作
        /*if (!file.isEmpty()) {
            String up = FileUploadUtil.up(file);
            obj.setGavatar(up);
        }*/
        //MessageAndData.success(obj.getLiName());
        //MessageAndData.success(obj.getLiType());

        SimpleDateFormat f1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat f2 = new SimpleDateFormat("HH:mm:ss");
        LocalTime now= LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String time = now.format(formatter);


        if(obj.getLiMyDate()!=null){
            Date today1;
            String liDate = obj.getLiMyDate()+" "+time;
            try {
                today1= f1.parse(liDate);
            } catch (ParseException e) {
                today1=null;
            }
            obj.setLiAddTime(today1);
        }

        if(obj.getLiId()==-1) {
            obj.setLiId(null);
        }
        if(obj.getLiInfo()==""){
            obj.setLiInfo(null);
        }

        Integer i = itemService.insertSelective(obj);
        if (i > 0) {
            return MessageAndData.success("成功添加" + i + "条记录");
        } else {
            return MessageAndData.error("添加失败");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/opt", method = RequestMethod.PUT)
    public MessageAndData optUpdateRest(ListItem obj) throws IOException {

        if(obj.getLiMyDate()!=null){
            SimpleDateFormat f1 = new SimpleDateFormat("yyyy-MM-dd");
            Date today1;
            try {
                today1= f1.parse(obj.getLiMyDate());
            } catch (ParseException e) {
                today1=null;
            }
            obj.setLiAddTime(today1);
        }

        if(itemService.selectByPrimaryKey(obj.getLiId()).getLiFinish()==FALSE && obj.getLiFinish()==TRUE){
            obj.setLiFinishTimeAuto();//自动获取完成时间
        }
        if(obj.getLiInfo()==""){
            obj.setLiInfo(null);
        }
        int i = itemService.updateByPrimaryKeySelective(obj);
        if (i > 0) {
            return MessageAndData.success("成功修改" + i + "条记录");
        } else {
            return MessageAndData.error("修改失败");
        }
    }


    @ResponseBody
    @RequestMapping(value = "/opt/{ids}", method = RequestMethod.DELETE)
    public MessageAndData deletes(@PathVariable("ids") String ids) {
        //获取传递过来的uid列表,分解为一个集合对象
        List<Integer> iIds = new ArrayList<Integer>();
        String splitSymbol = "\\D";
        String[] sIds = ids.split(splitSymbol);
        Integer i = null;
        for (String sId : sIds) {
            iIds.add(Integer.parseInt(sId));
        }
        if (iIds.size() > 1) {//删除多条记录
            ListItemExample example = new ListItemExample();
            example.createCriteria().andLiIdIn(iIds);
            i = itemService.deleteByExample(example);
        } else {//删除一条记录
            i = itemService.deleteByPrimaryKey(iIds.get(0));
        }
        return MessageAndData.success("删除成功" + i + "条记录").add("num", i);
    }



}
