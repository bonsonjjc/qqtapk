package com.bonson.qqtapk.view.ui.motion;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ActivityMotionBinding;
import com.bonson.resource.activity.BaseDaggerActivity;

import javax.inject.Inject;


public class MotionActivity extends BaseDaggerActivity<ActivityMotionBinding> {

    @Inject
    MotionViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBindingLayout(R.layout.activity_motion);
        binding.setViewModel(viewModel);
        setViewModel(viewModel);

        binding.toolbar.setTitle("运动睡眠");
        binding.toolbar.getTvLeft().setOnClickListener(v -> finish());
        viewModel.motion();
        viewModel.sleep();
    }
}
