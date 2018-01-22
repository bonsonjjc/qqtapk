package com.bonson.fjqqt.view.ui.terminal.silence;

import android.os.Bundle;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ActivitySilenceBinding;
import com.bonson.resource.activity.BaseDaggerActivity;

import javax.inject.Inject;

public class SilenceActivity extends BaseDaggerActivity<ActivitySilenceBinding> {
    @Inject
    SilenceViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBindingLayout(R.layout.activity_silence);
        binding.setViewModel(viewModel);
        setViewModel(viewModel);

        binding.toolbar.getTvLeft().setOnClickListener(v -> finish());
        binding.toolbar.getTvRight().setOnClickListener(v -> {

        });
    }
}
