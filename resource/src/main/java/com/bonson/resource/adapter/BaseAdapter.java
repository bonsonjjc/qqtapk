package com.bonson.resource.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

/**
 * Created by zjw on 2017/11/15.
 */

public abstract class BaseAdapter<VH extends RecyclerView.ViewHolder>
    extends RecyclerView.Adapter<VH> {
  private LayoutInflater inflater;

  protected BaseAdapter(LayoutInflater inflater) {
    this.inflater = inflater;
  }

  private OnItemClickListener mOnItemClickListener;
  private OnItemLongClickListener mOnItemLongClickListener;

  public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
    this.mOnItemClickListener = mOnItemClickListener;
  }

  public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
    this.mOnItemLongClickListener = onItemLongClickListener;
  }

  public interface OnItemClickListener {
    void onItemClick(int position, RecyclerView.ViewHolder holder);
  }

  public interface OnItemLongClickListener {
    boolean onItemLongClick(int position, RecyclerView.ViewHolder holder);
  }
}
