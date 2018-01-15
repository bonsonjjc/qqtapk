package com.bonson.resource.dialog;

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
import com.bonson.library.utils.DateUtils;
import com.bonson.resource.R;
import com.bonson.resource.view.DatePicker;
import java.util.Date;

public class DatePickerDialog extends DialogFragment {

  private DatePicker datePicker;

  private int minYear, maxYear;

  private Date date;

  @Nullable @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.dialog_date_picker, container, false);
    datePicker = view.findViewById(R.id.dpDate);
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

  @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    datePicker.setMinYear(minYear);
    datePicker.setMaxYear(maxYear);
    datePicker.setDate(date);
    view.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        dismiss();
      }
    });
    view.findViewById(R.id.tv_sure).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        if (onDateListener != null) {
          Date time = datePicker.getCalendar().getTime();
          onDateListener.onDate(DateUtils.format(time, "yyyy-MM-dd"), time);
        }
      }
    });
  }

  public DatePickerDialog setDate(Date date) {
    this.date = date;
    return this;
  }

  public DatePickerDialog setMinYear(int minYear) {
    this.minYear = minYear;
    return this;
  }

  public DatePickerDialog setMaxYear(int maxYear) {
    this.maxYear = maxYear;
    return this;
  }

  private OnDateListener onDateListener;

  public void setOnDateListener(OnDateListener onDateListener) {
    this.onDateListener = onDateListener;
  }

  public interface OnDateListener {
    void onDate(String dateStr, Date date);
  }
}
