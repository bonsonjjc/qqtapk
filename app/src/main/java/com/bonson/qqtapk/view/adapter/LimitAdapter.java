package com.bonson.qqtapk.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.ViewGroup;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ItemLimitBinding;
import com.bonson.qqtapk.model.bean.Limit;
import com.bonson.resource.fragment.BaseAdapter;
import com.bonson.resource.fragment.ViewHolder;

import java.util.List;

/**
 * Created by zjw on 2018/1/4.
 */

public class LimitAdapter extends BaseAdapter<Limit, ItemLimitBinding> {

    public LimitAdapter(Context context, List<Limit> contacts) {
        super(context, contacts);
    }

    @Override
    public ViewHolder<ItemLimitBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemLimitBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_limit, parent, false);
        return new ViewHolder<>(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder<ItemLimitBinding> holder, int position) {
        Limit limit = beans.get(position);
        holder.getBinding().setLimit(limit);
        holder.getBinding().executePendingBindings();
    }
}
