package com.bonson.qqtapk.view.ui.setting.notify;

import android.os.Bundle;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ActivityNotifyBinding;
import com.bonson.qqtapk.utils.TimeUtils;
import com.bonson.resource.activity.BaseDaggerActivity;
import com.bonson.resource.dialog.TimePickerDialog;

import javax.inject.Inject;

/**
 * Created by jiangjiancheng on 17/12/31.
 */

public class NotifyActivity extends BaseDaggerActivity<ActivityNotifyBinding> {
    @Inject
    NotifyViewModel viewModel;

    private TimePickerDialog timePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBindingLayout(R.layout.activity_notify);
        binding.setViewModel(viewModel);
        setViewModel(viewModel);

        binding.toolbar.setTitle("通知设置");
        binding.toolbar.getTvLeft().setOnClickListener(v -> finish());
        binding.flSleep.setOnClickListener(v -> {
            showTime();
        });
    }

    private void showTime() {
        if (timePickerDialog == null) {
            timePickerDialog = new TimePickerDialog();
            timePickerDialog.setOnSaveListener((startHour, startMinute, endHour, endMinute) -> {
                String start = startHour + startMinute;
                String end = endHour + endMinute;
                timePickerDialog.dismiss();
                viewModel.sleepTime.set(start + "-" + end);
                viewModel.notifyChange();
                viewModel.sleepTime();
            });
        }
        String[] array = TimeUtils.split2(viewModel.sleepTime.get());
        timePickerDialog.setValue(array[0], array[1], array[2], array[3]);
        timePickerDialog.show(getSupportFragmentManager(), "time");
    }
}
