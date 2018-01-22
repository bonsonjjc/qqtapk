package com.bonson.fjqqt.di;

import com.bonson.fjqqt.view.ui.area.SafeAreaModule;
import com.bonson.fjqqt.view.ui.family.FamilyModule;
import com.bonson.fjqqt.view.ui.route.RouteListActivity;
import com.bonson.fjqqt.view.ui.route.RouteLIstModule;
import com.bonson.fjqqt.view.ui.terminal.alarm.AlarmActivity;
import com.bonson.fjqqt.view.ui.terminal.alarm.AlarmModule;
import com.bonson.fjqqt.view.ui.area.SafeAreaActivity;
import com.bonson.fjqqt.view.ui.family.FamilyActivity;
import com.bonson.fjqqt.view.ui.terminal.limit.LimitsModule;
import com.bonson.fjqqt.view.ui.terminal.silence.SilenceActivity;
import com.bonson.fjqqt.view.ui.terminal.silence.SilenceModule;
import com.bonson.fjqqt.view.ui.terminal.limit.LimitsActivity;
import com.bonson.fjqqt.view.ui.terminal.timer.TimerActivity;
import com.bonson.fjqqt.view.ui.terminal.timer.TimerModule;
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
