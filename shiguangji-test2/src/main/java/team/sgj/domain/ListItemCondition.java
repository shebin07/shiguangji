package team.sgj.domain;

import java.io.Serializable;

public class ListItemCondition extends ListItem implements Serializable {
    private Integer liIdCondition;
    private Integer liUidCondition;
    private String liTypeCondition;
    private boolean liFinishCondition;
    private String liInfo;

    private Integer liImportant;


    public ListItemCondition() {
    }

    public ListItemCondition(Integer liId,Integer liUid,String liName,Boolean liFinish,String liType,String liInfo,Integer liImportant){
        super(liId,liUid, liName, liFinish, liType,liInfo,liImportant);
        this.liIdCondition = liIdCondition;
        this.liUidCondition = liUidCondition;
        this.liTypeCondition = liTypeCondition;
        this.liFinishCondition = liFinishCondition;
        this.liInfo = liInfo;
        this.liImportant = liImportant;
    }

    @Override
    public String toString() {
        return "ListItemCondition{" +
                "liIdCondition=" + liIdCondition +
                ",liUidCondition=" + liUidCondition +
                ", liTypeCondition=" + liTypeCondition +
                ",liTypeCondition=" + liTypeCondition +
                ", liFinishCondition=" + liFinishCondition +
                ",liImportantCondition="+liImportant+
                "} " + super.toString();
    }

    public Integer getliUidCondition() {
        return liUidCondition;
    }
    public Integer getLiIdCondition() {
        return liIdCondition;
    }
    public void setLiIdCondition(Integer liIdCondition) { this.liIdCondition = liIdCondition; }
    public void setliUidCondition(Integer liUidCondition) {
        this.liUidCondition = liUidCondition;
    }
    public String getLiTypeCondition(){ return liTypeCondition; }
    public void setLiTypeCondition(String liTypeCondition) { this.liTypeCondition = liTypeCondition; }

    public boolean isLiFinishCondition() { return liFinishCondition; }

    public void setLiFinishCondition(boolean liFinishCondition) { this.liFinishCondition = liFinishCondition; }
    public String getLiInfo() {
        return liInfo;
    }
    public void setLiInfo(String liInfo) {
        this.liInfo = liInfo;
    }

    public Integer getLiImportant() {
        return liImportant;
    }

    public void setLiImportant(Integer liImportant) {
        this.liImportant = liImportant;
    }

}
