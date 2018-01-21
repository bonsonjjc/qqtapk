package com.bonson.fjqqt;

import com.bonson.fjqqt.view.ui.route.RouteListActivity;
import com.bonson.fjqqt.view.ui.terminal.TerminalActivity;
import com.bonson.fjqqt.view.ui.terminal.alarm.AlarmActivity;
import com.bonson.fjqqt.view.ui.area.SafeAreaActivity;
import com.bonson.fjqqt.view.ui.family.FamilyActivity;
import com.bonson.fjqqt.view.ui.terminal.silence.SilenceActivity;
import com.bonson.fjqqt.view.ui.terminal.limit.LimitsActivity;
import com.bonson.fjqqt.view.ui.terminal.timer.TimerActivity;

public class Path {
    public static String route = RouteListActivity.class.getName();
    public static String family = FamilyActivity.class.getName();
    public static String area = SafeAreaActivity.class.getName();
    public static String terminal = TerminalActivity.class.getName();
    public static String limit = LimitsActivity.class.getName();
    public static String alarm = AlarmActivity.class.getName();
    public static String timer = TimerActivity.class.getName();
    public static String lesson= SilenceActivity.class.getName();
}
