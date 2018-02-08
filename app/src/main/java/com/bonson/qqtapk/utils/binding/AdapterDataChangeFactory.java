package com.bonson.qqtapk.utils.binding;

import android.databinding.ObservableList;
import android.support.v7.widget.RecyclerView;

public final class AdapterDataChangeFactory {
    private ObservableList.OnListChangedCallback changedCallback = new ObservableList.OnListChangedCallback() {
        @Override
        public void onChanged(ObservableList sender) {
            adapter.notifyDataSetChanged();
        }

        @Override
        public void onItemRangeChanged(ObservableList sender, int positionStart, int itemCount) {
            adapter.notifyItemChanged(positionStart, itemCount);
        }

        @Override
        public void onItemRangeInserted(ObservableList sender, int positionStart, int itemCount) {
            adapter.notifyItemRangeInserted(positionStart, itemCount);
        }

        @Override
        public void onItemRangeMoved(ObservableList sender, int fromPosition, int toPosition, int itemCount) {
            adapter.notifyItemMoved(fromPosition, toPosition);
        }

        @Override
        public void onItemRangeRemoved(ObservableList sender, int positionStart, int itemCount) {
            adapter.notifyItemRangeRemoved(positionStart, itemCount);
        }
    };

    private RecyclerView.Adapter adapter;

    private AdapterDataChangeFactory(RecyclerView.Adapter adapter) {
        this.adapter = adapter;
    }

    public void attach(ObservableList<?> list) {
        if (list != null)
            list.addOnListChangedCallback(changedCallback);
    }

    public static AdapterDataChangeFactory create(RecyclerView.Adapter adapter) {
        return new AdapterDataChangeFactory(adapter);
    }
}
