package com.bonson.qqtapk.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.View;
import android.view.ViewGroup;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ItemLessonBinding;
import com.bonson.qqtapk.model.bean.Lesson;
import com.bonson.resource.adapter.BaseAdapter;
import com.bonson.resource.adapter.ViewHolder;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by zjw on 2018/1/4.
 */

public class LessonAdapter extends BaseAdapter<Lesson, ItemLessonBinding> {
    @Inject
   public LessonAdapter(Context context) {
        super(context);
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
        holder.itemView.setOnClickListener((View v) -> {
            if (null != onItemClickListener) {
                onItemClickListener.itemClick(position);
            }
        });
    }
}
