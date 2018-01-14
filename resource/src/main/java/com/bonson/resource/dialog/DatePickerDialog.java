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

import com.bonson.library.utils.DateUtils;
import com.bonson.resource.R;
import com.bonson.resource.view.DatePicker;

import java.util.Calendar;
import java.util.Date;

public class DatePickerDialog {

    private PopupWindow popupWindow;

    private DatePicker datePicker;

    public DatePickerDialog(Context context, int minYear, final int maxYear) {
        View view = View.inflate(context, R.layout.dialog_date_picker, null);
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        datePicker = view.findViewById(R.id.dpDate);
        datePicker.setMinYear(minYear);
        datePicker.setMaxYear(maxYear);
        view.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        view.findViewById(R.id.tv_sure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onDateListener != null) {
                    Date time = datePicker.getCalendar().getTime();
                    onDateListener.onDate(DateUtils.format(time, "yyyy-MM-dd"), time);
                }
            }
        });
    }

    public void setDate(Date date) {
        datePicker.setDate(date);
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

    public static DatePickerDialog builder(Context context) {
        return new DatePickerDialog(context, 1900, 2050);
    }

    public static DatePickerDialog builder(Context context, int minYear, int maxYear) {
        return new DatePickerDialog(context, minYear, maxYear);
    }

    private OnDateListener onDateListener;

    public OnDateListener getOnDateListener() {
        return onDateListener;
    }

    public void setOnDateListener(OnDateListener onDateListener) {
        this.onDateListener = onDateListener;
    }

    public interface OnDateListener {
        void onDate(String dateStr, Date date);
    }
}
