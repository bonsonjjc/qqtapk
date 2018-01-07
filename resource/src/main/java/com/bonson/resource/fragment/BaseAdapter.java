package com.bonson.resource.fragment;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import java.util.List;

/**
 * Created by jiangjiancheng on 18/1/7.
 */

public abstract class BaseAdapter<Bean, Binding extends ViewDataBinding> extends RecyclerView.Adapter<ViewHolder<Binding>> {
    protected List<Bean> beans;
    protected LayoutInflater inflater;

    public BaseAdapter(Context context, List<Bean> contacts) {
        this.beans = contacts;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        if (beans == null) return 0;
        return beans.size();
    }
}
