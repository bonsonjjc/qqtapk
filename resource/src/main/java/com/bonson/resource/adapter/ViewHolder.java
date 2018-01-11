package com.bonson.resource.adapter;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 * Created by zjw on 2018/1/3.
 */

public class ViewHolder<Binding extends ViewDataBinding> extends RecyclerView.ViewHolder {
    Binding binding;

    public ViewHolder(Binding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public Binding getBinding() {
        return binding;
    }

    public void setBinding(Binding binding) {
        this.binding = binding;
    }
}
