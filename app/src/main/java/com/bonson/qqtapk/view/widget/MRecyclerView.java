package com.bonson.qqtapk.view.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.bonson.resource.adapter.OnItemClickListener;

import java.util.List;


public class MRecyclerView extends RecyclerView {
    public MRecyclerView(Context context) {
        super(context);
    }

    public MRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    List data;

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {

    }
}
