package com.bonson.qqtapk.view.ui.route;

import android.os.Bundle;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ActivityRouteBinding;
import com.bonson.resource.activity.BaseDaggerActivity;

import javax.inject.Inject;

/**
 * Created by jiangjiancheng on 17/12/31.
 */

public class RouteActivity extends BaseDaggerActivity<ActivityRouteBinding> {
    @Inject
    RouteViewModel viewModel;

    RouteTimeDialog timeDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBindingLayout(R.layout.activity_route);
        binding.setViewModel(viewModel);
        setViewModel(viewModel);

        binding.toolbar.getTvLeft().setOnClickListener(view -> finish());
        binding.toolbar.getTvTitle().setOnClickListener(view -> {
            if (timeDialog == null) {
                timeDialog = new RouteTimeDialog();
                timeDialog.setOnSaveListener((start, end) -> {
                    viewModel.routes(start, end);
                    timeDialog.dismiss();
                });
            }
            if (!timeDialog.isShowing()) {
                timeDialog.show(getSupportFragmentManager(), "time");
            }
        });
        viewModel.routes("", "");

    }
}
