package com.bonson.fjqqt.view.ui.route;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.bonson.fjqqt.view.ui.route.time.AddTimeFragment;
import com.bonson.fjqqt.view.ui.route.time.AddTimeViewModel;
import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ActivityRouteTimeBinding;
import com.bonson.resource.activity.BaseDaggerActivity;

import javax.inject.Inject;


public class RouteListActivity extends BaseDaggerActivity {
    @Inject
    RouteListViewModel timeViewModel;

    @Inject
    TimeAdapter adapter;

    @Inject
    AddTimeFragment fragment;

    @Inject
    AddTimeViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityRouteTimeBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_route_time);
        binding.setViewModel(timeViewModel);
        binding.toolbar.getTvLeft().setOnClickListener(v -> finish());
        binding.toolbar.setTitle("路径时间");
        binding.toolbar.setRightText("添加");
        binding.toolbar.getTvRight().setOnClickListener(v -> {
            fragment.setViewModel(viewModel);
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(android.R.id.content,fragment)
                    .addToBackStack("add")
                    .commit();
        });
        binding.recNumbers.setAdapter(adapter);
        timeViewModel.setView(this);
        timeViewModel.times();
    }
}
