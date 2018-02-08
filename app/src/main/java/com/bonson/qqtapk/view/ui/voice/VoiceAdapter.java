package com.bonson.qqtapk.view.ui.voice;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.ViewGroup;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ItemVoiceLeftBinding;
import com.bonson.qqtapk.databinding.ItemVoiceRightBinding;
import com.bonson.qqtapk.model.bean.Voice;
import com.bonson.resource.adapter.BaseAdapter;
import com.bonson.resource.adapter.ViewHolder;

import java.util.List;

import javax.inject.Inject;

public class VoiceAdapter extends BaseAdapter<Voice, ViewDataBinding> {
    @Inject
    public VoiceAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder<ViewDataBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(inflater, viewType == 1 ? R.layout.item_voice_left : R.layout.item_voice_right, parent, false);
        ViewHolder<ViewDataBinding> viewHolder = new ViewHolder<>(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder<ViewDataBinding> holder, int position) {
        Voice voice = beans.get(position);
        if (holder.getItemViewType() == 1) {
            ItemVoiceLeftBinding binding = (ItemVoiceLeftBinding) holder.getBinding();
            binding.setVoice(voice);
            binding.executePendingBindings();
        } else {
            ItemVoiceRightBinding binding = (ItemVoiceRightBinding) holder.getBinding();
            binding.setVoice(voice);
            binding.executePendingBindings();
        }
    }

    @Override
    public int getItemViewType(int position) {
        Voice voice = beans.get(position);
        return voice.getFtype().equals("1") ? 2 : 1;
    }
}
