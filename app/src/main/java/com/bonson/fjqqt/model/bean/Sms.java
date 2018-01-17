package com.bonson.fjqqt.model.bean;

import com.bonson.qqtapk.model.bean.Base;

public class Sms extends Base {
    private String ftmobile;
    private String fmsgid;
    private String ftype;
    private String fctime;

    public String getFtmobile() {
        return ftmobile;
    }

    public void setFtmobile(String ftmobile) {
        this.ftmobile = ftmobile;
    }

    public String getFmsgid() {
        return fmsgid;
    }

    public void setFmsgid(String fmsgid) {
        this.fmsgid = fmsgid;
    }

    public String getFctime() {
        return fctime;
    }

    public void setFctime(String fctime) {
        this.fctime = fctime;
    }

    public String getFtype() {
        return ftype;
    }

    public void setFtype(String ftype) {
        this.ftype = ftype;
    }
}
