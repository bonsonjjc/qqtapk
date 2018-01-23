package com.bonson.fjqqt.view.terminal.timer;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.ViewGroup;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ItemTimerBinding;
import com.bonson.resource.adapter.BaseAdapter;
import com.bonson.resource.adapter.ViewHolder;

import javax.inject.Inject;


public class TimerAdapter extends BaseAdapter<Timer, ItemTimerBinding> {
    @Inject
    public TimerAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder<ItemTimerBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemTimerBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_timer, parent, false);
        return new ViewHolder<>(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder<ItemTimerBinding> holder, int position) {
        Timer timer = beans.get(position);
        ItemTimerBinding binding = holder.getBinding();
        binding.setTimer(timer);
        binding.executePendingBindings();
        binding.getRoot().setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.itemClick(position);
            }
        });
    }
}
