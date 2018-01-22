package com.bonson.qqtapk.view.ui.setting.about;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ActivityAboutBinding;
import com.bonson.resource.activity.BaseDaggerActivity;

import javax.inject.Inject;

/**
 * Created by jiangjiancheng on 18/1/7.
 */

public class AboutActivity extends BaseDaggerActivity<ActivityAboutBinding> {
    @Inject
    AboutViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBindingLayout(R.layout.activity_about);
        binding.setViewModel(viewModel);
        setViewModel(viewModel);

        binding.toolbar.setTitle("关于我们");
        binding.toolbar.getTvLeft().setOnClickListener(v -> {
            finish();
        });
    }

    public void itemClick(View view) {
        switch (view.getId()) {
            case R.id.fl_web_url:
                break;
            case R.id.fl_service_phone:
                break;
            case R.id.tv_protocol:
                break;
            case R.id.tv_declaration:
                break;
        }
    }
}
