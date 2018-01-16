package com.bonson.qqtapk.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.View;
import android.view.ViewGroup;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ItemContactBinding;
import com.bonson.qqtapk.model.bean.Contact;
import com.bonson.resource.adapter.BaseAdapter;
import com.bonson.resource.adapter.ViewHolder;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by zjw on 2018/1/4.
 */

public class ContactAdapter extends BaseAdapter<Contact, ItemContactBinding> {

    @Inject
    public ContactAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder<ItemContactBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemContactBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_contact, parent, false);
        return new ViewHolder<>(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder<ItemContactBinding> holder, int position) {
        Contact contact = beans.get(position);
        holder.getBinding().setContact(contact);
        holder.getBinding().executePendingBindings();
        holder.itemView.setOnClickListener((View v) -> {
            if (null != onItemClickListener) {
                onItemClickListener.itemClick(position);
            }
        });
        holder.itemView.setOnLongClickListener(v -> null != onItemLongClickListener && onItemLongClickListener.itemLongClick(position));
    }
}
