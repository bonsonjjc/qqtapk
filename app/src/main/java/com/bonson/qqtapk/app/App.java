package com.bonson.qqtapk.app;

import android.content.Context;

import com.baidu.mapapi.SDKInitializer;
import com.bonson.qqtapk.di.AppComponent;
import com.bonson.qqtapk.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

/**
 * Created by zjw on 2017/12/29.
 */

public class App extends DaggerApplication {
    public void onCreate() {
        super.onCreate();
        context = this.getApplicationContext();
        SDKInitializer.initialize(this);
    }

    protected AndroidInjector<App> applicationInjector() {
        AppComponent appComponent = DaggerAppComponent.builder().application(this).build();
        return appComponent;
    }

    public static Context context;
}
