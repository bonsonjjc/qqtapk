package com.bonson.qqtapk.view.ui.setting.about;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ActivityAboutBinding;
import com.bonson.resource.activity.BaseDaggerActivity;

import javax.inject.Inject;

/**
 * Created by jiangjiancheng on 18/1/7.
 */

public class AboutActivity extends BaseDaggerActivity {
    @Inject
    AboutViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAboutBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_about);
        binding.toolbar.setTitle("关于我们");
        binding.toolbar.getTvLeft().setOnClickListener(v -> {
            finish();
        });
        binding.setViewModel(viewModel);
    }
}
