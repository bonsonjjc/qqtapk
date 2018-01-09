package com.bonson.qqtapk.model.bean;

/**
 * Created by jiangjiancheng on 17/12/31.
 */
public class Member extends Base {
    private String fid;
    private String fmobile;
    private String admin;
    private String fbid;
    private String fuid;
    private String fname;
    private String frelative; // '亲属关系',用于家庭成员列表展示

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getFmobile() {
        return fmobile;
    }

    public void setFmobile(String fmobile) {
        this.fmobile = fmobile;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getFbid() {
        return fbid;
    }


    public void setFbid(String fbid) {
        this.fbid = fbid;
    }

    public String getFuid() {
        return fuid;
    }

    public void setFuid(String fuid) {
        this.fuid = fuid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getFrelative() {
        return frelative;
    }

    public void setFrelative(String frelative) {
        this.frelative = frelative;
    }

    @Override
    public String toString() {
        return "Member{" +
                "fid='" + fid + '\'' +
                ", fmobile='" + fmobile + '\'' +
                ", admin='" + admin + '\'' +
                ", fbid='" + fbid + '\'' +
                ", fuid='" + fuid + '\'' +
                ", fname='" + fname + '\'' +
                ", frelative='" + frelative + '\'' +
                '}';
    }
}
