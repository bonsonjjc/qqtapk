package com.bonson.fjqqt.di;

import com.bonson.fjqqt.view.area.SafeAreaModule;
import com.bonson.fjqqt.view.family.FamilyModule;
import com.bonson.fjqqt.view.route.RouteListActivity;
import com.bonson.fjqqt.view.route.RouteLIstModule;
import com.bonson.fjqqt.view.terminal.alarm.AlarmActivity;
import com.bonson.fjqqt.view.terminal.alarm.AlarmModule;
import com.bonson.fjqqt.view.area.SafeAreaActivity;
import com.bonson.fjqqt.view.family.FamilyActivity;
import com.bonson.fjqqt.view.terminal.limit.LimitsModule;
import com.bonson.fjqqt.view.terminal.silence.SilenceActivity;
import com.bonson.fjqqt.view.terminal.silence.SilenceModule;
import com.bonson.fjqqt.view.terminal.limit.LimitsActivity;
import com.bonson.fjqqt.view.terminal.timer.TimerActivity;
import com.bonson.fjqqt.view.terminal.timer.TimerModule;
import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.view.ui.login.LoginActivity;
import com.bonson.qqtapk.view.ui.login.LoginModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class UIModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = {LoginModule.class, QQTRetrofitModule.class})
    abstract LoginActivity loginActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = {RouteLIstModule.class, QQTRetrofitModule.class})
    abstract RouteListActivity routeTimeActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = {AlarmModule.class, QQTRetrofitModule.class})
    abstract AlarmActivity alarmActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = {LimitsModule.class, QQTRetrofitModule.class})
    abstract LimitsActivity limitActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = {TimerModule.class, QQTRetrofitModule.class})
    abstract TimerActivity timerActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = {FamilyModule.class, QQTRetrofitModule.class})
    abstract FamilyActivity familyActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = {SafeAreaModule.class, QQTRetrofitModule.class})
    abstract SafeAreaActivity safeAreaActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = {SilenceModule.class, QQTRetrofitModule.class})
    abstract SilenceActivity lessonActivity();
}
