package com.bonson.qqtapk.view.ui.register;

import android.os.Bundle;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ActivityRegisterBinding;
import com.bonson.resource.activity.BaseDaggerActivity;

import javax.inject.Inject;

/**
 * Created by zjw on 2017/12/29.
 */

public class RegisterActivity extends BaseDaggerActivity<ActivityRegisterBinding> {
    @Inject
    RegisterViewModel viewModel;

    @Inject
    VerifyViewModel verifyViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBindingLayout(R.layout.activity_register);
        binding.setViewModel(viewModel);
        binding.setVerifyViewModel(verifyViewModel);
        setViewModel(viewModel, verifyViewModel);

        binding.toolbar.setTitle("注册");
        binding.toolbar.getTvLeft().setOnClickListener(view -> {
            finish();
        });
        verifyViewModel.verifyEnable.set(true);
        verifyViewModel.verifyText.set(getString(R.string.verify));
    }
}
