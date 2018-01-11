package com.bonson.qqtapk.view.ui.voice;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ActivityVoiceBinding;
import com.bonson.qqtapk.view.adapter.VoiceAdapter;
import com.bonson.resource.activity.BaseDaggerActivity;

import javax.inject.Inject;

/**
 * Created by jiangjiancheng on 17/12/31.
 */

public class VoiceActivity extends BaseDaggerActivity {
    @Inject
    VoiceViewModel viewModel;

    @Inject
    VoiceAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityVoiceBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_voice);
        binding.setViewModel(viewModel);
        binding.toolbar.setTitle("语音群聊");
        binding.toolbar.getTvLeft().setOnClickListener(v -> {
            finish();
        });
        binding.recVoices.setAdapter(adapter);
        viewModel.setView(this);
        viewModel.voices();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.onDestroy();
    }
}
