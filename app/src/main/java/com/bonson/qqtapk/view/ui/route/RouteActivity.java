package com.bonson.qqtapk.view.ui.route;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ActivityRouteBinding;
import com.bonson.resource.activity.BaseDaggerActivity;

import javax.inject.Inject;

/**
 * Created by jiangjiancheng on 17/12/31.
 */

public class RouteActivity extends BaseDaggerActivity {
    @Inject
    RouteViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityRouteBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_route);
        binding.setViewModel(viewModel);
        binding.toolbar.setTitle("路径查询");
        binding.toolbar.getTvLeft().setOnClickListener(view -> {
            finish();
        });
        binding.toolbar.getTvTitle().setOnClickListener(view -> {
            toast("显示对话框");
        });
        viewModel.setView(this);
        viewModel.routes("", "");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.onDestroy();
    }
}