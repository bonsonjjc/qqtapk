package com.bonson.qqtapk.view.ui.setting;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.app.Route;
import com.bonson.qqtapk.databinding.ActivitySettingBinding;
import com.bonson.qqtapk.model.bean.User;
import com.bonson.resource.activity.ActivityUtils;
import com.bonson.resource.activity.BaseDaggerActivity;
import com.bonson.resource.dialog.ActionSheetDialog;

import java.util.Stack;

import javax.inject.Inject;

/**
 * Created by jiangjiancheng on 17/12/31.
 */

public class SettingActivity extends BaseDaggerActivity {
    @Inject
    SettingViewModel viewModel;

    ActionSheetDialog sheetDialog = new ActionSheetDialog();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySettingBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_setting);
        binding.toolbar.setTitle("软件设置");
        binding.toolbar.getTvLeft().setOnClickListener(v -> finish());
        binding.setViewModel(viewModel);
        viewModel.setView(this);
    }

    public void itemClick(View view) {
        switch (view.getId()) {
            case R.id.tv_modify_password:
                start(Route.password);
                break;
            case R.id.tv_notify_set:
                start(Route.notify);
                break;
            case R.id.tv_online_service:
                viewModel.serverToken();
                break;
            case R.id.tv_about:
                start(Route.about);
                break;
            case R.id.tv_offline_map:
                start(Route.map);
                break;
            case R.id.tv_exit_login:
                sheetDialog.setTitle("是否退出登录?")
                        .setActionSheet(new String[]{"退出"}, Color.RED)
                        .setOnItemClickListener(position -> {
                            viewModel.exit();
                        })
                        .show(getSupportFragmentManager(), "exit");

                break;
        }
    }
}
