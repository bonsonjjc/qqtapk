package com.bonson.resource.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.NumberPicker;

import com.bonson.resource.R;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by jiangjiancheng on 18/1/14.
 */

public class DatePicker extends LinearLayout {
    private NumberPicker npYear;
    private NumberPicker npMonth;
    private NumberPicker npDay;
    private Calendar calendar = Calendar.getInstance();

    private NumberPicker.Formatter dayFormatter = new NumberPicker.Formatter() {
        @Override
        public String format(int value) {
            return String.format("%02d", value);
        }
    };

    private NumberPicker.Formatter monthFormatter = new NumberPicker.Formatter() {
        @Override
        public String format(int value) {
            return String.format("%02d", value + 1);
        }
    };
    private int minYear, maxYear;

    public DatePicker(Context context) {
        this(context, null);
    }

    public DatePicker(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DatePicker(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public DatePicker(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        View view = View.inflate(context, R.layout.date_picker, this);
        npYear = view.findViewById(R.id.np_year);
        npMonth = view.findViewById(R.id.np_month);
        npDay = view.findViewById(R.id.np_day);
    }

    private void init() {
        NumberPicker.OnValueChangeListener onValueChangeListener =
                new NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                        Log.e("log", newVal + "   " + (newVal - oldVal) + "");
                        if (picker == npYear) {
                            diff(newVal, -1, -1);
                        } else if (picker == npMonth) {
                            diff(-1, newVal, -1);
                        } else {
                            diff(-1, -1, newVal);
                        }
                        int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
                        npDay.setMinValue(1);
                        npDay.setMaxValue(maxDay);
                        npDay.setValue(calendar.get(Calendar.DAY_OF_MONTH));
                    }
                };

        npYear.setMinValue(minYear);
        npYear.setMaxValue(maxYear);
        setNumberPicker(npYear);
        npYear.setOnValueChangedListener(onValueChangeListener);
        npYear.setValue(calendar.get(Calendar.YEAR));

        npMonth.setMinValue(0);
        npMonth.setMaxValue(11);
        setNumberPicker(npMonth);
        npMonth.setFormatter(monthFormatter);
        npMonth.setOnValueChangedListener(onValueChangeListener);
        npMonth.setValue(calendar.get(Calendar.MONTH));

        npDay.setOnValueChangedListener(onValueChangeListener);
        npDay.setMinValue(1);
        npDay.setFormatter(dayFormatter);
        npDay.setMaxValue(calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        setNumberPicker(npDay);
        npDay.setValue(calendar.get(Calendar.DAY_OF_MONTH));
    }

    public int getMinYear() {
        return minYear;
    }

    public void setMinYear(int minYear) {
        this.minYear = minYear;
        npDay.setMinValue(minYear);
    }

    public int getMaxYear() {
        return maxYear;
    }

    public void setMaxYear(int maxYear) {
        this.maxYear = maxYear;
        npDay.setMaxValue(maxYear);
    }

    private Calendar tempCalendar;

    private void diff(int year, int month, int day) {
        tempCalendar = Calendar.getInstance();
        tempCalendar.set(Calendar.YEAR, year == -1 ? calendar.get(Calendar.YEAR) : year);
        tempCalendar.set(Calendar.MONTH, month == -1 ? calendar.get(Calendar.MONTH) : month);
        tempCalendar.set(Calendar.DAY_OF_MONTH, 1);
        day = day == -1 ? calendar.get(Calendar.DAY_OF_MONTH) : day;
        int maxDay = tempCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int minDay = Math.min(maxDay, day);
        Log.e("----", minDay + "");
        tempCalendar.set(Calendar.DAY_OF_MONTH, minDay);
        calendar.setTime(tempCalendar.getTime());
    }

    public void setDate(Date date) {
        calendar.setTime(date);
        init();
    }

    public Calendar getCalendar() {
        return calendar;
    }

    private void setNumberPicker(NumberPicker numberPicker) {
        numberPicker.setDisplayedValues(null);
        numberPicker.setWrapSelectorWheel(true);
        numberPicker.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
    }
}
