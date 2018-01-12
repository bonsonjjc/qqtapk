package com.bonson.resource.activity;

/**
 * Created by zjw on 2017/12/29.
 */

public interface BaseView {
    void start(String url);

    void load();

    void back();

    void toast(String msg);

    void dismiss();
}
