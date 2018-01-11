package com.bonson.library.utils;

/**
 * Created by zjw on 2018/1/4.
 */

public class NumberUtils {
    public static double parseDouble(String value) {
        return parseDouble(value, 0);
    }

    public static double parseDouble(String value, double defValue) {
        double result;
        try {
            result = Double.parseDouble(value);
        } catch (Exception e) {
            result = defValue;
        }
        return result;
    }

    public static int parseInt(String value) {
        return parseInt(value, 0);
    }

    public static int parseInt(String value, int defValue) {
        int result;
        try {
            result = Integer.parseInt(value);
        } catch (Exception e) {
            result = defValue;
        }
        return result;
    }

    public static float parseFloat(String value) {
        return parseFloat(value, 0);
    }

    public static float parseFloat(String value, float defValue) {
        float result;
        try {
            result = Integer.parseInt(value);
        } catch (Exception e) {
            result = defValue;
        }
        return result;
    }
}
