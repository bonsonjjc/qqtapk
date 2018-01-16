package com.bonson.qqtapk.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.View;
import android.view.ViewGroup;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ItemMemberBinding;
import com.bonson.qqtapk.model.bean.Member;
import com.bonson.resource.adapter.BaseAdapter;
import com.bonson.resource.adapter.ViewHolder;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by zjw on 2018/1/4.
 */

public class MemberAdapter extends BaseAdapter<Member, ItemMemberBinding> {
    @Inject
   public MemberAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder<ItemMemberBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemMemberBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_member, parent, false);
        return new ViewHolder<>(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder<ItemMemberBinding> holder, int position) {
        Member member = beans.get(position);
        holder.getBinding().setMember(member);
        holder.getBinding().executePendingBindings();
        holder.itemView.setOnClickListener((View v) -> {
            if (null != onItemClickListener) {
                onItemClickListener.itemClick(position);
            }
        });
        holder.itemView.setOnLongClickListener(v -> null != onItemLongClickListener && onItemLongClickListener.itemLongClick(position));
    }
}
