package team.sgj.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ListItem {
    private Integer liId;

    private Integer liUid;

    private String liName;

    private Boolean liFinish;

    private String liType;

    private String liInfo;

    private Date liAddTime;

    private Integer liImportant;

    private String liMyDate;
    private Date liFinishTime;

    public String getLiMyDate() {
        return liMyDate;
    }

    public void setLiMyDate() {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/mm/dd");
        this.liMyDate = formatter.format(liAddTime);
    }

    public void setLiMyDate(String date) {
        this.liMyDate = date;
    }


    public ListItem(Integer liId,Integer liUid,String liName,Boolean liFinish,String liType,String liInfo,Integer liImportant){
        this.liId = liId;
        this.liUid=liUid;
        this.liName = liName;
        this.liFinish = liFinish;
        this.liType = liType;
        this.liInfo = liInfo;
        //SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        this.liAddTime = new Date();// new Date()为获取当前系统时间
        this.liFinishTime=null;
        this.liImportant =liImportant;
    }
    public ListItem(Integer liId,Integer liUid,String liName,Boolean liFinish,String liType,Integer liImportant){
        this.liId = liId;
        this.liUid=liUid;
        this.liName = liName;
        this.liFinish = liFinish;
        this.liType = liType;
        this.liInfo = "";
        //SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        this.liAddTime = new Date();// new Date()为获取当前系统时间
        this.liFinishTime=null;
        this.liImportant =liImportant;
    }

    public ListItem() {
        this.liId = null;
        this.liUid= null;
        this.liName = null;
        this.liFinish = null;
        this.liType = null;
        this.liInfo = "";
        //SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        this.liAddTime = null;// new Date()为获取当前系统时间
        this.liFinishTime=null;
        this.liMyDate=null;
        this.liImportant=null;
    }

    public Integer getLiId() {
        return liId;
    }

    public void setLiId(Integer liId) {
        this.liId = liId;
    }

    public Integer getLiUid() {
        return liUid;
    }

    public void setLiUid(Integer liUid) {
        this.liUid = liUid;
    }

    public String getLiName() {
        return liName;
    }

    public void setLiName(String liName) {
        this.liName = liName;
    }

    public Boolean getLiFinish() {
        return liFinish;
    }

    public void setLiFinish(Boolean liFinish) {
        this.liFinish = liFinish;
    }

    public String getLiType() {
        return liType;
    }

    public void setLiType(String liType) {
        this.liType = liType;
    }

    public String getLiInfo() {
        return liInfo;
    }

    public void setLiInfo(String liInfo) {
        this.liInfo = liInfo;
    }

    public Date getLiAddTime() {
        return liAddTime;
    }

    public void setLiAddTime(Date liAddTime) {
        this.liAddTime = liAddTime;
    }
    public void setLiAddTime(String year,String month,String day) throws ParseException {
        String dateString = year+"-"+month+"-"+day;
        Date myDate= new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
        this.liAddTime = myDate;
    }
    public Date getLiFinishTime() {
        return liFinishTime;
    }

    public void setLiFinishTime(Date liFinishTime) {
        this.liFinishTime = liFinishTime;
    }

    public void setLiFinishTimeAuto() {
        this.liFinishTime = new Date();
    }

    public Integer getLiImportant() {
        return liImportant;
    }

    public void setLiImportant(int liImportant) {
        this.liImportant = liImportant;
    }
}