package com.bonson.qqtapk.utils;

import android.text.TextUtils;

/**
 * Created by zjw on 2018/1/4.
 */

public class TimeUtils {
    public static  String mapTime(String start, String end) {
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
}
