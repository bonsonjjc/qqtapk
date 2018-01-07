package com.bonson.qqtapk.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @类名: CallInLimit
 * @描述: 呼入限制列表实体类
 * @作者: Su
 * @日期: 2015年7月29日
 */

public class Limit extends Base {
	private String fid;
	private String fcstate;
	private String fmobile;
	private String fbegin;
	private String fend;
	private String ffbegin;
	private String ffend;
	private String fbid;
	private String fname;
	private String foptype;

	public String getFid() {
		return fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}

	public String getFcstate() {
		return fcstate;
	}

	public void setFcstate(String fcstate) {
		this.fcstate = fcstate;
	}

	public String getFmobile() {
		return fmobile;
	}

	public void setFmobile(String fmobile) {
		this.fmobile = fmobile;
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

	public String getFfbegin() {
		return ffbegin;
	}

	public void setFfbegin(String ffbegin) {
		this.ffbegin = ffbegin;
	}

	public String getFfend() {
		return ffend;
	}

	public void setFfend(String ffend) {
		this.ffend = ffend;
	}

	public String getFbid() {
		return fbid;
	}

	public void setFbid(String fbid) {
		this.fbid = fbid;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getFoptype() {
		return foptype;
	}

	public void setFoptype(String foptype) {
		this.foptype = foptype;
	}

	@Override
	public String toString() {
		return "CallInLimit [fid=" + fid + ", fcstate=" + fcstate + ", fmobile=" + fmobile + ", fbegin=" + fbegin + ", fend=" + fend + ", ffbegin=" + ffbegin + ", ffend=" + ffend + ", fbid=" + fbid + ", fname=" + fname + ", foptype=" + foptype + "]";
	}
}
