package com.bonson.qqtapk.view.ui.route;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.bonson.library.utils.DateUtils;
import com.bonson.qqtapk.R;
import com.bonson.resource.view.DatePicker;
import com.bonson.resource.view.TimePicker;

import java.util.Calendar;
import java.util.Date;


public class RouteTimeDialog extends DialogFragment {
    private DatePicker datePicker;
    private TimePicker tpStart;
    private TimePicker tpEnd;
    private OnSaveListener onSaveListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_datetime_picker, container, false);
        datePicker = view.findViewById(R.id.dpDate);
        tpStart = view.findViewById(R.id.tp_start);
        tpEnd = view.findViewById(R.id.tp_end);
        view.findViewById(R.id.tv_cancel).setOnClickListener(v -> dismiss());
        view.findViewById(R.id.tv_sure).setOnClickListener(v -> {
            Calendar start = Calendar.getInstance();
            start.setTime(datePicker.getCalendar().getTime());
            start.set(Calendar.HOUR_OF_DAY, tpStart.getHour());
            start.set(Calendar.MINUTE, tpStart.getMinute());

            Calendar end = Calendar.getInstance();
            end.setTime(datePicker.getCalendar().getTime());
            end.set(Calendar.HOUR_OF_DAY, tpEnd.getHour());
            end.set(Calendar.MINUTE, tpEnd.getMinute());
            if (end.before(start)) {
                Toast.makeText(getContext(), "开始时间不能大于结束时间", Toast.LENGTH_SHORT).show();
                return;
            }
            if (onSaveListener != null) {
                onSaveListener.onSave(DateUtils.format(start.getTime(), "yyyy-MM-dd hh:mm"), DateUtils.format(end.getTime(), "yyyy-MM-dd hh:mm"));
            }
        });
        datePicker.setMaxYear(Calendar.getInstance().get(Calendar.YEAR));
        datePicker.setMinYear(1990);
        datePicker.setDate(new Date());
        NumberPicker.Formatter hourFormatter = value -> {
            if (value < 10) {
                return "0" + value;
            }
            return "" + value;
        };
        NumberPicker.Formatter minuteFormatter = value -> {
            int v = value * 5;
            if (v < 10) {
                return "0" + v;
            }
            return "" + v;
        };
        tpStart.setWheel(true);
        tpEnd.setWheel(true);
        tpStart.setHourFormatter(hourFormatter);
        tpStart.setMinuteFormatter(minuteFormatter);
        tpEnd.setHourFormatter(hourFormatter);
        tpEnd.setMinuteFormatter(minuteFormatter);

        tpStart.setHour(0, 23);
        tpStart.setMinute(0, 11);
        tpEnd.setHour(0, 23);
        tpEnd.setMinute(0, 11);
        tpStart.setTime(0, 0);
        tpEnd.setTime(23, 59);
        return view;
    }

    public RouteTimeDialog setOnSaveListener(OnSaveListener onSaveListener) {
        this.onSaveListener = onSaveListener;
        return this;
    }

    @Override
    public void onResume() {
        super.onResume();
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams attributes = getDialog().getWindow().getAttributes();
        attributes.width = ViewGroup.LayoutParams.MATCH_PARENT;
        getDialog().getWindow().setAttributes(attributes);
        getDialog().getWindow().setGravity(Gravity.TOP);
    }

    public interface OnSaveListener {
        void onSave(String start, String end);
    }

    public boolean isShowing() {
        if (getDialog() != null) {
            return getDialog().isShowing();
        }
        return false;
    }
}
