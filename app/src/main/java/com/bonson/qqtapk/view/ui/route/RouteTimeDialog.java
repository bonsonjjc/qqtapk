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

import com.bonson.qqtapk.R;
import com.bonson.resource.view.DatePicker;
import com.bonson.resource.view.TimePicker;

import java.util.Date;


public class RouteTimeDialog extends DialogFragment {
    private DatePicker datePicker;
    private TimePicker tpStart;
    private TimePicker tpEnd;
    private int minYear = 1990, maxYear = 2050;
    private Date date;
    private int startHour = 0;
    private int startMinute = 0;
    private int endHour = 0;
    private int endMinute = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_datetime_picker, container, false);
        datePicker = view.findViewById(R.id.dpDate);
        tpStart = view.findViewById(R.id.tp_start);
        tpEnd = view.findViewById(R.id.tp_end);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        datePicker.setMaxYear(maxYear);
        datePicker.setMinYear(minYear);
        date = new Date();
        datePicker.setDate(date);
        tpStart.setTime(startHour, startMinute);
        tpEnd.setTime(endHour, endMinute);
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

    public boolean isShowing() {
        if (getDialog() != null) {
            return getDialog().isShowing();
        }
        return false;
    }
}
