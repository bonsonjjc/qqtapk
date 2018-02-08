package com.bonson.qqtapk.utils;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;

import com.bonson.library.utils.DensityUtils;
import com.bonson.qqtapk.app.App;

import java.util.Locale;

public class SpannableText {
    public static CharSequence setMotion(int progress) {
        String source = progress + "步";
        SpannableString spannableString = new SpannableString(source);
        spannableString.setSpan(new ForegroundColorSpan(0xff999999), source.length() - 1, source.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new AbsoluteSizeSpan(DensityUtils.sp(App.context, 20)), source.length() - 1, source.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    public static CharSequence setSleep(int progress) {
        int hour = progress / 60;
        int minute = progress % 60;
        String source = String.format(Locale.CHINA,"%d小时%02d分钟", hour, minute);
        SpannableString spannableString = new SpannableString(source);
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(0xff999999);
        int start = source.indexOf("小"), end = start + 2;
        spannableString.setSpan(new AbsoluteSizeSpan(DensityUtils.sp(App.context, 20)), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(foregroundColorSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        start = source.indexOf("分");
        end = start + 2;
        spannableString.setSpan(foregroundColorSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new AbsoluteSizeSpan(DensityUtils.sp(App.context, 20)), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }
}
