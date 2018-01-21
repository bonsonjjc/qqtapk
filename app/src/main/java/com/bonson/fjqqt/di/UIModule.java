package com.bonson.fjqqt.di;

import com.bonson.fjqqt.view.ui.area.SafeAreaModule;
import com.bonson.fjqqt.view.ui.family.FamilyModule;
import com.bonson.fjqqt.view.ui.route.RouteListActivity;
import com.bonson.fjqqt.view.ui.route.RouteLIstModule;
import com.bonson.fjqqt.view.ui.terminal.alarm.AlarmActivity;
import com.bonson.fjqqt.view.ui.terminal.alarm.AlarmModule;
import com.bonson.fjqqt.view.ui.area.SafeAreaActivity;
import com.bonson.fjqqt.view.ui.family.FamilyActivity;
import com.bonson.fjqqt.view.ui.terminal.silence.SilenceActivity;
import com.bonson.fjqqt.view.ui.terminal.silence.SilenceModule;
import com.bonson.fjqqt.view.ui.terminal.limit.LimitsActivity;
import com.bonson.fjqqt.view.ui.terminal.limit.LimitsModule;
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
    @ContributesAndroidInjector(modules = {LimitsModule.class, RetrofitModule.class})
    abstract LimitsActivity limitActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = {TimerModule.class, RetrofitModule.class})
    abstract TimerActivity timerActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = {FamilyModule.class, RetrofitModule.class})
    abstract FamilyActivity familyActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = {SafeAreaModule.class, RetrofitModule.class})
    abstract SafeAreaActivity safeAreaActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = {SilenceModule.class, RetrofitModule.class})
    abstract SilenceActivity lessonActivity();
}
