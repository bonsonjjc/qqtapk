package com.bonson.qqtapk.view.ui.login;

import android.os.Bundle;
import android.view.View;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.app.Route;
import com.bonson.qqtapk.databinding.ActivityLoginBinding;
import com.bonson.resource.activity.BaseDaggerActivity;

import javax.inject.Inject;

/**
 * Created by zjw on 2017/12/29.
 */
public class LoginActivity extends BaseDaggerActivity<ActivityLoginBinding> {
    @Inject
    LoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBindingLayout(R.layout.activity_login);
        binding.setViewModel(viewModel);
        setViewModel(viewModel);

        binding.toolbar.setLeftText("返回");
        binding.toolbar.getTvLeft().setOnClickListener(view -> finish());
        binding.toolbar.setTitle("登录");
    }

    public void login(View view) {
        viewModel.login();
    }

    public void forget(View view) {
        start(Route.forget);
    }

    public void register(View view) {
        start(Route.register);
    }

}
