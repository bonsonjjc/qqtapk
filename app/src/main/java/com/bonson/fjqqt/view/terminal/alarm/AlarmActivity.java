package com.bonson.fjqqt.view.terminal.alarm;

import android.graphics.Color;
import android.os.Bundle;

import com.bonson.fjqqt.view.terminal.alarm.add.AddAlarmFragment;
import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ActivityAlarmBinding;
import com.bonson.qqtapk.utils.binding.AdapterDataChangeFactory;
import com.bonson.qqtapk.view.ui.info.select.SelectFragment;
import com.bonson.resource.activity.BaseDaggerActivity;
import com.bonson.resource.dialog.ActionSheetDialog;

import javax.inject.Inject;


public class AlarmActivity extends BaseDaggerActivity<ActivityAlarmBinding> {
    @Inject
    AlarmViewModel viewModel;

    @Inject
    AddAlarmFragment fragment;

    @Inject
    SelectFragment selectFragment;

    @Inject
    AlarmAdapter alarmAdapter;

    ActionSheetDialog deleteDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBindingLayout(R.layout.activity_alarm);
        binding.setViewModel(viewModel);
        setViewModel(viewModel);

        binding.toolbar.setTitle("生活提醒");
        binding.toolbar.getTvLeft().setOnClickListener(v -> back());
        binding.toolbar.setRightText("添加");
        binding.toolbar.getTvRight().setOnClickListener(v -> {
            fragment.setSelectFragment(selectFragment);
            fragment.setViewModel(viewModel.getAddAlarmViewModel());
            viewModel.getAddAlarmViewModel().setAlarm(AlarmUtils.newAlarm());
            fragment.setOnAlarmSaveListener((it) -> {
                viewModel.alarm(-1, it);
            });
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(android.R.id.content, fragment)
                    .addToBackStack("add")
                    .commit();

        });

        binding.recAlarms.setAdapter(alarmAdapter);
        viewModel.alarms();
        AdapterDataChangeFactory.create(alarmAdapter).attach(viewModel.alarms);

        alarmAdapter.setOnItemClickListener(v -> {
            fragment.setSelectFragment(selectFragment);
            fragment.setViewModel(viewModel.getAddAlarmViewModel());
            viewModel.getAddAlarmViewModel().setAlarm(viewModel.alarms.get(v));
            fragment.setOnAlarmSaveListener((alarm) -> {
                alarm.setFtype("2");
                viewModel.alarm(v, alarm);
            });
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(android.R.id.content, fragment)
                    .addToBackStack("add")
                    .commit();
        });
        alarmAdapter.setOnItemLongClickListener(v -> {
            if (deleteDialog == null) {
                deleteDialog = new ActionSheetDialog();
                deleteDialog.setTitle("是否删除?");
                deleteDialog.addActionSheet("删除", Color.RED);
                deleteDialog.setOnItemClickListener(position -> {
                    Alarm alarm = viewModel.alarms.get(position);
                    alarm.setFtype("3");
                    viewModel.alarm(v, alarm);
                });
            }
            deleteDialog.show(getSupportFragmentManager(), "delete");
            return true;
        });
    }
}
