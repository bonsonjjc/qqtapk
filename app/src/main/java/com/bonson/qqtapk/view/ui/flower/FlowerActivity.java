package com.bonson.qqtapk.view.ui.flower;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ActivityFlowerBinding;
import com.bonson.resource.activity.BaseDaggerActivity;

import javax.inject.Inject;

/**
 * Created by jiangjiancheng on 17/12/31.
 */

public class FlowerActivity extends BaseDaggerActivity {
    @Inject
    FlowerViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityFlowerBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_flower);
        binding.toolbar.setTitle("小红花");
        binding.toolbar.getTvLeft().setOnClickListener(v -> {
            finish();
        });
        binding.setViewModel(viewModel);
        viewModel.setView(this);
        viewModel.flowers(0, 10);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.onDestroy();
    }
}
