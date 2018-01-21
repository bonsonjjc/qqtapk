package com.bonson.fjqqt.view.ui.terminal.silence;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ActivitySilenceBinding;
import com.bonson.resource.activity.BaseDaggerActivity;

import javax.inject.Inject;

public class SilenceActivity extends BaseDaggerActivity {
    @Inject
    SilenceViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySilenceBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_silence);
        binding.toolbar.getTvLeft().setOnClickListener(v -> finish());
        binding.toolbar.getTvRight().setOnClickListener(v -> {

        });
        viewModel.setView(this);
        binding.setViewModel(viewModel);
    }
}
