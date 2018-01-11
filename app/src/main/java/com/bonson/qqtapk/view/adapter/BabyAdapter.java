package com.bonson.qqtapk.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.ViewGroup;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ItemBabyBinding;
import com.bonson.qqtapk.model.bean.Baby;
import com.bonson.resource.adapter.BaseAdapter;
import com.bonson.resource.adapter.ViewHolder;

import java.util.List;

public class BabyAdapter extends BaseAdapter<Baby, ItemBabyBinding> {

    public BabyAdapter(Context context, List<Baby> babies) {
        super(context, babies);
    }

    @Override
    public ViewHolder<ItemBabyBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemBabyBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_baby, parent, false);
        return new ViewHolder<>(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder<ItemBabyBinding> holder, int position) {
        Baby baby = beans.get(position);
        ItemBabyBinding binding = holder.getBinding();
        binding.setBaby(baby);
        binding.executePendingBindings();
    }
}
