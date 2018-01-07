package com.bonson.qqtapk.view.ui.setting.password;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ActivityPasswordBinding;
import com.bonson.resource.activity.BaseDaggerActivity;

import javax.inject.Inject;

/**
 * Created by jiangjiancheng on 17/12/31.
 */

public class PasswordActivity extends BaseDaggerActivity {
    @Inject
    PasswordViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityPasswordBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_password);
        binding.setViewModel(viewModel);
        binding.toolbar.setTitle("修改密码");
        binding.toolbar.getTvLeft().setOnClickListener(v -> {
            finish();
        });
        binding.toolbar.setRightText("修改");
        binding.toolbar.getTvRight().setOnClickListener(v -> {
            viewModel.modify();
        });
        viewModel.setView(this);
    }
}
