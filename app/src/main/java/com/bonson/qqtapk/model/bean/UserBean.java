package com.bonson.qqtapk.model.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zjw on 2018/1/2.
 */

public class UserBean extends Base {
    private String fid;// 家长主键
    private String fmobile;// 账号
    private String fpasswd;// 密码
    private String ftoken;// 推送标识
    private String fisadmin;// 是否管理员 0 是 1否
    private String fbid;// 孩子主键
    private String fbimg;// 孩子头像
    private String frelative;// 关系
    private String fbname;// 昵称
    private String fbsex;// 性别
    private String fbbrith;// 生日
    private String fbheight;// 身高
    private String fbweight;// 体重
    private String fbidlist;// 孩子ID集合，分隔
    private String fbnamelist;// 孩子姓名集合，分隔
    private String fbimglist;// 孩子头像集合，分隔
    private String fbflower;// 小红花总数
    private String fbcstate;
    private String ftmobile; // 宝贝手机号码
    private String fprovince; // 所在 省
    private String fcity; // 市
    private String farea; // 区
    private String fareaname; // 区域名称
    private String fmenus;
    private String ftag;
    private String furl;

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

    public String getFpasswd() {
        return fpasswd;
    }

    public void setFpasswd(String fpasswd) {
        this.fpasswd = fpasswd;
    }

    public String getFtoken() {
        return ftoken;
    }

    public void setFtoken(String ftoken) {
        this.ftoken = ftoken;
    }

    public String getFisadmin() {
        return fisadmin;
    }

    public void setFisadmin(String fisadmin) {
        this.fisadmin = fisadmin;
    }

    public String getFbid() {
        return fbid;
    }

    public void setFbid(String fbid) {
        this.fbid = fbid;
    }

    public String getFbimg() {
        return fbimg;
    }

    public void setFbimg(String fbimg) {
        this.fbimg = fbimg;
    }

    public String getFrelative() {
        return frelative;
    }

    public void setFrelative(String frelative) {
        this.frelative = frelative;
    }

    public String getFbname() {
        return fbname;
    }

    public void setFbname(String fbname) {
        this.fbname = fbname;
    }

    public String getFbsex() {
        return fbsex;
    }

    public void setFbsex(String fbsex) {
        this.fbsex = fbsex;
    }

    public String getFbbrith() {
        return fbbrith;
    }

    public void setFbbrith(String fbbrith) {
        this.fbbrith = fbbrith;
    }

    public String getFbheight() {
        return fbheight;
    }

    public void setFbheight(String fbheight) {
        this.fbheight = fbheight;
    }

    public String getFbweight() {
        return fbweight;
    }

    public void setFbweight(String fbweight) {
        this.fbweight = fbweight;
    }

    public String getFbidlist() {
        return fbidlist;
    }

    public void setFbidlist(String fbidlist) {
        this.fbidlist = fbidlist;
    }

    public String getFbnamelist() {
        return fbnamelist;
    }

    public void setFbnamelist(String fbnamelist) {
        this.fbnamelist = fbnamelist;
    }

    public String getFbimglist() {
        return fbimglist;
    }

    public void setFbimglist(String fbimglist) {
        this.fbimglist = fbimglist;
    }

    public String getFbflower() {
        return fbflower;
    }

    public void setFbflower(String fbflower) {
        this.fbflower = fbflower;
    }

    public String getFbcstate() {
        return fbcstate;
    }

    public void setFbcstate(String fbcstate) {
        this.fbcstate = fbcstate;
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

    public User user() {
        User user = new User();
        user.setUserId(fid);
        user.setMobile(fmobile);
        user.setPassword(fpasswd);
        user.setBabyId(fbid);
        user.setAuto(true);
        user.setBabyList(babyList());
        return user;
    }

    public Baby baby() {
        Baby baby = new Baby();
        baby.setFaccount(fmobile);
        baby.setFbirth(fbbrith);
        baby.setFheight(fbheight);
        baby.setFid(fbid);
        baby.setFimg(fbimg);
        baby.setFname(fbname);
        baby.setFpasswd(fpasswd);
        baby.setFrelative(frelative);
        baby.setFsex(fbsex);
        baby.setFuser(fid);
        baby.setFweight(fbweight);
        baby.setFtmobile(ftmobile);
        baby.setFprovince(fprovince);
        baby.setFcity(fcity);
        baby.setFarea(farea);
        baby.setFareaname(fareaname);
        baby.setFisadmin(fisadmin);
        baby.setFmenus(fmenus);
        baby.setFtag(ftag);
        baby.setFurl(furl);
        return baby;
    }

    private List<Baby> babyList() {
        String bidlist[] = fbidlist.split(",");
        String bnamelist[] = fbnamelist.split(",");
        String bimglist[] = fbimglist.split(",");
        List<Baby> babies = new ArrayList<>();

        for (int i = 0; i < bidlist.length; i++) {
            Baby baby = new Baby();
            if (bidlist[i].equals(fbid)) {
                baby = baby();
            } else {
                baby.setFaccount(fmobile);
                baby.setFpasswd(fpasswd);
                baby.setFid(bidlist[i]);
                baby.setFname(bnamelist[i]);
                baby.setFimg(bimglist[i]);
                baby.setFuser(fid);
            }
            babies.add(baby);
        }
        return babies;
    }
}
