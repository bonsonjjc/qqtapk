package com.bonson.fjqqt.view.ui.route;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.ViewGroup;

import com.bonson.fjqqt.model.bean.Route;
import com.bonson.fjqqt.model.bean.RouteTime;
import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ItemTimeBinding;
import com.bonson.resource.adapter.BaseAdapter;
import com.bonson.resource.adapter.ViewHolder;

import javax.inject.Inject;

public class RouteTimeAdapter extends BaseAdapter<RouteTime, ItemTimeBinding> {
    @Inject
    public RouteTimeAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder<ItemTimeBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemTimeBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_time, parent, false);
        return new ViewHolder<>(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder<ItemTimeBinding> holder, int position) {
        RouteTime route = beans.get(position);
        ItemTimeBinding binding = holder.getBinding();
        binding.setData(route);
        binding.executePendingBindings();

        binding.getRoot().setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.itemClick(position);
            }
        });
    }
}
