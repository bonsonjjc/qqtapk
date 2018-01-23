package com.bonson.qqtapk.view.ui.center;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.ViewGroup;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ItemCenterBinding;
import com.bonson.qqtapk.model.bean.Message;
import com.bonson.resource.adapter.BaseAdapter;
import com.bonson.resource.adapter.ViewHolder;

import java.util.List;

import javax.inject.Inject;

public class CenterAdapter extends BaseAdapter<Message, ItemCenterBinding> {
    @Inject
    public CenterAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder<ItemCenterBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemCenterBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_center, parent, false);
        return new ViewHolder<>(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder<ItemCenterBinding> holder, int position) {
        ItemCenterBinding binding = holder.getBinding();
        Message message = beans.get(position);
        binding.setMessasge(message);
        binding.getRoot().setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.itemClick(position);
            }
        });
    }
}
