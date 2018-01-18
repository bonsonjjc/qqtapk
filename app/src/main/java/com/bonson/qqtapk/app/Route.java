package com.bonson.qqtapk.app;

import com.bonson.fjqqt.view.ui.route.RouteListActivity;
import com.bonson.fjqqt.view.ui.terminal.TerminalActivity;
import com.bonson.fjqqt.view.ui.terminal.alarm.AlarmActivity;
import com.bonson.fjqqt.view.ui.terminal.limit.LimitActivity;
import com.bonson.fjqqt.view.ui.terminal.timer.TimerActivity;
import com.bonson.qqtapk.view.ui.area.SafeAreaActivity;
import com.bonson.qqtapk.view.ui.center.message.MessageActivity;
import com.bonson.qqtapk.view.ui.contacts.ContactsActivity;
import com.bonson.qqtapk.view.ui.family.FamilyActivity;
import com.bonson.qqtapk.view.ui.flower.FlowerActivity;
import com.bonson.qqtapk.view.ui.forget.ForgetActivity;
import com.bonson.qqtapk.view.ui.index.IndexActivity;
import com.bonson.qqtapk.view.ui.info.InfoActivity;
import com.bonson.qqtapk.view.ui.lesson.LessonActivity;
import com.bonson.qqtapk.view.ui.limits.LimitsActivity;
import com.bonson.qqtapk.view.ui.login.LoginActivity;
import com.bonson.qqtapk.view.ui.member.MemberActivity;
import com.bonson.qqtapk.view.ui.center.CenterActivity;
import com.bonson.qqtapk.view.ui.mode.ModeActivity;
import com.bonson.qqtapk.view.ui.motion.MotionActivity;
import com.bonson.qqtapk.view.ui.register.RegisterActivity;
import com.bonson.qqtapk.view.ui.ring.RingActivity;
import com.bonson.qqtapk.view.ui.scan.ScanActivity;
import com.bonson.qqtapk.view.ui.setting.SettingActivity;
import com.bonson.qqtapk.view.ui.setting.about.AboutActivity;
import com.bonson.qqtapk.view.ui.setting.map.MapActivity;
import com.bonson.qqtapk.view.ui.setting.notify.NotifyActivity;
import com.bonson.qqtapk.view.ui.setting.password.PasswordActivity;
import com.bonson.qqtapk.view.ui.voice.VoiceActivity;

/**
 * Created by jiangjiancheng on 17/12/31.
 */

public final class Route {
    public static String index = IndexActivity.class.getName();
    public static String login = LoginActivity.class.getName();
    public static String scan = ScanActivity.class.getName();
    public static String register = RegisterActivity.class.getName();
    public static String forget = ForgetActivity.class.getName();
    public static String center = CenterActivity.class.getName();
    public static String family = FamilyActivity.class.getName();
    public static String area = SafeAreaActivity.class.getName();
    public static String lesson = LessonActivity.class.getName();
    public static String mode = ModeActivity.class.getName();
    public static String motion = MotionActivity.class.getName();
    public static String contacts = ContactsActivity.class.getName();
    public static String flower = FlowerActivity.class.getName();
    public static String member = MemberActivity.class.getName();
    public static String route = com.bonson.qqtapk.view.ui.route.RouteActivity.class.getName();
    public static String limit = LimitsActivity.class.getName();
    public static String voice = VoiceActivity.class.getName();

    public static String setting = SettingActivity.class.getName();
    public static String password = PasswordActivity.class.getName();
    public static String notify = NotifyActivity.class.getName();
    public static String about = AboutActivity.class.getName();
    public static String map = MapActivity.class.getName();
    public static String info = InfoActivity.class.getName();
    public static String ring = RingActivity.class.getName();
    public static String message = MessageActivity.class.getName();

    public static String route2 = RouteListActivity.class.getName();
    public static String terminal = TerminalActivity.class.getName();
    public static String limit2 = LimitActivity.class.getName();
    public static String alarm = AlarmActivity.class.getName();
    public static String timer = TimerActivity.class.getName();
}
