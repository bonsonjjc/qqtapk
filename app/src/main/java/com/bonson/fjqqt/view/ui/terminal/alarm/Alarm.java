package com.bonson.fjqqt.view.ui.terminal.alarm;

import com.bonson.qqtapk.model.bean.Base;

public class Alarm extends Base {
    private String fid;
    private String ftmobile;
    private String ftimes;
    private String fstate;
    private String fcycle;
    private String fcontent;

    private String ftype;

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getFtmobile() {
        return ftmobile;
    }

    public void setFtmobile(String ftmobile) {
        this.ftmobile = ftmobile;
    }

    public String getFtimes() {
        return ftimes;
    }

    public void setFtimes(String ftimes) {
        this.ftimes = ftimes;
    }

    public String getFstate() {
        return fstate;
    }

    public void setFstate(String fstate) {
        this.fstate = fstate;
    }

    public String getFcontent() {
        return fcontent;
    }

    public void setFcontent(String fcontent) {
        this.fcontent = fcontent;
    }

    public String getFcycle() {
        return fcycle;
    }

    public void setFcycle(String fcycle) {
        this.fcycle = fcycle;
    }

    public String getFtype() {
        return ftype;
    }

    public void setFtype(String ftype) {
        this.ftype = ftype;
    }
}
