package com.bonson.qqtapk.view.ui.setting.map;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.ViewGroup;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ItemChildBinding;
import com.bonson.qqtapk.databinding.ItemDownloadBinding;
import com.bonson.resource.adapter.BaseAdapter;
import com.bonson.resource.adapter.ViewHolder;

import javax.inject.Inject;

public class DownloadAdapter extends BaseAdapter<OfflineMap, ItemDownloadBinding> {
    @Inject
    public DownloadAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder<ItemDownloadBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemDownloadBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_download, parent, false);
        return new ViewHolder<>(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder<ItemDownloadBinding> holder, int position) {
        ItemDownloadBinding binding = holder.getBinding();
        OfflineMap offlineMap = beans.get(position);
        binding.setData(offlineMap);
        binding.getRoot().setOnClickListener(v -> {
            if (null != onItemClickListener) {
                onItemClickListener.itemClick(position);
            }
        });
    }
}
