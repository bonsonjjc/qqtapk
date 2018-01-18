package com.bonson.fjqqt.view.ui.terminal.alarm;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.bonson.fjqqt.view.ui.terminal.alarm.add.AddAlarmFragment;
import com.bonson.fjqqt.view.ui.terminal.alarm.add.AddAlarmViewModel;
import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ActivityAlarmBinding;
import com.bonson.resource.activity.BaseDaggerActivity;

import javax.inject.Inject;


public class AlarmActivity extends BaseDaggerActivity {
    @Inject
    AlarmViewModel viewModel;

    @Inject
    AddAlarmViewModel addAlarmViewModel;

    @Inject
    AddAlarmFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAlarmBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_alarm);
        binding.toolbar.setTitle("生活提醒");
        binding.toolbar.getTvLeft().setOnClickListener(v -> back());
        binding.toolbar.setRightText("添加");
        binding.toolbar.getTvRight().setOnClickListener(v -> {
            fragment.setViewModel(addAlarmViewModel);
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(android.R.id.content, fragment)
                    .addToBackStack("add")
                    .commit();
        });
        binding.setViewModel(viewModel);
        viewModel.setView(this);
        viewModel.alarms();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.onDestroy();
    }
}
