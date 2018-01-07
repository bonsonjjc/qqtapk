package com.bonson.qqtapk.model.bean;


public class Voice extends Base {
    private String fid; // 主键

    private String ftype; // 消息类型0主卡单聊，1家庭群聊，2好友单聊，3好友群聊

    private String fuser; // 发送人ID

    private String ftuser; // 消息接受人ID

    private String fpath; // 文件路径

    private String fcontent; // 文字内容（预留字段）

    private String freadstate; // 读取状态0未读1已读

    private String fctime; // 发送时间

    private String tran_no; // 交易流水号

    private String fparentname; // 家长名称

    private String fbabyname; // 宝贝名称

    private String ftime; // 语音时长

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getFtype() {
        return ftype;
    }

    public void setFtype(String ftype) {
        this.ftype = ftype;
    }

    public String getFuser() {
        return fuser;
    }

    public void setFuser(String fuser) {
        this.fuser = fuser;
    }

    public String getFtuser() {
        return ftuser;
    }

    public void setFtuser(String ftuser) {
        this.ftuser = ftuser;
    }

    public String getFpath() {
        return fpath;
    }

    public void setFpath(String fpath) {
        this.fpath = fpath;
    }

    public String getFcontent() {
        return fcontent;
    }

    public void setFcontent(String fcontent) {
        this.fcontent = fcontent;
    }

    public String getFreadstate() {
        return freadstate;
    }

    public void setFreadstate(String freadstate) {
        this.freadstate = freadstate;
    }

    public String getFctime() {
        return fctime;
    }

    public void setFctime(String fctime) {
        this.fctime = fctime;
    }

    public String getTran_no() {
        return tran_no;
    }

    public void setTran_no(String tran_no) {
        this.tran_no = tran_no;
    }

    public String getFparentname() {
        return fparentname;
    }

    public void setFparentname(String fparentname) {
        this.fparentname = fparentname;
    }

    public String getFbabyname() {
        return fbabyname;
    }

    public void setFbabyname(String fbabyname) {
        this.fbabyname = fbabyname;
    }

    public String getFtime() {
        return ftime;
    }

    public void setFtime(String ftime) {
        this.ftime = ftime;
    }
}
