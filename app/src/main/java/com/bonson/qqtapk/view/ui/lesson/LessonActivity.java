package com.bonson.qqtapk.view.ui.lesson;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.bonson.library.utils.NumberUtils;
import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ActivityLessonBinding;
import com.bonson.qqtapk.model.bean.Lesson;
import com.bonson.qqtapk.utils.TimeUtils;
import com.bonson.qqtapk.view.adapter.LessonAdapter;
import com.bonson.qqtapk.view.binding.AdapterDataChangeFactory;
import com.bonson.resource.activity.BaseDaggerActivity;
import com.bonson.resource.dialog.TimePickerDialog;

import javax.inject.Inject;

/**
 * Created by jiangjiancheng on 17/12/31.
 */

public class LessonActivity extends BaseDaggerActivity<ActivityLessonBinding> {
    @Inject
    LessonViewModel viewModel;

    @Inject
    LessonAdapter lessonAdapter;

    @Inject
    RecyclerView.ItemDecoration itemDecoration;

    TimePickerDialog picker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBindingLayout(R.layout.activity_lesson);
        binding.setViewModel(viewModel);
        setViewModel(viewModel);

        binding.toolbar.setTitle("上课静默");
        binding.toolbar.getTvLeft().setOnClickListener(v -> finish());
        binding.toolbar.setRightText("保存");
        binding.toolbar.getTvRight().setOnClickListener(v -> viewModel.update());

        binding.recNumbers.setAdapter(lessonAdapter);
        binding.recNumbers.addItemDecoration(itemDecoration);
        AdapterDataChangeFactory
                .create(lessonAdapter)
                .attach(viewModel.lessons);

        lessonAdapter.setOnItemClickListener(this::showTime);
        viewModel.lessons();
    }

    private void showTime(int index) {
        if (picker == null) {
            picker = new TimePickerDialog();
        }
        Lesson lesson = viewModel.lessons.get(index);
        String[] startTime = TimeUtils.split(lesson.getFbegin());
        String[] endTime = TimeUtils.split(lesson.getFend());
        picker.setValue(startTime[0], startTime[1], endTime[0], endTime[1]);
        picker.setOnSaveListener((startHour, startMinute, endHour, endMinute) -> {
            String start = startHour + startMinute;
            String end = endHour + endMinute;
            if (NumberUtils.parseInt(start) < NumberUtils.parseInt(end)) {
                lesson.setFbegin(start);
                lesson.setFend(end);
                viewModel.notifyChange();
                picker.dismiss();
            } else {
                toast("开始时间不能大于等于结束时间");
            }
        });
        picker.show(getSupportFragmentManager(), "edit");
    }
}
