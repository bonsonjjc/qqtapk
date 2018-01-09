package com.bonson.qqtapk.view.ui.lesson;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ActivityLessonBinding;
import com.bonson.qqtapk.view.adapter.LessonAdapter;
import com.bonson.resource.activity.BaseDaggerActivity;

import javax.inject.Inject;

/**
 * Created by jiangjiancheng on 17/12/31.
 */

public class LessonActivity extends BaseDaggerActivity {
    @Inject
    LessonViewModel viewModel;

    @Inject
    LessonAdapter lessonAdapter;

    @Inject
    RecyclerView.ItemDecoration itemDecoration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLessonBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_lesson);
        binding.setViewModel(viewModel);
        binding.toolbar.setTitle("上课静默");
        binding.toolbar.getTvLeft().setOnClickListener(v -> finish());
        binding.toolbar.setRightText("保存");
        binding.toolbar.getTvRight().setOnClickListener(v -> viewModel.update());
        binding.recNumbers.setAdapter(lessonAdapter);
        binding.recNumbers.addItemDecoration(itemDecoration);
        viewModel.setView(this);
        viewModel.lessons();
    }
}
