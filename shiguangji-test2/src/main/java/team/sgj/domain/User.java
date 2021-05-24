package team.sgj.domain;

import java.util.Date;

public class User {
    private Integer uid;

    private String username;

    private String password;

    private Date addTime;


    private String openId;

    public User(){};
    public User(Integer uid, String username, String password, Date addTime) {
        this.uid = uid;
        this.username = username;
        this.password=password;
        this.addTime = addTime;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

}