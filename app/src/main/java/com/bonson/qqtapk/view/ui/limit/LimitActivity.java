package com.bonson.qqtapk.view.ui.limit;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ActivityLimitBinding;
import com.bonson.resource.activity.BaseDaggerActivity;

import javax.inject.Inject;

/**
 * Created by jiangjiancheng on 17/12/31.
 */

public class LimitActivity extends BaseDaggerActivity {
    @Inject
    LimitViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLimitBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_limit);
        binding.setViewModel(viewModel);
        binding.toolbar.setTitle("呼入限制");
        binding.toolbar.getTvLeft().setOnClickListener(view -> {
            finish();
        });
        binding.toolbar.setRightText("添加");
        binding.toolbar.getTvRight().setOnClickListener(view -> {

        });
        binding.setViewModel(viewModel);
        viewModel.setView(this);
        viewModel.limits();
    }
}
