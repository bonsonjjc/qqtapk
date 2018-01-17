package com.bonson.fjqqt.model.bean;

import com.bonson.qqtapk.model.bean.Base;

public class Info extends Base {
    private String ftmobile;
    private String ftname;
    private String fsex;
    private String fbirthday;
    private String fheight;
    private String fweight;

    public String getFtmobile() {
        return ftmobile;
    }

    public void setFtmobile(String ftmobile) {
        this.ftmobile = ftmobile;
    }

    public String getFtname() {
        return ftname;
    }

    public void setFtname(String ftname) {
        this.ftname = ftname;
    }

    public String getFsex() {
        return fsex;
    }

    public void setFsex(String fsex) {
        this.fsex = fsex;
    }

    public String getFbirthday() {
        return fbirthday;
    }

    public void setFbirthday(String fbirthday) {
        this.fbirthday = fbirthday;
    }

    public String getFheight() {
        return fheight;
    }

    public void setFheight(String fheight) {
        this.fheight = fheight;
    }

    public String getFweight() {
        return fweight;
    }

    public void setFweight(String fweight) {
        this.fweight = fweight;
    }
}
