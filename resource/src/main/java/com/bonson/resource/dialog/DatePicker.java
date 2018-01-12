package com.bonson.resource.dialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.PopupWindow;

import com.bonson.resource.R;

import java.util.Calendar;

public class DatePicker {
  private NumberPicker npYear;
  private NumberPicker npMonth;
  private NumberPicker npDay;

  private PopupWindow popupWindow;
  private int minYear;
  private int maxYear;

  private Calendar calendar = Calendar.getInstance();

  public DatePicker(Context context, int minYear, int maxYear) {
    this.minYear = minYear;
    this.maxYear = maxYear;
    View view = View.inflate(context, R.layout.date_picker, null);
    popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.MATCH_PARENT);
    popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    npYear = view.findViewById(R.id.np_year);
    npMonth = view.findViewById(R.id.np_month);
    npDay = view.findViewById(R.id.np_day);
    init();
    view.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        dismiss();
      }
    });
    view.findViewById(R.id.tv_sure).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {

      }
    });
  }

  private NumberPicker.Formatter dayFormatter = new NumberPicker.Formatter() {
    @Override public String format(int value) {
      return String.format("%02d", value);
    }
  };

  private NumberPicker.Formatter monthFormatter = new NumberPicker.Formatter() {
    @Override public String format(int value) {
      return String.format("%02d", value + 1);
    }
  };

  private void init() {
    NumberPicker.OnValueChangeListener onValueChangeListener =
        new NumberPicker.OnValueChangeListener() {
          @Override public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
            Log.e("log", newVal + "   " + (newVal - oldVal) + "");
            if (picker == npYear) {
              diff(newVal);
              calendar.set(Calendar.YEAR, newVal);
            } else if (picker == npMonth) {
              calendar.set(Calendar.MONTH, newVal);
            } else {
              calendar.set(Calendar.DATE, newVal);
            }
            int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            int currDay = calendar.get(Calendar.DAY_OF_MONTH);
            npDay.setMinValue(1);
            npDay.setMaxValue(maxDay);
            npDay.setValue(Math.min(currDay, maxDay));
            Log.e("date", (String) DateFormat.format("yyyy-MM-dd", calendar));
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

  Calendar tempCalendar;

  private void diff(int newVal) {
    tempCalendar = Calendar.getInstance();
    tempCalendar.setTime(calendar.getTime());
    tempCalendar.set(Calendar.YEAR, newVal);
    int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    int days1 = tempCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    calendar.set(Calendar.DAY_OF_MONTH, Math.min(days1, days));
  }

  private void setNumberPicker(NumberPicker numberPicker) {
    numberPicker.setDisplayedValues(null);
    numberPicker.setWrapSelectorWheel(true);
    numberPicker.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
  }

  public void show(View parent, int gravity) {
    popupWindow.showAtLocation(parent, gravity, 0, 0);
  }

  public boolean isShowing() {
    return popupWindow.isShowing();
  }

  public void dismiss() {
    popupWindow.dismiss();
  }

  public static DatePicker builder(Context context) {
    return new DatePicker(context, 1900, 2050);
  }

  public static DatePicker builder(Context context, int minYear, int maxYear) {
    return new DatePicker(context, minYear, maxYear);
  }

  public interface OnDateListener {
    void onDate(String year, String month, String day);
  }
}
