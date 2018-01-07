package com.bonson.qqtapk.view.ui.family;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ActivityFamilyBinding;
import com.bonson.resource.activity.BaseDaggerActivity;

import javax.inject.Inject;

/**
 * Created by jiangjiancheng on 17/12/31.
 */

public class FamilyActivity extends BaseDaggerActivity {
    @Inject
    FamilyViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityFamilyBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_family);
        binding.toolbar.setTitle("亲情号码");
        binding.toolbar.getTvLeft().setOnClickListener(v -> {
            finish();
        });
        viewModel.setView(this);
        binding.setViewModel(viewModel);
        viewModel.families();
    }
}
