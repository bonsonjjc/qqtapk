package com.bonson.qqtapk.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.ViewGroup;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ItemFlowerBinding;
import com.bonson.qqtapk.databinding.ItemMemberBinding;
import com.bonson.qqtapk.model.bean.Member;
import com.bonson.resource.fragment.BaseAdapter;
import com.bonson.resource.fragment.ViewHolder;

import java.util.List;

/**
 * Created by zjw on 2018/1/4.
 */

public class MemberAdapter extends BaseAdapter<Member, ItemMemberBinding> {
    public MemberAdapter(Context context, List<Member> contacts) {
        super(context, contacts);
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
    }
}
