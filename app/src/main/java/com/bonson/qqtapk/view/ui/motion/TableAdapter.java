package com.bonson.qqtapk.view.ui.motion;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.ViewGroup;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ItemTableBinding;
import com.bonson.qqtapk.view.ui.motion.bean.Table;
import com.bonson.qqtapk.view.widget.TableView;
import com.bonson.resource.adapter.BaseAdapter;
import com.bonson.resource.adapter.ViewHolder;

import javax.inject.Inject;


public class TableAdapter extends BaseAdapter<Table, ItemTableBinding> implements TableView.TitleAdapter {
    @Inject
    public TableAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder<ItemTableBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemTableBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_table, parent, false);
        return new ViewHolder<>(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder<ItemTableBinding> holder, int position) {
        ItemTableBinding binding = holder.getBinding();
        Table table = beans.get(position);
        binding.setTable(table);
        binding.executePendingBindings();
    }

    @Override
    public String getTitle(int position) {
        return "02/12";
    }
}
