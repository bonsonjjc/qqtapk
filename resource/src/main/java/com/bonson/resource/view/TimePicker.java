package com.bonson.resource.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.NumberPicker;

import com.bonson.resource.R;

/**
 * Created by jiangjiancheng on 18/1/14.
 */

public class TimePicker extends LinearLayout {
    private NumberPicker npHour;
    private NumberPicker npMinute;

    private NumberPicker.Formatter hourFormatter, minuteFormatter;

    public TimePicker(Context context) {
        this(context, null);
    }

    public TimePicker(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TimePicker(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public TimePicker(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        View view = View.inflate(context, R.layout.time_picker, this);
        npHour = view.findViewById(R.id.np_hour);
        npMinute = view.findViewById(R.id.np_minute);
    }

    public void setWheel(boolean wheel) {
        npHour.setDisplayedValues(null);
        npHour.setWrapSelectorWheel(wheel);
        npHour.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        npMinute.setDisplayedValues(null);
        npMinute.setWrapSelectorWheel(wheel);
        npMinute.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
    }

    public void setTime(int hour, int minute) {
        npHour.setValue(hour);
        npMinute.setValue(minute / 5);
    }

    public NumberPicker.Formatter getHourFormatter() {
        return hourFormatter;
    }

    public void setHourFormatter(NumberPicker.Formatter hourFormatter) {
        this.hourFormatter = hourFormatter;
    }

    public NumberPicker.Formatter getMinuteFormatter() {
        return minuteFormatter;
    }

    public void setMinuteFormatter(NumberPicker.Formatter minuteFormatter) {
        this.minuteFormatter = minuteFormatter;
    }

    public void setHour(int min, int max) {
        npHour.setMinValue(min);
        npHour.setMaxValue(max);
        npHour.setFormatter(hourFormatter);
    }

    public void setMinute(int min, int max) {
        npMinute.setMinValue(min);
        npMinute.setMaxValue(max);
        npMinute.setFormatter(minuteFormatter);
    }

    public int getHour() {
        return npHour.getValue();
    }

    public int getMinute() {
        return npMinute.getValue();
    }

}
