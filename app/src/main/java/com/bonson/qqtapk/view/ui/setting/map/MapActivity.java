package com.bonson.qqtapk.view.ui.setting.map;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ActivityMapBinding;
import com.bonson.resource.activity.BaseDaggerActivity;

import javax.inject.Inject;

/**
 * Created by jiangjiancheng on 18/1/7.
 */

public class MapActivity extends BaseDaggerActivity {
    @Inject
    MapViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMapBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_map);
        binding.toolbar.setTitle("离线地图");
        binding.toolbar.getTvLeft().setOnClickListener(v -> {
            finish();
        });
        binding.setViewModel(viewModel);
    }
}
