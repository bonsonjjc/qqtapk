package com.bonson.qqtapk.utils;

import android.text.TextUtils;

/**
 * Created by zjw on 2018/1/4.
 */

public class TimeUtils {
    public static String mapTime(String start, String end) {
        start = TextUtils.isEmpty(start) ? "0000" : start;
        end = TextUtils.isEmpty(end) ? "0000" : end;
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < start.length(); i++) {
            buffer.append(start.charAt(i));
            if (i == 1) {
                buffer.append(":");
            }
        }
        buffer.append("~");
        for (int i = 0; i < end.length(); i++) {
            buffer.append(end.charAt(i));
            if (i == 1) {
                buffer.append(":");
            }
        }
        return buffer.toString();
    }

    public static String mapTime(String time) {
        String[] times = time.split("-");
        return mapTime(times[0], times[1]);
    }

    public static String[] split(String time) {
        time = TextUtils.isEmpty(time) ? "0000" : time;
        String hour = time.substring(0, 2);
        String minute = time.substring(2, 4);
        return new String[]{hour, minute};
    }

    public static String[] split2(String time) {
        String[] times = time.split("-");
        String[] start = split(times[0]);
        String[] end = split(times[1]);
        String[] result = new String[4];
        result[0] = start[0];
        result[1] = start[1];
        result[2] = end[0];
        result[3] = end[1];
        return result;
    }
}
