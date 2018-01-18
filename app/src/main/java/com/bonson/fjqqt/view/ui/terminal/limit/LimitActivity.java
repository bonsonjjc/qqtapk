package com.bonson.fjqqt.view.ui.terminal.limit;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ActivityLimitBinding;
import com.bonson.resource.activity.BaseDaggerActivity;

import javax.inject.Inject;


public class LimitActivity extends BaseDaggerActivity {

    @Inject
    LimitViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLimitBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_limit);
        binding.toolbar.setTitle("生活提醒");
        binding.toolbar.getTvLeft().setOnClickListener(v -> back());
        binding.toolbar.setRightText("添加");
        binding.toolbar.getTvRight().setOnClickListener(v -> {

        });
        binding.setViewModel(viewModel);
    }
}
