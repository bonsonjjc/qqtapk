package com.bonson.qqtapk.view.ui.info;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ActivityInfoBinding;
import com.bonson.qqtapk.model.bean.Baby;
import com.bonson.resource.activity.BaseDaggerActivity;

import javax.inject.Inject;

/**
 * Created by jiangjiancheng on 17/12/31.
 */

public class InfoActivity extends BaseDaggerActivity {
    @Inject
    InfoViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityInfoBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_info);
        binding.setViewModel(viewModel);
        binding.toolbar.setTitle("宝贝信息");
        binding.toolbar.getTvLeft().setOnClickListener(v -> {
            finish();
        });
        viewModel.setBaby(Baby.baby);
    }
}
