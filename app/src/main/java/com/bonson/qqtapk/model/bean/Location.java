package com.bonson.qqtapk.model.bean;

public class Location extends Base {

	private String fid;

	private String futime;

	private String fseqid;

	private String ftext;

	private String fx;

	private String fy;

	private String fbid;

	private String fctime;

	private String ftype;

	private String fshort;

	private String fmode;

	public void setFid(String fid) {
		this.fid = fid;
	}

	public String getFid() {
		return this.fid;
	}

	public void setFutime(String futime) {
		this.futime = futime;
	}

	public String getFutime() {
		return this.futime;
	}

	public void setFseqid(String fseqid) {
		this.fseqid = fseqid;
	}

	public String getFseqid() {
		return this.fseqid;
	}

	public void setFtext(String ftext) {
		this.ftext = ftext;
	}

	public String getFtext() {
		return this.ftext;
	}

	public void setFx(double fx) {
		this.fx = fx+"";
	}

	public double getFx() {
		return Double.valueOf(fx);
	}

	public void setFy(double fy) {
		this.fy = fy+"";
	}

	public double getFy() {
		return Double.valueOf(fy);
	}

	public void setFbid(String fbid) {
		this.fbid = fbid;
	}

	public String getFbid() {
		return this.fbid;
	}

	public void setFctime(String fctime) {
		this.fctime = fctime;
	}

	public String getFctime() {
		return this.fctime;
	}

	public void setFtype(String ftype) {
		this.ftype = ftype;
	}

	public String getFtype() {
		return this.ftype;
	}

	public String getFshort() {
		return fshort;
	}

	public void setFshort(String fshort) {
		this.fshort = fshort;
	}

	public String getFmode() {
		return fmode;
	}

	public void setFmode(String fmode) {
		this.fmode = fmode;
	}

}
