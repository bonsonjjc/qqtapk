package com.bonson.qqtapk.view.ui.login;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.app.Route;
import com.bonson.qqtapk.databinding.ActivityLoginBinding;
import com.bonson.qqtapk.view.ui.forget.ForgetActivity;
import com.bonson.qqtapk.view.ui.index.IndexActivity;
import com.bonson.qqtapk.view.ui.register.RegisterActivity;
import com.bonson.resource.activity.BaseDaggerActivity;

import javax.inject.Inject;

/**
 * Created by zjw on 2017/12/29.
 */

public class LoginActivity extends BaseDaggerActivity {
    @Inject
    LoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLoginBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.toolbar.setLeftText("返回");
        binding.toolbar.getTvLeft().setOnClickListener(view -> finish());
        viewModel.setView(this);
        binding.toolbar.setTitle("登录");
        binding.setViewModel(viewModel);
        viewModel.init();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.onDestroy();
    }
}
