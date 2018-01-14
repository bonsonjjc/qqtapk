package com.bonson.resource.dialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.PopupWindow;

import com.bonson.library.utils.NumberUtils;
import com.bonson.resource.R;
import com.bonson.resource.view.TimePicker;

public class TimePickerDialog {
    private TimePicker tpStart;
    private TimePicker tpEnd;

    private PopupWindow popupWindow;

    private TimePickerDialog(Context context) {
        View view = View.inflate(context, R.layout.dialog_time_picker, null);
        tpStart = view.findViewById(R.id.tp_start);
        tpEnd = view.findViewById(R.id.tp_end);
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        view.findViewById(R.id.tv_sure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onSaveListener != null) {
                    onSaveListener.onSave(hourFormatter.format(tpStart.getHour()), minuteFormatter.format(tpStart.getMinute()), hourFormatter.format(tpEnd.getHour()), minuteFormatter.format(tpEnd.getMinute()));
                }
            }
        });
        view.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        tpStart.setWheel(true);
        tpStart.setHourFormatter(hourFormatter);
        tpStart.setMinuteFormatter(minuteFormatter);
        tpStart.setHour(0, 23);
        tpStart.setMinute(0, 11);

        tpEnd.setHourFormatter(hourFormatter);
        tpEnd.setMinuteFormatter(minuteFormatter);
        tpEnd.setWheel(true);
        tpEnd.setHour(0, 23);
        tpEnd.setMinute(0, 11);
    }

    public void setValue(int startHour, int startMinute, int endHour, int endMinute) {
        tpStart.setTime(startHour, startMinute);
        tpEnd.setTime(endHour, endMinute);
    }

    public void setValue(String startHour, String startMinute, String endHour, String endMinute) {
        tpStart.setTime(NumberUtils.parseInt(startHour), NumberUtils.parseInt(startMinute));
        tpEnd.setTime(NumberUtils.parseInt(endHour), NumberUtils.parseInt(endMinute));
    }

    private NumberPicker.Formatter hourFormatter = new NumberPicker.Formatter() {
        @Override
        public String format(int value) {
            return String.format("%02d", value);
        }
    };
    private NumberPicker.Formatter minuteFormatter = new NumberPicker.Formatter() {
        @Override
        public String format(int value) {
            return String.format("%02d", value * 5);
        }
    };

    public void show(View parent, int gravity, int x, int y) {
        popupWindow.showAtLocation(parent, gravity, x, y);
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

    private OnSaveListener onSaveListener;

    public OnSaveListener getOnSaveListener() {
        return onSaveListener;
    }

    public void setOnSaveListener(OnSaveListener onSaveListener) {
        this.onSaveListener = onSaveListener;
    }

    public interface OnSaveListener {
        void onSave(String startHour, String startMinute, String endHour, String endMinute);
    }

    public static TimePickerDialog builder(Context context) {
        return new TimePickerDialog(context);
    }
}
