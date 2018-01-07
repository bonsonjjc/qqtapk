package com.bonson.qqtapk.view.ui.lesson;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ActivityLessonBinding;
import com.bonson.resource.activity.BaseDaggerActivity;

import javax.inject.Inject;

/**
 * Created by jiangjiancheng on 17/12/31.
 */

public class LessonActivity extends BaseDaggerActivity {
    @Inject
    LessonViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLessonBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_lesson);
        binding.setViewModel(viewModel);
        binding.toolbar.setTitle("上课静默");
        binding.toolbar.getTvLeft().setOnClickListener(v -> {
            finish();
        });
        binding.toolbar.setRightText("保存");
        binding.toolbar.getTvRight().setOnClickListener(v -> {
            viewModel.update();
        });
        viewModel.setView(this);
        viewModel.lessons();
    }
}
