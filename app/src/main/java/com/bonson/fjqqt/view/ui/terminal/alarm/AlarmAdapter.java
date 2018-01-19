package com.bonson.fjqqt.view.ui.terminal.alarm;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.View;
import android.view.ViewGroup;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ItemAlarmBinding;
import com.bonson.resource.adapter.BaseAdapter;
import com.bonson.resource.adapter.ViewHolder;

import javax.inject.Inject;


public class AlarmAdapter extends BaseAdapter<Alarm, ItemAlarmBinding> {
    @Inject
    public AlarmAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder<ItemAlarmBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemAlarmBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_alarm, parent, false);
        return new ViewHolder<>(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder<ItemAlarmBinding> holder, int position) {
        ItemAlarmBinding binding = holder.getBinding();
        Alarm alarm = beans.get(position);
        binding.setAlarm(alarm);
        binding.executePendingBindings();
        binding.getRoot().setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.itemClick(position);
            }
        });
        binding.getRoot().setOnLongClickListener(v -> {
            if (onItemLongClickListener != null) {
                return onItemLongClickListener.itemLongClick(position);
            }
            return false;
        });


    }
}
