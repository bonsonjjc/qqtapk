package com.bonson.fjqqt.view.ui.terminal.limit;

import android.databinding.Observable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ActivityLimitBinding;
import com.bonson.qqtapk.view.adapter.LimitAdapter;
import com.bonson.qqtapk.view.binding.AdapterDataChangeFactory;
import com.bonson.qqtapk.view.ui.limits.add.LimitFragment;
import com.bonson.resource.activity.BaseDaggerActivity;

import javax.inject.Inject;


public class LimitsActivity extends BaseDaggerActivity<ActivityLimitBinding> {

    @Inject
    LimitFragment fragment;

    @Inject
    LimitAdapter adapter;

    @Inject
    RecyclerView.ItemDecoration itemDecoration;
    @Inject
    LimitsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBindingLayout(R.layout.activity_limit);
        binding.setViewModel(viewModel);
        setViewModel(viewModel);

        binding.toolbar.getTvLeft().setOnClickListener(view -> finish());
        binding.toolbar.getTvRight().setOnClickListener(view -> {
            fragment.setViewModel(viewModel.addViewModel());
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(android.R.id.content, fragment)
                    .addToBackStack("limit")
                    .commit();
        });
        binding.recLimits.setAdapter(adapter);
        binding.recLimits.addItemDecoration(itemDecoration);
        AdapterDataChangeFactory.create(adapter).attach(viewModel.limits);

        adapter.setOnItemClickListener(position -> {
            fragment.setViewModel(viewModel.updateViewModel(position));
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(android.R.id.content, fragment)
                    .addToBackStack("limit")
                    .commit();
        });

        viewModel.limits();
        viewModel.open.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                viewModel.updateState();
            }
        });
    }
}
