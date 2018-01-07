package com.bonson.qqtapk.view.ui.setting.notify;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ActivityNotifyBinding;
import com.bonson.resource.activity.BaseDaggerActivity;

import javax.inject.Inject;

/**
 * Created by jiangjiancheng on 17/12/31.
 */

public class NotifyActivity extends BaseDaggerActivity {
    @Inject
    NotifyViewModel notifyViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityNotifyBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_notify);
        binding.setViewModel(notifyViewModel);
        binding.toolbar.setTitle("通知设置");
        binding.toolbar.getTvLeft().setOnClickListener(v -> {
            finish();
        });
        notifyViewModel.setView(this);
    }
}
