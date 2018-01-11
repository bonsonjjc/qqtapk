package com.bonson.resource.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import com.bonson.resource.R;

import java.util.ArrayList;
import java.util.List;

import dagger.android.support.DaggerAppCompatActivity;

/**
 * Created by zjw on 2017/12/29.
 */

public abstract class BaseDaggerActivity extends DaggerAppCompatActivity implements BaseView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().findViewById(android.R.id.content).setBackgroundColor(color(R.color.background));
        ActivityUtils.push(this);
    }

    @Override
    protected void onDestroy() {
        ActivityUtils.pop(this);
        super.onDestroy();
    }

    @Override
    public void load() {
        toast("加载中..");
    }

    @Override
    public void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void start(String url) {
        try {
            Class urlClass = Class.forName(url);
            startActivity(new Intent(this, urlClass));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void back() {
        onBackPressed();
    }

    public int color(@ColorRes int resId) {
        return ActivityCompat.getColor(this, resId);
    }

    public Drawable drawable(@DrawableRes int resId) {
        return ActivityCompat.getDrawable(this, resId);
    }
}
