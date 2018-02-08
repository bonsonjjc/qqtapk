package com.bonson.resource.adapter.group;

import android.support.v7.widget.RecyclerView;

import com.bonson.library.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class GroupAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    private List<GroupData> mGroupData = new ArrayList<>();
    private AdapterChangeHelper adapterChangeHelper;
    private GroupHelper groupHelper;

    protected GroupAdapter() {
        groupHelper = new GroupHelper();
        adapterChangeHelper = new AdapterChangeHelper();
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        onBindViewHolder(holder, groupHelper.find(position));
    }

    public abstract void onBindViewHolder(VH holder, IndexPath indexPath);

    @Override
    public void registerAdapterDataObserver(RecyclerView.AdapterDataObserver observer) {
        super.registerAdapterDataObserver(adapterChangeHelper);
        super.registerAdapterDataObserver(observer);
    }

    @Override
    public void unregisterAdapterDataObserver(RecyclerView.AdapterDataObserver observer) {
        super.unregisterAdapterDataObserver(adapterChangeHelper);
        super.unregisterAdapterDataObserver(observer);
    }

    public abstract int getGroupCount();

    public abstract int getChildCount(int group);

    public boolean isExpanded(int group) {
        return mGroupData.get(group).expanded;
    }

    @Override
    public int getItemViewType(int position) {
        return getItemViewType(groupHelper.find(position));
    }

    public abstract int getItemViewType(IndexPath indexPath);

    @Override
    public final int getItemCount() {
        return groupHelper.total();
    }

    class GroupData {
        int row = 0;
        int start = 0;
        int end = start;
        int count = end - start;
        boolean expanded = false;
    }

    public interface OnItemClickListener {
        void onItemClick(IndexPath indexPath);
    }

    protected OnItemClickListener clickListener;

    public void setClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public class AdapterChangeHelper extends RecyclerView.AdapterDataObserver {
        @Override
        public void onChanged() {
            super.onChanged();
            groupHelper.fillGroupData();
            groupHelper.calculation(0);
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            super.onItemRangeChanged(positionStart, itemCount);
            groupHelper.calculation(0);
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
            super.onItemRangeChanged(positionStart, itemCount, payload);
            groupHelper.calculation(0);
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            super.onItemRangeInserted(positionStart, itemCount);
            groupHelper.fillGroupData();
            groupHelper.calculation(0);
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            super.onItemRangeRemoved(positionStart, itemCount);
            groupHelper.fillGroupData();
            groupHelper.calculation(0);
        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            super.onItemRangeMoved(fromPosition, toPosition, itemCount);
            groupHelper.fillGroupData();
            groupHelper.calculation(0);
        }
    }

    public void expanded(IndexPath path) {
        groupHelper.expanded(path);
    }

    public void collapsed(IndexPath path) {
        groupHelper.collapsed(path);
    }

    public void toggle(IndexPath path) {
        groupHelper.toggle(path);
    }

    class GroupHelper {

        public void toggle(IndexPath group) {
            GroupData groupData = indexPath(group.row);
            if (groupData.expanded) {
                collapsed(group);
            } else {
                expanded(group);
            }
        }

        public void expanded(IndexPath group) {
            GroupData groupData = indexPath(group.row);
            if (groupData.expanded) {
                return;
            }
            groupData.expanded = true;
            int start = groupData.start;
            int count = groupData.count;
            notifyItemChanged(start);
            notifyItemRangeInserted(start + 1, count - 1);
        }

        public void collapsed(IndexPath group) {
            GroupData groupData = indexPath(group.row);
            if (!groupData.expanded) {
                return;
            }
            groupData.expanded = false;
            int start = groupData.start;
            int count = groupData.count;
            notifyItemChanged(start);
            notifyItemRangeRemoved(start + 1, count - 1);
        }

        public void fillGroupData() {
            int diffSize = getGroupCount() - mGroupData.size();
            while (getGroupCount() != mGroupData.size()) {
                if (diffSize > 0) {
                    mGroupData.add(new GroupData());
                } else {
                    mGroupData.remove(getGroupCount());
                }
            }
        }

        public void calculation(int start) {
            int position = 0;
            GroupData groupData;
            for (int i = start; i < mGroupData.size(); i++) {
                groupData = mGroupData.get(i);
                groupData.row = i;
                groupData.start = position;
                groupData.end = groupData.start + childCount(i);
//                LogUtils.e("group start:" + groupData.start + " ; end:" + groupData.end);
                position = groupData.end + 1;
            }
            System.out.println();
        }

        public int childCount(int group) {
            if (getChildCount(group) == 0) {
                return 0;
            }
            if (isExpanded(group)) {
                return getChildCount(group) - 1;
            }
            return 0;
        }

        public GroupData indexPath(int group) {
            GroupData groupData = mGroupData.get(group);
            groupData.row = group;
            groupData.count = getChildCount(group);
            return groupData;
        }


        public int total() {
            if (mGroupData.isEmpty()) {
                return 0;
            }
            int total = 0;
            GroupData groupData = mGroupData.get(mGroupData.size() - 1);
            total += groupData.end + 1;
//            LogUtils.e("total:" + total);
            return total;
        }

        public IndexPath find(int position) {
            IndexPath indexPath = new IndexPath();
            GroupData groupData;
            for (int i = 0; i < mGroupData.size(); i++) {
                groupData = mGroupData.get(i);
                int start = groupData.start;
                int end = groupData.end;
                if (start <= position && end >= position) {
                    indexPath.row = i;
                    if (start == end) {
                        indexPath.section = -1;
                    } else {
                        indexPath.section = position - start - 1;
                    }
                    indexPath.index = position;
                    break;
                }
            }
            return indexPath;
        }
    }

}
