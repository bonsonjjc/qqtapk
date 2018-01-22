package com.bonson.fjqqt.view.ui.route;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.bonson.fjqqt.view.ui.route.time.AddTimeFragment;
import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ActivityRouteTimeBinding;
import com.bonson.qqtapk.view.binding.AdapterDataChangeFactory;
import com.bonson.resource.activity.BaseDaggerActivity;

import javax.inject.Inject;


public class RouteListActivity extends BaseDaggerActivity<ActivityRouteTimeBinding> {
    @Inject
    RouteListViewModel viewModel;

    @Inject
    RouteTimeAdapter adapter;

    @Inject
    AddTimeFragment fragment;

    @Inject
    RecyclerView.ItemDecoration itemDecoration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBindingLayout(R.layout.activity_route_time);
        binding.setViewModel(viewModel);
        setViewModel(viewModel);

        binding.toolbar.getTvLeft().setOnClickListener(v -> finish());
        binding.toolbar.setTitle("路径时间");
        binding.toolbar.setRightText("添加");
        binding.toolbar.getTvRight().setOnClickListener(v -> {
            fragment.setViewModel(viewModel.add());
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(android.R.id.content, fragment)
                    .addToBackStack("add")
                    .commit();
        });
        binding.recNumbers.setAdapter(adapter);
        binding.recNumbers.addItemDecoration(itemDecoration);
        adapter.setOnItemClickListener(v -> {
            fragment.setViewModel(viewModel.edit(v));
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(android.R.id.content, fragment)
                    .addToBackStack("time")
                    .commit();
        });
        AdapterDataChangeFactory.create(adapter).attach(viewModel.routes);
        viewModel.times();
    }
}
