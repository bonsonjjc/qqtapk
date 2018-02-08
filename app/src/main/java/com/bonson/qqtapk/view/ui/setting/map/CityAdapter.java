package com.bonson.qqtapk.view.ui.setting.map;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bonson.library.utils.LogUtils;
import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ItemChildBinding;
import com.bonson.resource.adapter.ViewHolder;
import com.bonson.resource.adapter.group.GroupAdapter;
import com.bonson.resource.adapter.group.IndexPath;

import java.util.List;

import javax.inject.Inject;

public class CityAdapter extends GroupAdapter<ViewHolder<ItemChildBinding>> {
    private List<OfflineMap> searchRecords;
    private LayoutInflater layoutInflater;

    @Inject
    public CityAdapter(Context context) {
        super();
        layoutInflater = LayoutInflater.from(context);
    }

    public void setBeans(List<OfflineMap> searchRecords) {
        this.searchRecords = searchRecords;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder<ItemChildBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemChildBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_child, parent, false);
        return new ViewHolder<>(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder<ItemChildBinding> holder, IndexPath indexPath) {
        ItemChildBinding binding = holder.getBinding();
        OfflineMap record = searchRecords.get(indexPath.row);
        if (indexPath.section != -1) {
            record = record.childMap.get(indexPath.section);
        }
        if (1 != record.cityType) {
            binding.imgArrow.setImageResource(R.drawable.ico_xz);
            binding.getRoot().setOnClickListener(v -> {
                if (clickListener != null) {
                    clickListener.onItemClick(indexPath);
                }
            });
        } else {
            binding.getRoot().setOnClickListener(v -> toggle(indexPath));
            binding.imgArrow.setImageResource(isExpanded(indexPath.row) ? R.drawable.ico_arrow_close : R.drawable.ico_arrow_open);
        }
        binding.setData(record);
    }


    @Override
    public int getItemViewType(IndexPath indexPath) {
        return indexPath.section == -1 ? 0 : 1;
    }

    @Override
    public int getGroupCount() {
        if (searchRecords == null) return 0;
        return searchRecords.size();
    }

    @Override
    public int getChildCount(int group) {
        OfflineMap record = searchRecords.get(group);
        if (record.childMap == null)
            return 0;
        return record.childMap.size();
    }
}
