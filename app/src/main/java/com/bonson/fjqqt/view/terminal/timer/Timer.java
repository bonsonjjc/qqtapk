package com.bonson.fjqqt.view.terminal.timer;

import android.text.TextUtils;

import com.bonson.qqtapk.model.bean.Base;

public class Timer extends Base {
    private String fid;
    private String ftmobile;
    private String ftimes;
    private String futime;
    private String fctime;

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

    public String getFutime() {
        return futime;
    }

    public void setFutime(String futime) {
        this.futime = futime;
    }

    public String getFctime() {
        return fctime;
    }

    public void setFctime(String fctime) {
        this.fctime = fctime;
    }

    public boolean isOpen() {
        return !"00:00".equals(ftimes);
    }
    public void setOpen(boolean open){
        ftimes="00:00";
    }
}
