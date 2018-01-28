package com.bonson.qqtapk.view.widget;

import android.content.Context;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bonson.qqtapk.R;

/**
 * Created by jiangjiancheng on 18/1/28.
 */
public class TableView extends RadioGroup {
    private RecyclerView.AdapterDataObserver adapterDataObserver = new RecyclerView.AdapterDataObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
            updateTitles();
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            super.onItemRangeChanged(positionStart, itemCount);
            onChanged();
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
            super.onItemRangeChanged(positionStart, itemCount, payload);
            onChanged();
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            super.onItemRangeInserted(positionStart, itemCount);
            onChanged();
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            super.onItemRangeRemoved(positionStart, itemCount);
            onChanged();
        }
    };
    PagerTitleHelper pagerTitleHelper;
    private RecyclerView.Adapter mAdapter;

    RecyclerView recyclerView;

    public TableView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnCheckedChangeListener((group, checkedId) -> recyclerView.smoothScrollToPosition(checkedId));
        pagerTitleHelper = new PagerTitleHelper();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    public void setAdapter(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        pagerTitleHelper.attachToRecyclerView(recyclerView);
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (this.mAdapter == adapter) {
            return;
        }
        if (mAdapter != null) {
            mAdapter.unregisterAdapterDataObserver(adapterDataObserver);
        }
        mAdapter = adapter;
        mAdapter.registerAdapterDataObserver(adapterDataObserver);
        updateTitles();
    }

    @Override
    public void check(int id) {
        super.check(id);
        if (id != -1) {
            onPagerListener.onPager(id);
        }
    }

    private void updateTitles() {
        int itemCount = mAdapter.getItemCount();
        int childCount = getChildCount();
        removeAllViews();
        if (mAdapter instanceof TitleAdapter) {
            for (int i = 0; i < itemCount; i++) {
                RadioButton radioButton = new RadioButton(getContext());
                radioButton.setText(((TitleAdapter) mAdapter).getTitle(i));
                radioButton.setBackgroundResource(R.drawable.new_tab);
                radioButton.setGravity(Gravity.CENTER);
                radioButton.setId(i);
                radioButton.setButtonDrawable(null);
                LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1);
                addView(radioButton, layoutParams);
            }
        }
        if (mAdapter.getItemCount() == 0) return;
        int checkedRadioButtonId = getCheckedRadioButtonId();
        if (checkedRadioButtonId == -1) {
            check(itemCount - 1);
        }
    }

    @Override
    protected void removeDetachedView(View child, boolean animate) {
        super.removeDetachedView(child, animate);
        if (mAdapter != null) {
            mAdapter.unregisterAdapterDataObserver(adapterDataObserver);
        }
        recyclerView = null;
        mAdapter = null;
    }

    public interface TitleAdapter {
        String getTitle(int position);
    }

    private OnPagerListener onPagerListener;

    public void setOnPagerListener(OnPagerListener onPagerListener) {
        this.onPagerListener = onPagerListener;
    }

    public interface OnPagerListener {
        void onPager(int position);
    }

    class PagerTitleHelper extends PagerSnapHelper {
        @Override
        public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int velocityX, int velocityY) {
            int position = super.findTargetSnapPosition(layoutManager, velocityX, velocityY);
            Log.e("position", position + "");
            if (position == -1) return position;
            int itemCount = recyclerView.getAdapter().getItemCount();
            check(Math.min(itemCount - 1, position));
            return position;
        }
    }
}
