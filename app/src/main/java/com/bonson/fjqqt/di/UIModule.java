package com.bonson.fjqqt.di;

import com.bonson.fjqqt.view.ui.route.RouteListActivity;
import com.bonson.fjqqt.view.ui.route.RouteLIstModule;
import com.bonson.fjqqt.view.ui.terminal.alarm.AlarmActivity;
import com.bonson.fjqqt.view.ui.terminal.alarm.AlarmModule;
import com.bonson.fjqqt.view.ui.terminal.limit.LimitActivity;
import com.bonson.fjqqt.view.ui.terminal.limit.LimitModule;
import com.bonson.fjqqt.view.ui.terminal.timer.TimerActivity;
import com.bonson.fjqqt.view.ui.terminal.timer.TimerModule;
import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.di.RetrofitModule;
import com.bonson.qqtapk.view.ui.login.LoginActivity;
import com.bonson.qqtapk.view.ui.login.LoginModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class UIModule {
    @FjqqtScope
    @ContributesAndroidInjector(modules = {LoginModule.class})
    abstract LoginActivity loginActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = {RouteLIstModule.class, RetrofitModule.class})
    abstract RouteListActivity routeTimeActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = {AlarmModule.class, RetrofitModule.class})
    abstract AlarmActivity alarmActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = {LimitModule.class, RetrofitModule.class})
    abstract LimitActivity limitActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = {TimerModule.class, RetrofitModule.class})
    abstract TimerActivity timerActivity();
}
