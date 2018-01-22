package com.bonson.qqtapk.view.ui.forget;

import android.os.Bundle;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ActivityForgetBinding;
import com.bonson.qqtapk.view.ui.register.VerifyViewModel;
import com.bonson.resource.activity.BaseDaggerActivity;

import javax.inject.Inject;

/**
 * Created by zjw on 2017/12/29.
 */

public class ForgetActivity extends BaseDaggerActivity<ActivityForgetBinding> {
    @Inject
    ForgetViewModel viewModel;

    @Inject
    VerifyViewModel verifyViewModel;

    @Inject
    ResetFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBindingLayout(R.layout.activity_forget);
        binding.setViewModel(viewModel);
        binding.setVerifyViewModel(verifyViewModel);
        setViewModel(viewModel,verifyViewModel);

        binding.toolbar.setTitle("找回密码");
        binding.toolbar.getTvLeft().setOnClickListener(view -> finish());

        verifyViewModel.verifyEnable.set(true);
        verifyViewModel.verifyText.set(getString(R.string.verify));
    }


    @Override
    public void start(String url) {
        binding.toolbar.setTitle(R.string.reset_password);
        fragment.viewModel = viewModel.viewModel;
        viewModel.viewModel.setOnSaveListener(() -> {
            viewModel.reset();
        });
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.content, fragment)
                .commit();
    }
}
