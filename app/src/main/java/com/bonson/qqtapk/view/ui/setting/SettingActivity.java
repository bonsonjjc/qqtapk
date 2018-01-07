package com.bonson.qqtapk.view.ui.setting;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ActivitySettingBinding;
import com.bonson.qqtapk.model.bean.User;
import com.bonson.resource.activity.BaseDaggerActivity;

import javax.inject.Inject;

/**
 * Created by jiangjiancheng on 17/12/31.
 */

public class SettingActivity extends BaseDaggerActivity {
    @Inject
    SettingViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySettingBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_setting);
        binding.toolbar.setTitle("软件设置");
        binding.toolbar.getTvLeft().setOnClickListener(v -> {
            finish();
        });
        binding.setViewModel(viewModel);
        viewModel.mobile.set(User.user.getMobile());
    }
}
