package com.bonson.qqtapk.view.ui.motion.target;

import android.os.Bundle;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ActivityTargetBinding;
import com.bonson.resource.activity.BaseDaggerActivity;

import javax.inject.Inject;

public class TargetActivity extends BaseDaggerActivity<ActivityTargetBinding> {
    @Inject
    TargetViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBindingLayout(R.layout.activity_target);
        binding.toolbar.getTvLeft().setOnClickListener(v -> finish());
        binding.toolbar.getTvRight().setOnClickListener(v -> viewModel.save());
        binding.setViewModel(viewModel);
        setViewModel(viewModel);
        viewModel.type.set(getIntent().getIntExtra("type", 1));
    }
}
