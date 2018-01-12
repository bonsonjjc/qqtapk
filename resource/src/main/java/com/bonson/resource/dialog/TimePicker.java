package com.bonson.resource.dialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.PopupWindow;

import com.bonson.library.utils.NumberUtils;
import com.bonson.resource.R;

public class TimePicker {
    private NumberPicker npStartHour;
    private NumberPicker npStartMinute;
    private NumberPicker npEndHour;
    private NumberPicker npEndMinute;

    private PopupWindow popupWindow;

    private TimePicker(Context context) {
        View view = View.inflate(context, R.layout.time_picker, null);
        npStartHour = view.findViewById(R.id.np_start_hour);
        npStartMinute = view.findViewById(R.id.np_start_minute);
        npEndHour = view.findViewById(R.id.np_end_hour);
        npEndMinute = view.findViewById(R.id.np_end_minute);
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        init();
        view.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        view.findViewById(R.id.tv_sure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onSaveListener != null) {
                    onSaveListener.onSave(startHour, startMinute, endHour, endMinute);
                }
            }
        });
    }

    private String startHour, startMinute, endHour, endMinute;
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

    private NumberPicker.OnValueChangeListener onValueChangeListener = new NumberPicker.OnValueChangeListener() {
        @Override
        public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
            if (picker == npStartHour) {
                startHour = hourFormatter.format(newVal);
            } else if (picker == npStartMinute) {
                startMinute = minuteFormatter.format(newVal);
            } else if (picker == npEndHour) {
                endHour = hourFormatter.format(newVal);
            } else {
                endMinute = minuteFormatter.format(newVal);
            }

        }
    };

    private void init() {
        setNumberPicker(npStartHour);
        setNumberPicker(npStartMinute);
        setNumberPicker(npEndHour);
        setNumberPicker(npEndMinute);
        npStartHour.setMinValue(0);
        npStartHour.setMaxValue(23);

        npStartMinute.setMinValue(1);
        npStartMinute.setMaxValue(11);

        npEndHour.setMinValue(0);
        npEndHour.setMaxValue(23);

        npEndMinute.setMinValue(1);
        npEndMinute.setMaxValue(11);

        npStartHour.setFormatter(hourFormatter);
        npEndHour.setFormatter(hourFormatter);
        npStartMinute.setFormatter(minuteFormatter);
        npEndMinute.setFormatter(minuteFormatter);


        npStartHour.setOnValueChangedListener(onValueChangeListener);
        npStartMinute.setOnValueChangedListener(onValueChangeListener);
        npEndHour.setOnValueChangedListener(onValueChangeListener);
        npEndMinute.setOnValueChangedListener(onValueChangeListener);
    }

    public NumberPicker getStartHour() {
        return npStartHour;
    }

    public NumberPicker getStartMinute() {
        return npStartMinute;
    }

    public NumberPicker getEndHour() {
        return npEndHour;
    }

    public NumberPicker getEndMinute() {
        return npEndMinute;
    }

    private void setNumberPicker(NumberPicker numberPicker) {
        numberPicker.setDisplayedValues(null);
        numberPicker.setWrapSelectorWheel(true);
        numberPicker.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
    }

    public void scrollTo(int startHour, int startMinute, int endHour, int endMinute) {
        npStartHour.setValue(startHour);
        npStartMinute.setValue(startMinute / 5);
        npEndHour.setValue(endHour);
        npEndMinute.setValue(endMinute / 5);
    }

    public void scrollTo(String startHour, String startMinute, String endHour, String endMinute) {
        scrollTo(NumberUtils.parseInt(startHour), NumberUtils.parseInt(startMinute), NumberUtils.parseInt(endHour), NumberUtils.parseInt(endMinute));
    }

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

    public static TimePicker builer(Context context) {
        return new TimePicker(context);
    }
}
