package com.bonson.qqtapk.view.ui.motion;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ActivityMotionBinding;
import com.bonson.qqtapk.model.bean.Motion;
import com.bonson.qqtapk.model.bean.Sleep;
import com.bonson.qqtapk.utils.binding.AdapterDataChangeFactory;
import com.bonson.resource.activity.BaseDaggerActivity;

import javax.inject.Inject;


public class MotionActivity extends BaseDaggerActivity<ActivityMotionBinding> {

    @Inject
    MotionViewModel viewModel;

    @Inject
    TableAdapter tableAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBindingLayout(R.layout.activity_motion);
        binding.setViewModel(viewModel);
        setViewModel(viewModel);
        binding.getRoot().setBackgroundColor(color(R.color.theme_yd));
        binding.toolbar.setTitle("运动睡眠");
        binding.toolbar.getTvLeft().setOnClickListener(v -> finish());
        binding.recTables.setAdapter(tableAdapter);
        binding.recTables.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        AdapterDataChangeFactory.create(tableAdapter).attach(viewModel.tables);
        binding.rgActionGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.rb_yd:
                    viewModel.motion();
                    binding.getRoot().setBackgroundColor(color(R.color.theme_yd));
                    break;
                case R.id.rb_sm:
                    viewModel.sleep();
                    binding.getRoot().setBackgroundColor(color(R.color.theme_sm));
                    break;
            }
        });
        binding.tbTimes.setAdapter(binding.recTables);
        binding.tbTimes.setOnPagerListener(position -> {
            if (viewModel.type.get() == 1) {
                Motion motion = viewModel.motions.get(position);
                binding.mv.moving(motion.getFstepsNumber(), "0公里 | 0千卡", 100);
            } else {
                Sleep sleep = viewModel.sleeps.get(position);
                binding.mv.sleeping(sleep.getFyestreenSL(), "昨晚共睡眠0分钟", 100);
            }
        });
        viewModel.motion();
    }
}
