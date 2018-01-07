package com.bonson.qqtapk.model.bean;

import android.databinding.BaseObservable;


/**
 * Created by zjw on 2018/1/3.
 */

public class Menu extends BaseObservable {
    private String icon;
    private String name;
    private String detail;

    private Runnable runnable;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
        notifyChange();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Runnable getRunnable() {
        return runnable;
    }

    public void setRunnable(Runnable runnable) {
        this.runnable = runnable;
    }
}
