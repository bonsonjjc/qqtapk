package com.bonson.qqtapk.model.bean;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "babys")
public class Baby extends Base {
    @PrimaryKey
    @NonNull
    private String fid;
    private String fname;
    private String fimg;
    private String faccount;
    private String fpasswd;
    private String fuser;
    private String fimei;
    private String frelative;
    private String fsex;
    private String fbirth;
    private String fheight;
    private String fweight;
    private String fisadmin;// 是否管理员 0 是 1否
    private String fbtname; // 蓝牙名称
    private String ftmobile; // 宝贝手机号码
    private String fprovince; // 所在 省
    private String fcity; // 市
    private String farea; // 区
    private String fareaname; // 区域名称
    private String fmenus;
    private String ftag;
    private String furl;

    @NonNull
    public String getFid() {
        return fid;
    }

    public void setFid(@NonNull String fid) {
        this.fid = fid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getFimg() {
        return fimg;
    }

    public void setFimg(String fimg) {
        this.fimg = fimg;
    }

    public String getFaccount() {
        return faccount;
    }

    public void setFaccount(String faccount) {
        this.faccount = faccount;
    }

    public String getFpasswd() {
        return fpasswd;
    }

    public void setFpasswd(String fpasswd) {
        this.fpasswd = fpasswd;
    }

    public String getFuser() {
        return fuser;
    }

    public void setFuser(String fuser) {
        this.fuser = fuser;
    }

    public String getFimei() {
        return fimei;
    }

    public void setFimei(String fimei) {
        this.fimei = fimei;
    }

    public String getFrelative() {
        return frelative;
    }

    public void setFrelative(String frelative) {
        this.frelative = frelative;
    }

    public String getFsex() {
        return fsex;
    }

    public void setFsex(String fsex) {
        this.fsex = fsex;
    }

    public String getFbirth() {
        return fbirth;
    }

    public void setFbirth(String fbirth) {
        this.fbirth = fbirth;
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

    public String getFisadmin() {
        return fisadmin;
    }

    public void setFisadmin(String fisadmin) {
        this.fisadmin = fisadmin;
    }

    public String getFbtname() {
        return fbtname;
    }

    public void setFbtname(String fbtname) {
        this.fbtname = fbtname;
    }

    public String getFtmobile() {
        return ftmobile;
    }

    public void setFtmobile(String ftmobile) {
        this.ftmobile = ftmobile;
    }

    public String getFprovince() {
        return fprovince;
    }

    public void setFprovince(String fprovince) {
        this.fprovince = fprovince;
    }

    public String getFcity() {
        return fcity;
    }

    public void setFcity(String fcity) {
        this.fcity = fcity;
    }

    public String getFarea() {
        return farea;
    }

    public void setFarea(String farea) {
        this.farea = farea;
    }

    public String getFareaname() {
        return fareaname;
    }

    public void setFareaname(String fareaname) {
        this.fareaname = fareaname;
    }

    public String getFmenus() {
        return fmenus;
    }

    public void setFmenus(String fmenus) {
        this.fmenus = fmenus;
    }

    public String getFtag() {
        return ftag;
    }

    public void setFtag(String ftag) {
        this.ftag = ftag;
    }

    public String getFurl() {
        return furl;
    }

    public void setFurl(String furl) {
        this.furl = furl;
    }

    public static Baby baby;
}
