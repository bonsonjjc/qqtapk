package com.bonson.qqtapk.view.binding;

import android.databinding.BindingAdapter;
import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.databinding.InverseBindingAdapter;
import android.databinding.InverseBindingListener;
import android.databinding.InverseBindingMethod;
import android.databinding.InverseBindingMethods;
import android.widget.CompoundButton;

import com.bonson.resource.view.SwitchButton;

/**
 * Created by zjw on 2018/1/5.
 */
@InverseBindingMethods({
        @InverseBindingMethod(type = SwitchButton.class, attribute = "android:checked", event = "android:checkedAttrChanged"),
})
@BindingMethods({
        @BindingMethod(type = CompoundButton.class, attribute = "android:onCheckedChanged", method = "setOnCheckedChangeListener"),
})
public class SwitchButtonBindingAdapter {

    @BindingAdapter("android:checked")
    public static void setChecked(SwitchButton button, boolean checked) {
        if (button.isChecked() != checked)
            button.setChecked(checked);
    }

    @BindingAdapter(value = {"android:onCheckedChanged", "android:checkedAttrChanged"}, requireAll = false)
    public static void setOnChange(SwitchButton button, SwitchButton.OnCheckedChangeListener onChangeListener, InverseBindingListener inverseBindingListener) {
        button.setOnCheckedChangeListener(onChangeListener);
        if (inverseBindingListener == null) {
            button.setOnCheckedChangeListener(onChangeListener);
        } else {
            button.setOnCheckedChangeListener(isOn -> {
                if (onChangeListener != null) {
                    onChangeListener.onSwitchStateChange(isOn);
                }
                inverseBindingListener.onChange();
            });
        }
    }

    @InverseBindingAdapter(attribute = "android:checked", event = "android:checkedAttrChanged")
    public static Boolean isChecked(SwitchButton button) {
        return button.isChecked();
    }

}
