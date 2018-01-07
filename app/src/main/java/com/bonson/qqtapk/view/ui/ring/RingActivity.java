package com.bonson.qqtapk.view.ui.ring;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ActivityRingBinding;
import com.bonson.resource.activity.BaseDaggerActivity;

import javax.inject.Inject;

/**
 * Created by jiangjiancheng on 18/1/8.
 */

public class RingActivity extends BaseDaggerActivity {
    @Inject
    RingViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityRingBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_ring);
        binding.toolbar.setTitle("铃声设置");
        binding.toolbar.getTvLeft().setOnClickListener(view -> {
            finish();
        });
        binding.setViewModel(viewModel);
    }
}
