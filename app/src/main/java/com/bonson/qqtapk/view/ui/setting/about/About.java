package com.bonson.qqtapk.view.ui.setting.about;

import com.bonson.qqtapk.model.bean.Base;

public class About extends Base{
    private String fid = "";
    private String farea = "";
    private String ftel = "";
    private String furl = "";

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getFarea() {
        return farea;
    }

    public void setFarea(String farea) {
        this.farea = farea;
    }

    public String getFtel() {
        return ftel;
    }

    public void setFtel(String ftel) {
        this.ftel = ftel;
    }

    public String getFurl() {
        return furl;
    }

    public void setFurl(String furl) {
        this.furl = furl;
    }
}
