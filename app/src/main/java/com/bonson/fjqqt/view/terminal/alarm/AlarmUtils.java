package com.bonson.fjqqt.view.terminal.alarm;

import com.bonson.library.utils.NumberUtils;

import java.util.Calendar;
import java.util.Locale;

public class AlarmUtils {
    public static final String TODAY = "0";
    public static final String WEEKDAY = "1!2!3!4!5";
    public static final String WEEKEND = "6!7";

    public static final String[] days = {"周一", "周二", "周三", "周四", "周五", "周六", "周日"};
    public static final String[] notifyTypes = new String[]{"闹钟提醒 ", "打针吃药 ", "买菜做饭 ", "接送孩子 ", "电视节目 "};


    public static final int TYPE_TODAY = 1;
    public static final int TYPE_WEEKDAY = 2;
    public static final int TYPE_WEEKEND = 3;
    public static final int TYPE_CUSTOM = 4;


    public static Alarm newAlarm() {
        Alarm alarm = new Alarm();
        alarm.setFstate("1");
        alarm.setFcontent(notifyTypes[1]);
        alarm.setFcycle(TODAY);
        alarm.setFtype("1");
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        alarm.setFtimes(String.format(Locale.CHINA,"%02d:%02d", hour, minute));
        return alarm;
    }

    public static String convert(String cycle) {
        StringBuilder builder = new StringBuilder();
        switch (cycle) {
            case WEEKDAY:
                builder.append("工作日");
                break;
            case WEEKEND:
                builder.append("周末");
                break;
            default:
                parse(builder, cycle);
                break;
        }
        return builder.toString();
    }

    public static void parse(StringBuilder builder, String cycle) {
        for (int i = 0; i < cycle.length(); i++) {
            char c = cycle.charAt(i);
            int index = NumberUtils.parseInt(c + "");
            builder.append(days[i - 1]);
            if (i != cycle.length() - 1) {
                builder.append("、");
            }
        }
    }

    public static int[] parseTime(String time) {
        String[] split = time.split(":");
        return new int[]{NumberUtils.parseInt(split[0]), NumberUtils.parseInt(split[1])};
    }

    public static int getType(String cycle) {
        switch (cycle) {
            case TODAY:
                return TYPE_TODAY;
            case WEEKDAY:
                return TYPE_WEEKDAY;
            case WEEKEND:
                return TYPE_WEEKEND;
            default:
                return TYPE_CUSTOM;
        }
    }
}
