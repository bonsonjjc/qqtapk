package com.bonson.qqtapk.view.ui.flower;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ActivityFlowerBinding;
import com.bonson.resource.activity.BaseDaggerActivity;

import javax.inject.Inject;

/**
 * Created by jiangjiancheng on 17/12/31.
 */

public class FlowerActivity extends BaseDaggerActivity<ActivityFlowerBinding> {
    @Inject
    FlowerViewModel viewModel;

    @Inject
    FlowerAdapter flowerAdapter;

    @Inject
    RecyclerView.ItemDecoration itemDecoration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBindingLayout(R.layout.activity_flower);
        binding.setViewModel(viewModel);
        setViewModel(viewModel);

        binding.toolbar.setTitle("小红花");
        binding.toolbar.getTvLeft().setOnClickListener(v -> finish());
        binding.recFlowers.setAdapter(flowerAdapter);
        binding.recFlowers.addItemDecoration(itemDecoration);
        viewModel.flowers(0, 10);
    }
}
