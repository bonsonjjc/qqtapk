package com.bonson.resource.adapter;

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

    public BaseAdapter(Context context) {
        this.beans = beans;
        inflater = LayoutInflater.from(context);
    }

    public void setBeans(List<Bean> beans) {
        this.beans = beans;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (beans == null) return 0;
        return beans.size();
    }

    protected OnItemClickListener onItemClickListener;
    protected OnItemLongClickListener onItemLongClickListener;

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public OnItemLongClickListener getOnItemLongClickListener() {
        return onItemLongClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }
}
