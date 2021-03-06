package com.bonson.qqtapk.view.ui.index;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.view.View;
import android.view.ViewGroup;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ItemBabyBinding;
import com.bonson.qqtapk.model.bean.Baby;
import com.bonson.resource.adapter.BaseAdapter;
import com.bonson.resource.adapter.ViewHolder;

import java.util.List;

import javax.inject.Inject;

public class BabyAdapter extends BaseAdapter<Baby, ItemBabyBinding> {
    public final ObservableField<String> bid = new ObservableField<>();

    @Inject
    public BabyAdapter(Context context) {
        super(context);
        bid.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                notifyDataSetChanged();
            }
        });
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
        binding.setIsCurrent(baby.getFid().equals(bid.get()));
        binding.executePendingBindings();
        binding.getRoot().setOnClickListener((View v) -> {
            if (onItemClickListener != null) {
                onItemClickListener.itemClick(position);
            }
        });
    }
}
