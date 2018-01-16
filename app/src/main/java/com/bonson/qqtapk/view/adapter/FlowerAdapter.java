package com.bonson.qqtapk.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.ViewGroup;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ItemFlowerBinding;
import com.bonson.qqtapk.model.bean.Flower;
import com.bonson.resource.adapter.BaseAdapter;
import com.bonson.resource.adapter.ViewHolder;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by zjw on 2018/1/4.
 */

public class FlowerAdapter extends BaseAdapter<Flower, ItemFlowerBinding> {

    @Inject
   public FlowerAdapter(Context context) {
        super(context);
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
