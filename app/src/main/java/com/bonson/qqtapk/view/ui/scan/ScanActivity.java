package com.bonson.qqtapk.view.ui.scan;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ActivityScanBinding;
import com.bonson.resource.activity.BaseDaggerActivity;

import javax.inject.Inject;

public class ScanActivity extends BaseDaggerActivity {
    @Inject
    ScanViewModel scanViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityScanBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_scan);
        binding.toolbar.setTitle("添加宝贝");
        binding.toolbar.getTvLeft().setOnClickListener(v -> finish());
        binding.toolbar.getTvRight().setText("手动输入");
        binding.toolbar.getTvRight().setOnClickListener(v -> {

        });
        binding.setViewModel(scanViewModel);
        scanViewModel.init();
    }
}

