package com.bonson.fjqqt.view.ui.terminal.timer;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ActivityTimerBinding;
import com.bonson.resource.activity.BaseDaggerActivity;
import com.bonson.resource.dialog.TimeDialog;
import com.bonson.resource.dialog.TimePickerDialog;

import javax.inject.Inject;

public class TimerActivity extends BaseDaggerActivity {
    @Inject
    TimerViewModel viewModel;

    @Inject
    TimerAdapter timerAdapter;

    TimeDialog dialog;

    @Inject
    RecyclerView.ItemDecoration itemDecoration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityTimerBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_timer);
        binding.setViewModel(viewModel);

        binding.toolbar.setTitle("定时定位");
        binding.toolbar.getTvLeft().setOnClickListener(v -> back());
        binding.toolbar.setRightText("保存");
        binding.toolbar.getTvRight().setOnClickListener(v -> viewModel.update());

        binding.recTimers.addItemDecoration(itemDecoration);
        binding.recTimers.setAdapter(timerAdapter);

        timerAdapter.setOnItemClickListener(v -> {
            if (dialog == null) {
                dialog = new TimeDialog();
            }
            Timer timer = viewModel.timers.get(v);
            String[] times = timer.getFtimes().split(":");
            dialog.setValue(times[0], times[1]);
            dialog.setOnSaveListener((hour, minute) -> {
                timer.setFtimes(hour + ":" + minute);
                viewModel.notifyChange();
                dialog.dismiss();
            });
            dialog.show(getSupportFragmentManager(), "time");
        });
        viewModel.setView(this);
        viewModel.timers();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.onDestroy();
    }
}
