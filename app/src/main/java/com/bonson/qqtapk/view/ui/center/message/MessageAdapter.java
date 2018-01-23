package com.bonson.qqtapk.view.ui.center.message;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.ViewGroup;

import com.bonson.library.utils.NumberUtils;
import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ItemMenuBinding;
import com.bonson.qqtapk.databinding.ItemMessageBinding;
import com.bonson.qqtapk.databinding.ItemMessageFriendBinding;
import com.bonson.qqtapk.model.bean.Menu;
import com.bonson.qqtapk.model.bean.Message;
import com.bonson.resource.adapter.BaseAdapter;
import com.bonson.resource.adapter.ViewHolder;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by zjw on 2018/1/3.
 */

public class MessageAdapter extends BaseAdapter<Message, ViewDataBinding> {

    @Inject
    public MessageAdapter(Context context) {
        super(context);
    }

    private String type;

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public ViewHolder<ViewDataBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding;
        switch (viewType) {
            case 63:
                binding = DataBindingUtil.inflate(inflater, R.layout.item_message_friend, parent, false);
                break;
            default:
                binding = DataBindingUtil.inflate(inflater, R.layout.item_message, parent, false);
        }
        return new ViewHolder<>(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder<ViewDataBinding> holder, int position) {
        Message message = beans.get(position);
        if (holder.getBinding() instanceof ItemMessageBinding) {
            ((ItemMessageBinding) holder.getBinding()).setData(message);
        } else {
            ItemMessageFriendBinding binding = (ItemMessageFriendBinding)holder.getBinding();
            binding.setData(message);
            if (onFriendListener != null) {
                binding.tvAgree.setOnClickListener(v -> {
                    if (onFriendListener != null) {
                        onFriendListener.onFriend(position, "0");
                    }
                });
                binding.tvRefuse.setOnClickListener(v -> {
                    if (onFriendListener != null) {
                        onFriendListener.onFriend(position, "1");
                    }
                });
            }
        }
        holder.getBinding().executePendingBindings();
        holder.itemView.setOnClickListener(v -> {
            if (holder.getItemViewType() == 25) {
                if (null != onItemClickListener) {
                    onItemClickListener.itemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return NumberUtils.parseInt(type);
    }

    private OnFriendListener onFriendListener;

    public void setOnFriendListener(OnFriendListener onFriendListener) {
        this.onFriendListener = onFriendListener;
    }

    public interface OnFriendListener {
        void onFriend(int position, String type);
    }
}
