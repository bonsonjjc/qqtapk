package com.bonson.qqtapk.view.ui.limits;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ActivityLimitsBinding;
import com.bonson.qqtapk.view.ui.limits.add.LimitFragment;
import com.bonson.resource.activity.BaseDaggerActivity;

import javax.inject.Inject;

/**
 * Created by jiangjiancheng on 17/12/31.
 */

public class LimitsActivity extends BaseDaggerActivity {
    @Inject
    LimitsViewModel viewModel;

    @Inject
    LimitFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLimitsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_limits);
        binding.toolbar.setTitle("呼入限制");
        binding.toolbar.getTvLeft().setOnClickListener(view -> finish());
        binding.toolbar.setRightText("添加");
        binding.toolbar.getTvRight().setOnClickListener(view -> {
            fragment.setViewModel(viewModel.addViewModel());
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(android.R.id.content, fragment)
                    .addToBackStack("limit")
                    .commit();
        });
        binding.setViewModel(viewModel);
        viewModel.setView(this);
        viewModel.limits();
    }
}
