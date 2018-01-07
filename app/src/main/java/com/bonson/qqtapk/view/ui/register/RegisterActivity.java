package com.bonson.qqtapk.view.ui.register;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ActivityRegisterBinding;
import com.bonson.resource.activity.BaseDaggerActivity;

import javax.inject.Inject;

/**
 * Created by zjw on 2017/12/29.
 */

public class RegisterActivity extends BaseDaggerActivity {
    @Inject
    RegisterViewModel viewModel;

    @Inject
    VerifyViewModel verifyViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityRegisterBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        binding.toolbar.setTitle("注册");
        binding.toolbar.getTvLeft().setOnClickListener(view -> {
            finish();
        });
        binding.setViewModel(viewModel);
        viewModel.setView(this);

        binding.setVerifyViewModel(verifyViewModel);
        verifyViewModel.setView(this);
        verifyViewModel.verifyEnable.set(true);
        verifyViewModel.verifyText.set(getString(R.string.verify));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.onDestroy();
        verifyViewModel.onDestroy();
    }
}
