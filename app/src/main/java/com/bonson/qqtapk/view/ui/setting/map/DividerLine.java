package com.bonson.qqtapk.view.ui.setting.map;

import android.content.Context;
import android.databinding.ObservableInt;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bonson.library.utils.DensityUtils;

public class DividerLine extends RecyclerView.ItemDecoration {
    private Paint paint;
    private ObservableInt hostCount;
    private Rect titleRect, tempRect;
    private int leftPadding, titleHeight;

    public DividerLine(Context context, ObservableInt hostCount) {
        paint = new Paint(Color.BLACK);
        paint.setAntiAlias(true);
        paint.setTextSize(DensityUtils.sp(context, 14));
        this.hostCount = hostCount;
        titleRect = new Rect();
        tempRect = new Rect();
        leftPadding = DensityUtils.dp(context, 8);
        titleHeight = DensityUtils.dp(context, 30);
        tempRect.set(0, 0, leftPadding, titleHeight);
        titleRect.set(0, 0, leftPadding, titleHeight);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        tempRect.right = c.getWidth();
        int count = parent.getChildCount();
        View child;
        for (int i = 0; i < count; i++) {
            child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            int viewAdapterPosition = layoutParams.getViewAdapterPosition();
            if (viewAdapterPosition == 0) continue;
            if (isDrawTitle(viewAdapterPosition)) {
                tempRect.offsetTo(0, child.getTop() - tempRect.height());
                String title = title(viewAdapterPosition);
                drawTitle(title, tempRect, c);
            }
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        if (parent.getChildCount() == 0) return;

        titleRect.right = c.getWidth();
        View firstView = parent.getChildAt(0);
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) firstView.getLayoutParams();
        int firstGroup = layoutParams.getViewAdapterPosition();
        boolean flag = false;

        if (isDiff(firstGroup)) {
            if (firstView.getBottom() <= titleHeight) {
                c.save();
                c.translate(0, firstView.getBottom() - titleHeight);
                flag = true;
            }
        }
        drawTitle(title(firstGroup), titleRect, c);
        if (flag) {
            c.restore();
        }
    }

    private final String[] titles = {"当前城市", "热门城市", "所有城市"};

    private String title(int position) {
        int group = group(position);
        return titles[group];
    }

    private int group(int position) {
        if (position < 1) {
            return 0;
        }
        if (position < hostCount.get()) {
            return 1;
        }
        return 2;
    }

    private boolean isDiff(int position) {
        return group(position) != group(position + 1);
    }

    private boolean isDrawTitle(int position) {
        return position == 0 || position == hostCount.get() || position == 1;
    }

    private void drawTitle(String text, Rect rect, Canvas canvas) {
        Rect textRect = new Rect();
        paint.getTextBounds(text, 0, text.length(), textRect);
        paint.setColor(0xFFE7E7E7);
        canvas.drawRect(rect, paint);
        paint.setColor(Color.BLACK);
        int bottom = (rect.height() - textRect.height()) / 2;
        canvas.drawText(text, leftPadding, rect.bottom - bottom, paint);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        RecyclerView.ViewHolder holder = parent.findContainingViewHolder(view);
        if (isDrawTitle(holder.getAdapterPosition())) {
            outRect.top = titleHeight;
        }
        outRect.bottom = 1;
    }
}
