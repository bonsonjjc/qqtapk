package com.bonson.qqtapk.view.ui.ring;

import android.os.Bundle;
import android.view.View;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ActivityRingBinding;
import com.bonson.library.utils.media.PlayerUtils;
import com.bonson.qqtapk.view.ui.info.select.SelectFragment;
import com.bonson.qqtapk.view.ui.info.select.SelectViewModel;
import com.bonson.resource.activity.BaseDaggerActivity;

import javax.inject.Inject;

/**
 * Created by jiangjiancheng on 18/1/8.
 */

public class RingActivity extends BaseDaggerActivity<ActivityRingBinding> {
    @Inject
    RingViewModel viewModel;

    @Inject
    SelectFragment selectFragment;

    @Inject
    PlayerUtils playerUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBindingLayout(R.layout.activity_ring);
        binding.setViewModel(viewModel);
        setViewModel(viewModel);

        binding.toolbar.setTitle("铃声设置");
        binding.toolbar.getTvLeft().setOnClickListener(view -> finish());
        binding.toolbar.setRightText("保存");
        binding.toolbar.getTvRight().setOnClickListener(v -> viewModel.setRing());
        viewModel.ring();
    }

    public void selectRing(View view) {
        SelectViewModel selectViewModel = viewModel.viewModel();
        selectFragment.setViewModel(selectViewModel);
        getSupportFragmentManager()
                .beginTransaction()
                .add(android.R.id.content, selectFragment)
                .addToBackStack("ring")
                .commit();
    }

    @Override
    public void onBackPressed() {
        playerUtils.stop();
        super.onBackPressed();
    }
}
