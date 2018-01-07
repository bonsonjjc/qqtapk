package com.bonson.qqtapk.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.ViewGroup;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ItemFlowerBinding;
import com.bonson.qqtapk.databinding.ItemLessonBinding;
import com.bonson.qqtapk.model.bean.Lesson;
import com.bonson.resource.fragment.BaseAdapter;
import com.bonson.resource.fragment.ViewHolder;

import java.util.List;

/**
 * Created by zjw on 2018/1/4.
 */

public class LessonAdapter extends BaseAdapter<Lesson, ItemLessonBinding> {
    public LessonAdapter(Context context, List<Lesson> contacts) {
        super(context, contacts);
    }

    @Override
    public ViewHolder<ItemLessonBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemLessonBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_lesson, parent, false);
        return new ViewHolder<>(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder<ItemLessonBinding> holder, int position) {
        Lesson lesson = beans.get(position);
        holder.getBinding().setLesson(lesson);
        holder.getBinding().executePendingBindings();
    }
}
