package com.bonson.qqtapk.di;

import com.bonson.qqtapk.view.ui.area.SafeAreaActivity;
import com.bonson.qqtapk.view.ui.area.SafeAreaModule;
import com.bonson.qqtapk.view.ui.contacts.ContactsActivity;
import com.bonson.qqtapk.view.ui.contacts.ContactsModule;
import com.bonson.qqtapk.view.ui.family.FamilyActivity;
import com.bonson.qqtapk.view.ui.family.FamilyModule;
import com.bonson.qqtapk.view.ui.flower.FlowerActivity;
import com.bonson.qqtapk.view.ui.flower.FlowerModule;
import com.bonson.qqtapk.view.ui.forget.ForgetActivity;
import com.bonson.qqtapk.view.ui.forget.ForgetModule;
import com.bonson.qqtapk.view.ui.index.IndexActivity;
import com.bonson.qqtapk.view.ui.index.IndexModule;
import com.bonson.qqtapk.view.ui.info.InfoActivity;
import com.bonson.qqtapk.view.ui.info.InfoModule;
import com.bonson.qqtapk.view.ui.lesson.LessonActivity;
import com.bonson.qqtapk.view.ui.lesson.LessonModule;
import com.bonson.qqtapk.view.ui.limit.LimitActivity;
import com.bonson.qqtapk.view.ui.limit.LimitModule;
import com.bonson.qqtapk.view.ui.login.LoginActivity;
import com.bonson.qqtapk.view.ui.login.LoginModule;
import com.bonson.qqtapk.view.ui.member.MemberActivity;
import com.bonson.qqtapk.view.ui.member.MemberModule;
import com.bonson.qqtapk.view.ui.mode.ModeActivity;
import com.bonson.qqtapk.view.ui.mode.ModeModule;
import com.bonson.qqtapk.view.ui.register.RegisterActivity;
import com.bonson.qqtapk.view.ui.register.RegisterModule;
import com.bonson.qqtapk.view.ui.ring.RingActivity;
import com.bonson.qqtapk.view.ui.ring.RingModule;
import com.bonson.qqtapk.view.ui.route.RouteActivity;
import com.bonson.qqtapk.view.ui.route.RouteModule;
import com.bonson.qqtapk.view.ui.setting.SettingActivity;
import com.bonson.qqtapk.view.ui.setting.SettingModule;
import com.bonson.qqtapk.view.ui.setting.about.AboutActivity;
import com.bonson.qqtapk.view.ui.setting.about.AboutModule;
import com.bonson.qqtapk.view.ui.setting.map.MapActivity;
import com.bonson.qqtapk.view.ui.setting.map.MapModule;
import com.bonson.qqtapk.view.ui.setting.notify.NotifyActivity;
import com.bonson.qqtapk.view.ui.setting.notify.NotifyModule;
import com.bonson.qqtapk.view.ui.setting.password.PasswordActivity;
import com.bonson.qqtapk.view.ui.setting.password.PasswordModule;
import com.bonson.qqtapk.view.ui.voice.VoiceActivity;
import com.bonson.qqtapk.view.ui.voice.VoiceModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/*import com.bonson.qqtapk.view.area.SafeAreaActivity;
import com.bonson.qqtapk.view.area.SafeAreaModule;
import com.bonson.qqtapk.view.contacts.ContactsActivity;
import com.bonson.qqtapk.view.contacts.ContactsModule;
import com.bonson.qqtapk.view.family.FamilyActivity;
import com.bonson.qqtapk.view.family.FamilyModule;
import com.bonson.qqtapk.view.flower.FlowerActivity;
import com.bonson.qqtapk.view.flower.FlowerModule;
import com.bonson.qqtapk.view.info.InfoActivity;
import com.bonson.qqtapk.view.info.InfoModule;
import com.bonson.qqtapk.view.lesson.LessonActivity;
import com.bonson.qqtapk.view.lesson.LessonModule;
import com.bonson.qqtapk.view.limit.LimitActivity;
import com.bonson.qqtapk.view.limit.LimitModule;
import com.bonson.qqtapk.view.member.MemberActivity;
import com.bonson.qqtapk.view.member.MemberModule;
import com.bonson.qqtapk.view.message.MessageActivity;
import com.bonson.qqtapk.view.message.MessageModule;
import com.bonson.qqtapk.view.mode.ModeActivity;
import com.bonson.qqtapk.view.mode.ModeModule;
import com.bonson.qqtapk.view.ring.RingActivity;
import com.bonson.qqtapk.view.ring.RingModule;
import com.bonson.qqtapk.view.route.RouteActivity;
import com.bonson.qqtapk.view.route.RouteModule;
import com.bonson.qqtapk.view.setting.SettingActivity;
import com.bonson.qqtapk.view.setting.SettingModule;
import com.bonson.qqtapk.view.setting.notify.NotifyActivity;
import com.bonson.qqtapk.view.setting.notify.NotifyModule;
import com.bonson.qqtapk.view.setting.password.PasswordActivity;
import com.bonson.qqtapk.view.setting.password.PasswordModule;
import com.bonson.qqtapk.view.voice.VoiceActivity;
import com.bonson.qqtapk.view.voice.VoiceModule;*/

/**
 * Created by zjw on 2017/12/29.
 */
@Module
abstract class ActivityModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = {LoginModule.class, RetrofitModule.class})
    abstract LoginActivity loginActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = {RegisterModule.class, RetrofitModule.class})
    abstract RegisterActivity registerActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = {ForgetModule.class, RetrofitModule.class})
    abstract ForgetActivity forgetActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = {IndexModule.class, RetrofitModule.class})
    abstract IndexActivity mainActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = {FamilyModule.class, RetrofitModule.class})
    abstract FamilyActivity familyActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = {SafeAreaModule.class, RetrofitModule.class})
    abstract SafeAreaActivity safeAreaActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = {LessonModule.class, RetrofitModule.class})
    abstract LessonActivity lessonActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = {ModeModule.class, RetrofitModule.class})
    abstract ModeActivity modeActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = {ContactsModule.class, RetrofitModule.class})
    abstract ContactsActivity contactsActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = {FlowerModule.class, RetrofitModule.class})
    abstract FlowerActivity flowerActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = {MemberModule.class, RetrofitModule.class})
    abstract MemberActivity memberActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = {RouteModule.class, RetrofitModule.class})
    abstract RouteActivity routeActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = {LimitModule.class, RetrofitModule.class})
    abstract LimitActivity limitActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = {VoiceModule.class, RetrofitModule.class})
    abstract VoiceActivity voiceActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = {SettingModule.class, RetrofitModule.class})
    abstract SettingActivity settingActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = {PasswordModule.class, RetrofitModule.class})
    abstract PasswordActivity passwordViewModel();

    @ActivityScope
    @ContributesAndroidInjector(modules = {NotifyModule.class, RetrofitModule.class})
    abstract NotifyActivity notifyActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = {AboutModule.class, RetrofitModule.class})
    abstract AboutActivity aboutActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = {MapModule.class, RetrofitModule.class})
    abstract MapActivity mapActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = {InfoModule.class, RetrofitModule.class})
    abstract InfoActivity infoActivity();


    @ActivityScope
    @ContributesAndroidInjector(modules = {RingModule.class, RetrofitModule.class})
    abstract RingActivity ringActivity();

}