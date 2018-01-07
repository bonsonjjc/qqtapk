package com.bonson.qqtapk.model.bean;

import android.arch.persistence.room.Ignore;

import com.bonson.qqtapk.app.ErrorCode;

/**
 * Created by zjw on 2018/1/2.
 */

public class Base {
    @Ignore
    private String fresult = "-7";

    public final String getFresult() {
        return fresult;
    }

    public final void setFresult(String fresult) {
        this.fresult = fresult;
    }

    public String getMsg() {
        return ErrorCode.message(fresult);
    }
}
