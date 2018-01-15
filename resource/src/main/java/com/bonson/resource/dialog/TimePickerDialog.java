package com.bonson.resource.dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.NumberPicker;

import com.bonson.library.utils.NumberUtils;
import com.bonson.resource.R;
import com.bonson.resource.view.TimePicker;

public class TimePickerDialog extends DialogFragment {
  private TimePicker tpStart;
  private TimePicker tpEnd;
  private int startHour;
  private int startMinute;
  private int endHour;
  private int endMinute;

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.dialog_time_picker, container, false);
    tpStart = view.findViewById(R.id.tp_start);
    tpEnd = view.findViewById(R.id.tp_end);
    view.findViewById(R.id.tv_sure).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        if (onSaveListener != null) {
          onSaveListener.onSave(hourFormatter.format(tpStart.getHour()),
              minuteFormatter.format(tpStart.getMinute()), hourFormatter.format(tpEnd.getHour()),
              minuteFormatter.format(tpEnd.getMinute()));
        }
      }
    });
    view.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        dismiss();
      }
    });
    return view;
  }

  @Override public void onResume() {
    super.onResume();
    getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    WindowManager.LayoutParams attributes = getDialog().getWindow().getAttributes();
    attributes.width = ViewGroup.LayoutParams.MATCH_PARENT;
    getDialog().getWindow().setAttributes(attributes);
    getDialog().getWindow().setGravity(Gravity.BOTTOM);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    tpStart.setHourFormatter(hourFormatter);
    tpStart.setMinuteFormatter(minuteFormatter);
    tpStart.setWheel(true);
    tpStart.setHour(0, 23);
    tpStart.setMinute(0, 11);

    tpEnd.setHourFormatter(hourFormatter);
    tpEnd.setMinuteFormatter(minuteFormatter);
    tpEnd.setWheel(true);
    tpEnd.setHour(0, 23);
    tpEnd.setMinute(0, 11);

    tpStart.setTime(startHour, startMinute);
    tpEnd.setTime(endHour, endMinute);
  }

  public TimePickerDialog setValue(int startHour, int startMinute, int endHour, int endMinute) {
    this.startHour = startHour;
    this.startMinute = startMinute;
    this.endHour = endHour;
    this.endMinute = endMinute;
    return this;
  }

  public TimePickerDialog setValue(String startHour, String startMinute, String endHour,
      String endMinute) {
    setValue(NumberUtils.parseInt(startHour), NumberUtils.parseInt(startMinute),
        NumberUtils.parseInt(endHour), NumberUtils.parseInt(endMinute));
    return this;
  }

  private NumberPicker.Formatter hourFormatter = new NumberPicker.Formatter() {
    @Override public String format(int value) {
      return String.format("%02d", value);
    }
  };
  private NumberPicker.Formatter minuteFormatter = new NumberPicker.Formatter() {
    @Override public String format(int value) {
      return String.format("%02d", value * 5);
    }
  };

  private OnSaveListener onSaveListener;

  public void setOnSaveListener(OnSaveListener onSaveListener) {
    this.onSaveListener = onSaveListener;
  }

  public interface OnSaveListener {
    void onSave(String startHour, String startMinute, String endHour, String endMinute);
  }
}
