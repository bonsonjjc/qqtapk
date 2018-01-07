package com.bonson.qqtapk.model.bean;

/**
 * Created by jiangjiancheng on 17/12/31.
 */

public class SafeArea extends Base {
    private String fid;
    private String fx;
    private String fy;
    private String fradius;
    private String fstate;
    private String fbid;

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getFbid() {
        return fbid;
    }

    public void setFbid(String fbid) {
        this.fbid = fbid;
    }

    public String getFx() {
        return fx;
    }

    public void setFx(String fx) {
        this.fx = fx;
    }

    public String getFy() {
        return fy;
    }

    public void setFy(String fy) {
        this.fy = fy;
    }

    public String getFradius() {
        return fradius;
    }

    public void setFradius(String fradius) {
        this.fradius = fradius;
    }

    public String getFstate() {
        return fstate;
    }

    public void setFstate(String fstate) {
        this.fstate = fstate;
    }
}
