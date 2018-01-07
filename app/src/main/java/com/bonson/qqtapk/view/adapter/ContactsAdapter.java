package com.bonson.qqtapk.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.ViewGroup;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ItemContacsBinding;
import com.bonson.qqtapk.model.bean.Contacts;
import com.bonson.resource.fragment.BaseAdapter;
import com.bonson.resource.fragment.ViewHolder;

import java.util.List;

/**
 * Created by zjw on 2018/1/4.
 */

public class ContactsAdapter extends BaseAdapter<Contacts, ItemContacsBinding> {

    public ContactsAdapter(Context context, List<Contacts> contacts) {
        super(context, contacts);
    }

    @Override
    public ViewHolder<ItemContacsBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemContacsBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_contacs, parent, false);
        return new ViewHolder<>(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder<ItemContacsBinding> holder, int position) {
        Contacts lesson = beans.get(position);
        holder.getBinding().setContacts(lesson);
        holder.getBinding().executePendingBindings();
    }
}
