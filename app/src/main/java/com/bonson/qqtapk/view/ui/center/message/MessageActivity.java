package com.bonson.qqtapk.view.ui.center.message;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ActivityMessageBinding;
import com.bonson.resource.activity.BaseDaggerActivity;

import javax.inject.Inject;


public class MessageActivity extends BaseDaggerActivity {
    @Inject
    MessageViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMessageBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_message);
        binding.setViewModel(viewModel);
        binding.toolbar.getTvLeft().setOnClickListener(v -> finish());
        binding.toolbar.setTitle(getIntent().getStringExtra("title"));
    }
}
