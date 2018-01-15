package com.bonson.resource.dialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.PopupWindow;

import com.bonson.library.utils.DateUtils;
import com.bonson.library.utils.DensityUtils;
import com.bonson.resource.R;

import java.lang.reflect.Field;
import java.util.Date;

/**
 * Created by jiangjiancheng on 18/1/14.
 */

public class CityPickerDialog extends DialogFragment {
  private NumberPicker npProvince;
  private NumberPicker npCity;
  private NumberPicker npDistrict;

  CityAdapter cityAdapter;
  private OnCitySaveListener onCitySaveListener;

  public CityPickerDialog setCityAdapter(CityAdapter cityAdapter) {
    this.cityAdapter = cityAdapter;
    return this;
  }

  @Nullable @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.city_picker, container, false);
    npProvince = view.findViewById(R.id.np_province);
    npCity = view.findViewById(R.id.np_city);
    npDistrict = view.findViewById(R.id.np_district);
    npProvince.setFormatter(new NumberPicker.Formatter() {
      @Override public String format(int value) {
        if (cityAdapter == null) return "--";
        return cityAdapter.province(value);
      }
    });

    npCity.setFormatter(new NumberPicker.Formatter() {
      @Override public String format(int value) {
        if (cityAdapter == null) return "--";
        return cityAdapter.city(value);
      }
    });
    npDistrict.setFormatter(new NumberPicker.Formatter() {
      @Override public String format(int value) {
        if (cityAdapter == null) return "--";
        return cityAdapter.district(value);
      }
    });
    // 设置背景透明
    getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    // 去掉标题 死恶心死恶心的
    getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
    getDialog().getWindow().setGravity(Gravity.BOTTOM);
    view.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        dismiss();
      }
    });
    view.findViewById(R.id.tv_sure).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        if (onCitySaveListener != null) {
          onCitySaveListener.onCity(npProvince.getValue(), npCity.getValue(),
              npDistrict.getValue());
        }
      }
    });

    npProvince.setOnScrollListener(new NumberPicker.OnScrollListener() {
      @Override public void onScrollStateChange(NumberPicker view, int scrollState) {
        if (scrollState != 0) {
          npCity.setEnabled(false);
          npDistrict.setEnabled(false);
          return;
        }
        npCity.setEnabled(true);
        npDistrict.setEnabled(true);
      }
    });
    npCity.setOnScrollListener(new NumberPicker.OnScrollListener() {
      @Override public void onScrollStateChange(NumberPicker view, int scrollState) {
        if (scrollState != 0) {
          npProvince.setEnabled(false);
          npDistrict.setEnabled(false);
          return;
        }
        npProvince.setEnabled(true);
        npDistrict.setEnabled(true);
      }
    });
    npDistrict.setOnScrollListener(new NumberPicker.OnScrollListener() {
      @Override public void onScrollStateChange(NumberPicker view, int scrollState) {
        if (scrollState != 0) {
          npProvince.setEnabled(false);
          npCity.setEnabled(false);
          return;
        }
        npProvince.setEnabled(true);
        npCity.setEnabled(true);
      }
    });
    return view;
  }

  @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    npProvince.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
      @Override public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        npCity.setMinValue(0);
        npCity.setMaxValue(cityAdapter.citySize(newVal));

        npDistrict.setMinValue(0);
        npDistrict.setMaxValue(cityAdapter.districtSize(0));
      }
    });

    npCity.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
      @Override public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        npDistrict.setMinValue(0);
        npDistrict.setMaxValue(cityAdapter.districtSize(newVal));
      }
    });

    npProvince.setMinValue(0);
    npProvince.setMaxValue(cityAdapter.provinceSize());
    npCity.setMinValue(0);
    npCity.setMaxValue(cityAdapter.citySize(0));
    npDistrict.setMinValue(0);
    npDistrict.setMaxValue(cityAdapter.districtSize(0));
    setNumberPicker(npProvince);
    setNumberPicker(npCity);
    setNumberPicker(npDistrict);
  }

  @Override public void onResume() {
    super.onResume();
    WindowManager.LayoutParams mLayoutParams = getDialog().getWindow().getAttributes();
    mLayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
    mLayoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
    getDialog().getWindow().setAttributes(mLayoutParams);
  }

  private void setNumberPicker(NumberPicker numberPicker) {
    numberPicker.setDisplayedValues(null);
    numberPicker.setWrapSelectorWheel(true);
    numberPicker.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
    setNumberPickerDividerColor(numberPicker, 0xff4fc1e9);
  }

  public void setNumberPickerDividerColor(NumberPicker numberPicker, int color) {
    Field[] pickerFields = NumberPicker.class.getDeclaredFields();
    for (Field SelectionDividerField : pickerFields) {
      if (SelectionDividerField.getName().equals("mSelectionDivider")) {
        SelectionDividerField.setAccessible(true);
        try {
          SelectionDividerField.set(numberPicker, new ColorDrawable(color));
        } catch (IllegalAccessException e) {
          e.printStackTrace();
        }
        break;
      }
    }
  }

  public interface OnCitySaveListener {
    void onCity(int province, int city, int district);
  }

  public interface CityAdapter {
    int provinceSize();

    int citySize(int index);

    int districtSize(int index);

    String province(int index);

    String city(int index);

    String district(int index);
  }
}
