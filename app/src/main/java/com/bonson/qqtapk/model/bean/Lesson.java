package com.bonson.qqtapk.model.bean;

public class Lesson extends Base {
    private String fid;
    private String fbegin;
    private String fend;
    private String fstate;

    private boolean open;

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getFbegin() {
        return fbegin;
    }

    public void setFbegin(String fbegin) {
        this.fbegin = fbegin;
    }

    public String getFend() {
        return fend;
    }

    public void setFend(String fend) {
        this.fend = fend;
    }

    public String getFstate() {
        return fstate;
    }

    public void setFstate(String fstate) {
        this.fstate = fstate;
        if ("0".equals(fstate)) {
            setOpen(true);
        } else {
            setOpen(false);
        }

    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean isOpened) {
        this.open = isOpened;
    }

    @Override
    public String toString() {
        return "Lesson [fid=" + fid + ", fbegin=" + fbegin + ", fend=" + fend + ", fstate=" + fstate + ", 是否开启=" + open + "]";
    }

}
