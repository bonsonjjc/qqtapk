package com.bonson.qqtapk.view.ui.setting.notify;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.Gravity;

import com.bonson.library.utils.NumberUtils;
import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ActivityNotifyBinding;
import com.bonson.qqtapk.utils.TimeUtils;
import com.bonson.resource.activity.BaseDaggerActivity;
import com.bonson.resource.dialog.TimePickerDialog;

import javax.inject.Inject;

/**
 * Created by jiangjiancheng on 17/12/31.
 */

public class NotifyActivity extends BaseDaggerActivity {
    @Inject
    NotifyViewModel notifyViewModel;

    private TimePickerDialog timePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityNotifyBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_notify);
        binding.setViewModel(notifyViewModel);
        binding.toolbar.setTitle("通知设置");
        binding.toolbar.getTvLeft().setOnClickListener(v -> finish());
        binding.flSleep.setOnClickListener(v -> {

            if (timePickerDialog == null) {
                timePickerDialog = TimePickerDialog.builder(NotifyActivity.this);
                timePickerDialog.setOnSaveListener((startHour, startMinute, endHour, endMinute) -> {
                    String start = startHour + startMinute;
                    String end = endHour + endMinute;
                    timePickerDialog.dismiss();
                    notifyViewModel.sleepTime.set(start + "-" + end);
                    notifyViewModel.notifyChange();
                    notifyViewModel.sleepTime();
                });
            }
            String[] array = TimeUtils.split2(notifyViewModel.sleepTime.get());
            timePickerDialog.show(binding.getRoot(), Gravity.BOTTOM);
            timePickerDialog.setValue(array[0], array[1], array[2], array[3]);
        });
        notifyViewModel.setView(this);
        notifyViewModel.init();
    }
}
