package com.bonson.fjqqt.view.terminal.timer;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ActivityTimerBinding;
import com.bonson.qqtapk.utils.binding.AdapterDataChangeFactory;
import com.bonson.resource.activity.BaseDaggerActivity;
import com.bonson.resource.dialog.TimeDialog;

import javax.inject.Inject;

public class TimerActivity extends BaseDaggerActivity<ActivityTimerBinding> {
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
        setBindingLayout(R.layout.activity_timer);
        binding.setViewModel(viewModel);
        setViewModel(viewModel);

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
        AdapterDataChangeFactory.create(timerAdapter).attach(viewModel.timers);
        viewModel.timers();
    }
}
