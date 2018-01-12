package com.bonson.qqtapk.view.ui.index;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ActivityIndexBinding;
import com.bonson.qqtapk.view.adapter.BabyAdapter;
import com.bonson.qqtapk.view.ui.index.main.MainFragment;
import com.bonson.resource.activity.BaseDaggerActivity;

import javax.inject.Inject;

/**
 * Created by zjw on 2018/1/2.
 */

public class IndexActivity extends BaseDaggerActivity {
    @Inject
    IndexViewModel viewModel;

    @Inject
    MainFragment mainFragment;

    @Inject
    BabyAdapter babyAdapter;

    @Inject
    RecyclerView.ItemDecoration itemDecoration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityIndexBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_index);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.content, mainFragment)
                .commit();
        binding.setViewModel(viewModel);
        binding.recBabyList.setAdapter(babyAdapter);
        binding.recBabyList.addItemDecoration(itemDecoration);
        viewModel.setView(this);
        babyAdapter.setOnItemClickListener((v) -> {
            viewModel.change(v);
        });
        viewModel.babies();
    }
}
