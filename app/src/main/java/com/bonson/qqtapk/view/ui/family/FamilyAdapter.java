package com.bonson.qqtapk.view.ui.family;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.View;
import android.view.ViewGroup;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ItemFamilyBinding;
import com.bonson.qqtapk.model.bean.Family;
import com.bonson.resource.adapter.BaseAdapter;
import com.bonson.resource.adapter.ViewHolder;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by jiangjiancheng on 18/1/7.
 */

public class FamilyAdapter extends BaseAdapter<Family, ItemFamilyBinding> {
    @Inject
    public FamilyAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder<ItemFamilyBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemFamilyBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_family, parent, false);
        return new ViewHolder<>(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder<ItemFamilyBinding> holder, int position) {
        Family family = beans.get(position);
        holder.getBinding().setFamily(family);
        holder.getBinding().executePendingBindings();
        holder.itemView.setOnClickListener((View v) -> {
            if (null != onItemClickListener) {
                onItemClickListener.itemClick(position);
            }
        });
    }
}
