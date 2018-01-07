package com.bonson.qqtapk.view.ui.main;

import android.app.Application;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.model.bean.Menu;
import com.bonson.resource.viewmodel.AndroidViewModel;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by zjw on 2018/1/2.
 */
@ActivityScope
public class MainViewModel extends AndroidViewModel {
    @Inject
    MainViewModel(Application application) {
        super(application);
    }

    private String type, battery, stepNumber, sleepTime, heart, address, time;
    private List<Menu> menus;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBattery() {
        return battery;
    }

    public void setBattery(String battery) {
        this.battery = battery;
    }

    public String getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(String stepNumber) {
        this.stepNumber = stepNumber;
    }

    public String getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(String sleepTime) {
        this.sleepTime = sleepTime;
    }

    public String getHeart() {
        return heart;
    }

    public void setHeart(String heart) {
        this.heart = heart;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public void initMenu() {
        menus = MenuHelper.createMenu();
    }
}
