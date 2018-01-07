package com.bonson.qqtapk.view.ui.forget;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ActivityForgetBinding;
import com.bonson.qqtapk.view.ui.register.VerifyViewModel;
import com.bonson.resource.activity.BaseDaggerActivity;

import javax.inject.Inject;

/**
 * Created by zjw on 2017/12/29.
 */

public class ForgetActivity extends BaseDaggerActivity {
    @Inject
    ForgetViewModel viewModel;

    @Inject
    VerifyViewModel verifyViewModel;

    ActivityForgetBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_forget);
        binding.setViewModel(viewModel);
        binding.toolbar.setTitle("找回密码");
        binding.toolbar.getTvLeft().setOnClickListener(view -> finish());
        binding.setViewModel(viewModel);
        viewModel.setView(this);

        binding.setVerifyViewModel(verifyViewModel);
        verifyViewModel.setView(this);
        verifyViewModel.verifyEnable.set(true);
        verifyViewModel.verifyText.set(getString(R.string.verify));
    }

    ResetFragment fragment;

    @Override
    public void start(String url) {
        binding.toolbar.setTitle(R.string.reset_password);
        fragment = ResetFragment.newInstance(viewModel.getMobile());
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.content, fragment)
                .commit();
    }
}
