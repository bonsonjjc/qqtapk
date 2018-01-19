package com.bonson.qqtapk.view.binding;

import android.databinding.BindingAdapter;
import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.databinding.InverseBindingAdapter;
import android.databinding.InverseBindingListener;
import android.databinding.InverseBindingMethod;
import android.databinding.InverseBindingMethods;
import android.widget.NumberPicker;

import com.bonson.resource.view.SwitchButton;


@InverseBindingMethods({
        @InverseBindingMethod(type = NumberPicker.class, attribute = "android:value", event = "android:valueAttrChanged"),
})
@BindingMethods({
        @BindingMethod(type = NumberPicker.class, attribute = "android:onValueChanged", method = "setOnValueChangedListener"),
})
public class NumberPickerBindingAdapter {

    @BindingAdapter("android:value")
    public static void setValue(NumberPicker picker, int value) {
        if (picker.getValue() != value) {
            picker.setValue(value);
        }
    }

    @InverseBindingAdapter(attribute = "android:value", event = "android:valueAttrChanged")
    public static int getValue(NumberPicker picker) {
        return picker.getValue();
    }

    @BindingAdapter(value = {"android:onValueChanged", "android:valueAttrChanged"}, requireAll = false)
    public static void setOnValueChange(NumberPicker picker, NumberPicker.OnValueChangeListener onChangeListener, InverseBindingListener inverseBindingListener) {
        if (inverseBindingListener == null) {
            picker.setOnValueChangedListener(onChangeListener);
        } else {
            picker.setOnValueChangedListener((picker1, oldVal, newVal) -> {
                onChangeListener.onValueChange(picker1, oldVal, newVal);
                inverseBindingListener.onChange();
            });
        }
    }
}
