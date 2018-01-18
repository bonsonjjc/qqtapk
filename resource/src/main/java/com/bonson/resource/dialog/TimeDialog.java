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

public class TimeDialog extends DialogFragment {
  private TimePicker timePicker;
  private int hour;
  private int minute;

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.dialog_time, container, false);
    timePicker = view.findViewById(R.id.tp_start);
    view.findViewById(R.id.tv_sure).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        if (onSaveListener != null) {
          onSaveListener.onSave(hourFormatter.format(timePicker.getHour()),
              minuteFormatter.format(timePicker.getMinute()));
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
    timePicker.setHourFormatter(hourFormatter);
    timePicker.setMinuteFormatter(minuteFormatter);
    timePicker.setWheel(true);
    timePicker.setHour(0, 23);
    timePicker.setMinute(0, 11);
    timePicker.setTime(hour, minute);
  }

  public TimeDialog setValue(int startHour, int startMinute) {
    this.hour = startHour;
    this.minute = startMinute;
    return this;
  }

  public TimeDialog setValue(String hour, String minute) {
    setValue(NumberUtils.parseInt(hour), NumberUtils.parseInt(minute));
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
    void onSave(String hour, String minute);
  }
}
