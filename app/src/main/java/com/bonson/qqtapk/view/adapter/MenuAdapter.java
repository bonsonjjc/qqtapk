package com.bonson.qqtapk.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.View;
import android.view.ViewGroup;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ItemMenuBinding;
import com.bonson.qqtapk.model.bean.Menu;
import com.bonson.resource.adapter.BaseAdapter;
import com.bonson.resource.fragment.ViewHolder;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by zjw on 2018/1/3.
 */

public class MenuAdapter extends BaseAdapter<Menu, ItemMenuBinding> {

    @Inject
    public MenuAdapter(Context context, List<Menu> contacts) {
        super(context, contacts);
    }

    @Override
    public ViewHolder<ItemMenuBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemMenuBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_menu, parent, false);
        return new ViewHolder<>(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder<ItemMenuBinding> holder, int position) {
        holder.getBinding().setMenu(beans.get(position));
        holder.getBinding().executePendingBindings();
//        holder.itemView.setOnClickListener((View v) -> {
//            if (null != onItemClickListener) {
//                onItemClickListener.itemClick(position);
//            }
//        });
    }
}
