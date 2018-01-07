package com.bonson.qqtapk.model.bean;

import java.io.Serializable;

/**
 * @类名: Member
 * @描述: 家庭成员列表实体类
 * @作者: Su
 * @日期: 2015年5月29日
 */

public class Member extends Base {
    private String fid;
    private String fuser;
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

    public String getFuser() {
        return fuser;
    }

    public void setFuser(String fuser) {
        this.fuser = fuser;
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
                ", fuser='" + fuser + '\'' +
                ", fmobile='" + fmobile + '\'' +
                ", admin='" + admin + '\'' +
                ", fbid='" + fbid + '\'' +
                ", fuid='" + fuid + '\'' +
                ", fname='" + fname + '\'' +
                ", frelative='" + frelative + '\'' +
                '}';
    }
}
