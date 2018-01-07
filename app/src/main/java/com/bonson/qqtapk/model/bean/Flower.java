package com.bonson.qqtapk.model.bean;

public class Flower extends Base {
    private String fid;

    private String fnum;

    private String ftype;

    private String fdesc;

    private String fctime;

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getFid() {
        return this.fid;
    }

    public void setFnum(String fnum) {
        this.fnum = fnum;
    }

    public String getFnum() {
        return this.fnum;
    }

    public void setFtype(String ftype) {
        this.ftype = ftype;
    }

    public String getFtype() {
        return this.ftype;
    }

    public void setFdesc(String fdesc) {
        this.fdesc = fdesc;
    }

    public String getFdesc() {
        return this.fdesc;
    }

    public void setFctime(String fctime) {
        this.fctime = fctime;
    }

    public String getFctime() {
        return this.fctime;
    }
}
