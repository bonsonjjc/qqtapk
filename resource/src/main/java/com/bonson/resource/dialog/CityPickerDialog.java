package com.bonson.resource.dialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.PopupWindow;

import com.bonson.library.utils.DateUtils;
import com.bonson.resource.R;

import java.lang.reflect.Field;
import java.util.Date;

/**
 * Created by jiangjiancheng on 18/1/14.
 */

public class CityPickerDialog {
    private NumberPicker npProvince;
    private NumberPicker npCity;
    private NumberPicker npDistrict;

    private PopupWindow popupWindow;

    CityAdapter cityAdapter;
    private OnCitySaveListener onCitySaveListener;

    public CityPickerDialog(Context context) {
        View view = View.inflate(context, R.layout.city_picker, null);
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        npProvince = view.findViewById(R.id.np_province);
        npCity = view.findViewById(R.id.np_city);
        npDistrict = view.findViewById(R.id.np_district);
        npProvince.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int value) {
                if (cityAdapter == null) return "--";
                return cityAdapter.province(value);
            }
        });

        npCity.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int value) {
                if (cityAdapter == null) return "--";
                return cityAdapter.city(value);
            }
        });
        npDistrict.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int value) {
                if (cityAdapter == null) return "--";
                return cityAdapter.district(value);
            }
        });
        view.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        view.findViewById(R.id.tv_sure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onCitySaveListener != null) {
                    onCitySaveListener.onCity(npProvince.getValue(), npCity.getValue(), npDistrict.getValue());
                }
            }
        });

        npProvince.setOnScrollListener(new NumberPicker.OnScrollListener() {
            @Override
            public void onScrollStateChange(NumberPicker view, int scrollState) {
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
            @Override
            public void onScrollStateChange(NumberPicker view, int scrollState) {
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
            @Override
            public void onScrollStateChange(NumberPicker view, int scrollState) {
                if (scrollState != 0) {
                    npProvince.setEnabled(false);
                    npCity.setEnabled(false);
                    return;
                }
                npProvince.setEnabled(true);
                npCity.setEnabled(true);
            }
        });
    }

    public CityAdapter getCityAdapter() {
        return cityAdapter;
    }

    public void setCityAdapter(CityAdapter cityAdapter) {
        this.cityAdapter = cityAdapter;
        init();
    }

    private void init() {

        npProvince.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                npCity.setMinValue(0);
                npCity.setMaxValue(cityAdapter.citySize(newVal));

                npDistrict.setMinValue(0);
                npDistrict.setMaxValue(cityAdapter.districtSize(0));
            }
        });

        npCity.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
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

    private void setNumberPicker(NumberPicker numberPicker) {
        numberPicker.setDisplayedValues(null);
        numberPicker.setWrapSelectorWheel(true);
        numberPicker.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
        setNumberPickerDividerColor(numberPicker, 0xff4fc1e9);
    }

    public static void setNumberPickerDividerColor(NumberPicker numberPicker, int color) {
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

    public void show(View parent, int gravity) {
        popupWindow.showAtLocation(parent, gravity, 0, 0);
    }

    public boolean isShowing() {
        return popupWindow.isShowing();
    }

    public void dismiss() {
        popupWindow.dismiss();
    }

    public interface OnCitySaveListener {
        void onCity(int province, int city, int district);
    }

    public static CityPickerDialog builder(Context context) {
        return new CityPickerDialog(context);
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
