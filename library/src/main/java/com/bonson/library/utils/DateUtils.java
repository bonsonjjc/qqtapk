package com.bonson.library.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static String format(Date date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        try {
            String result = dateFormat.format(date);
            return result;
        } catch (Exception e) {
            return "";
        }
    }

    public static Date parse(String date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        try {
            Date result = dateFormat.parse(date);
            return result;
        } catch (Exception e) {
            return new Date();
        }
    }
}
