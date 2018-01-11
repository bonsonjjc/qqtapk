package com.bonson.qqtapk.view.ui.info.select;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ItemSelectBinding;
import com.bonson.resource.adapter.BaseAdapter;
import com.bonson.resource.adapter.ViewHolder;

import java.util.List;


public class SelectAdapter extends BaseAdapter<Select, ItemSelectBinding> {
    public SelectAdapter(Context context, List<Select> selects) {
        super(context, selects);
    }

    @Override
    public ViewHolder<ItemSelectBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemSelectBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_select, parent, false);
        return new ViewHolder<>(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder<ItemSelectBinding> holder, int position) {
        Select select = beans.get(position);
        ItemSelectBinding binding = holder.getBinding();
        binding.setSelect(select);
        binding.executePendingBindings();
        binding.getRoot().setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.itemClick(position);
            }
        });
    }
}
