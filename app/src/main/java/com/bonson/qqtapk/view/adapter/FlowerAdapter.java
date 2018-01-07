package com.bonson.qqtapk.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.ViewGroup;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ItemContacsBinding;
import com.bonson.qqtapk.databinding.ItemFlowerBinding;
import com.bonson.qqtapk.model.bean.Flower;
import com.bonson.resource.fragment.BaseAdapter;
import com.bonson.resource.fragment.ViewHolder;

import java.util.List;

/**
 * Created by zjw on 2018/1/4.
 */

public class FlowerAdapter extends BaseAdapter<Flower, ItemFlowerBinding> {

    public FlowerAdapter(Context context, List<Flower> contacts) {
        super(context, contacts);
    }

    @Override
    public ViewHolder<ItemFlowerBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemFlowerBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_flower, parent, false);
        return new ViewHolder<>(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder<ItemFlowerBinding> holder, int position) {
        Flower flower = beans.get(position);
        holder.getBinding().setFlower(flower);
        holder.getBinding().executePendingBindings();
    }
}
