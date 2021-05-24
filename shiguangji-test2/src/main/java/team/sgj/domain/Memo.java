package team.sgj.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Memo {
    private Integer mId;

    private Integer mUid;

    private String mInfo;

    private Date mAddTime;

    private String mAddDate;

    public Integer getmId() {
        return mId;
    }

    public void setmId(Integer mId) {
        this.mId = mId;
    }

    public Integer getmUid() {
        return mUid;
    }

    public void setmUid(Integer mUid) {
        this.mUid = mUid;
    }

    public String getmInfo() {
        return mInfo;
    }

    public void setmInfo(String mInfo) {
        this.mInfo = mInfo;
    }

    public Date getmAddTime() {
        return mAddTime;
    }

    public void setmAddTime(Date mAddTime) {

        this.mAddTime = mAddTime;
        this.setmAddDate();
    }

    public String getmAddDate() {
        return mAddDate;
    }

    public void setmAddDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        this.mAddDate = formatter.format(mAddTime);
    }
}