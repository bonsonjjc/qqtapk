package com.bonson.qqtapk.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.ViewGroup;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ItemFamilyBinding;
import com.bonson.qqtapk.model.bean.Family;
import com.bonson.resource.fragment.BaseAdapter;
import com.bonson.resource.fragment.ViewHolder;

import java.util.List;

/**
 * Created by jiangjiancheng on 18/1/7.
 */

public class FamilyAdapter extends BaseAdapter<Family, ItemFamilyBinding> {
    public FamilyAdapter(Context context, List<Family> contacts) {
        super(context, contacts);
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
    }
}