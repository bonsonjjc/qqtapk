package com.bonson.qqtapk.view.ui.voice;

import android.databinding.DataBindingUtil;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;

import com.bonson.library.utils.media.AudioRecorderUtils;
import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ActivityVoiceBinding;
import com.bonson.qqtapk.view.adapter.VoiceAdapter;
import com.bonson.qqtapk.view.widget.VoiceView;
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
    ActivityVoiceBinding binding;

    @Inject
    AudioRecorderUtils recorderUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_voice);
        binding.setViewModel(viewModel);
        binding.toolbar.setTitle("语音群聊");
        binding.btnSend.setOnListener(new VoiceView.OnListener() {
            @Override
            public void onDown() {
                binding.llVoiceTip.setVisibility(View.VISIBLE);
                send();
                recorderUtils.startRecord();
            }

            @Override
            public void onChange() {
                if (binding.btnSend.getModel() == VoiceView.sendModel) {
                    send();
                } else {
                    cancel();
                }
            }

            @Override
            public void onUp() {
                binding.llVoiceTip.setVisibility(View.GONE);
                int duration = recorderUtils.stopRecord();
                viewModel.send(duration + "", recorderUtils.getFilePath());
            }

            @Override
            public void onCancel() {
                recorderUtils.cancelRecord();
                binding.llVoiceTip.setVisibility(View.GONE);
            }
        });
        binding.toolbar.getTvLeft().setOnClickListener(v -> finish());
        binding.recVoices.setAdapter(adapter);
        viewModel.setView(this);
        viewModel.voices();
    }

    private AnimationDrawable aniamtionDrawable;

    public void send() {
        binding.tvVoiceText.setText(R.string.voice_send);
        binding.imgAnimation.setImageResource(R.drawable.speaking);
        aniamtionDrawable = (AnimationDrawable) binding.imgAnimation.getDrawable();
        aniamtionDrawable.start();
    }

    public void cancel() {
        binding.tvVoiceText.setText(R.string.voice_cancel);
        if (binding.imgAnimation.getDrawable() instanceof AnimationDrawable) {
            aniamtionDrawable = (AnimationDrawable) binding.imgAnimation.getDrawable();
            aniamtionDrawable.stop();
        }
        binding.imgAnimation.setImageResource(R.drawable.yy_qx);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.onDestroy();
    }
}
