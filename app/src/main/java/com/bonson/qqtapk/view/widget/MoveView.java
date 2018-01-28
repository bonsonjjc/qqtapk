package com.bonson.qqtapk.view.widget;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.bonson.library.utils.NumberUtils;
import com.bonson.qqtapk.R;

import java.text.DecimalFormat;

public class MoveView extends ViewGroup {
    private Paint bgPaint, fePaint;
    private final int startAngle = 120, endAngle = 300;
    float borderWidth;
    float progress = 0;
    private PaintFlagsDrawFilter pfd;


    public TextView tvTitle, tvNumber, tvDesc, tvPercentDesc, tvPercent;

    public MoveView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MoveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);

    }

    public MoveView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        borderWidth = px(8f);
        pfd = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
        bgPaint = new Paint();
        fePaint = new Paint();
        bgPaint.setAntiAlias(true);
        bgPaint.setStyle(Paint.Style.STROKE);
        bgPaint.setStrokeWidth(borderWidth);
        int arcColor = 0x48000000;
        bgPaint.setColor(arcColor);
        fePaint.setStyle(Paint.Style.STROKE);
        fePaint.setAntiAlias(true);
        fePaint.setStrokeWidth(borderWidth);
        int feColor = 0xFFFF6600;
        fePaint.setColor(feColor);

        tvTitle = new TextView(context, attrs);
        tvTitle.setTextSize(14f);
        tvNumber = new TextView(context, attrs);
        tvNumber.setTextSize(30f);
        tvDesc = new TextView(context, attrs);
        tvDesc.setTextSize(14f);
        tvPercentDesc = new TextView(context, attrs);
        tvPercentDesc.setTextSize(14f);
        tvPercent = new TextView(context, attrs);
        tvPercent.setTextSize(14f);
        tvPercent.setBackgroundResource(R.drawable.btn_sel);
        tvPercent.setPadding(px(12), px(6), px(12), px(6));
        addView(tvTitle);
        addView(tvNumber);
        addView(tvDesc);
        addView(tvPercentDesc);
        addView(tvPercent);
        setTextColor(Color.WHITE);
    }

    public void setTextColor(int color) {
        View child;
        for (int i = 0; i < getChildCount(); i++) {
            child = getChildAt(i);
            if (child instanceof TextView) {
                ((TextView) child).setTextColor(color);
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int width = r - l, height = b - t;
        int centerX = width / 2, centerY = height / 2;
        int vHeight = 0;

        //
        Rect tv4Rect = centerHor(tvPercent, centerX);
        vHeight = tv4Rect.height();
        measureRect(width, height, tv4Rect.centerY());
        tv4Rect.top = height - vHeight;
        tv4Rect.bottom = height;
        tvPercent.layout(tv4Rect.left, tv4Rect.top, tv4Rect.right, tv4Rect.bottom);

        Rect tv3Rect = centerHor(tvPercentDesc, centerX);
        vHeight = tv3Rect.height();
        tv3Rect.top = tv4Rect.top - vHeight - px(2);
        tv3Rect.bottom = tv4Rect.top - px(2);
        tvPercentDesc.layout(tv3Rect.left, tv3Rect.top, tv3Rect.right, tv3Rect.bottom);

        Rect tv1Rect = centerHor(tvNumber, centerX);
        vHeight = tv1Rect.height();
        tv1Rect.bottom = (int) contentRect.centerY() + tv1Rect.centerY();
        tv1Rect.top = tv1Rect.bottom - vHeight;
        tvNumber.layout(tv1Rect.left, tv1Rect.top, tv1Rect.right, tv1Rect.bottom);

        Rect tvRect = centerHor(tvTitle, centerX);
        vHeight = tvRect.height();
        tvRect.bottom = tv1Rect.top - vHeight / 3;
        tvRect.top = tvRect.bottom - vHeight;
        tvTitle.layout(tvRect.left, tvRect.top, tvRect.right, tvRect.bottom);

        Rect tv2Rect = centerHor(tvDesc, centerX);
        vHeight = tv2Rect.height();
        tv2Rect.top = tv1Rect.bottom + vHeight / 3;
        tv2Rect.bottom = tv2Rect.top + vHeight;
        tvDesc.layout(tv2Rect.left, tv2Rect.top, tv2Rect.right, tv2Rect.bottom);
    }

    private Rect centerHor(TextView text, int centerX) {
        Rect rect = new Rect();
        int width = text.getMeasuredWidth();
        rect.left = centerX - width / 2;
        rect.right = rect.left + width;
        rect.top = 0;
        rect.bottom = text.getMeasuredHeight();
        return rect;
    }

    private void measureRect(int width, int height, int paddingBottom) {
        int centerX = width / 2, centerY = height / 2;
        float radius = Math.min(centerX, centerY);
        float ovrRadius = radius - borderWidth;
        float left, top, right, bottom;
        left = centerX - ovrRadius;
        right = centerX + ovrRadius;
        contentRect.left = left;
        contentRect.top = borderWidth / 2;
        contentRect.right = right;
        contentRect.bottom = radius * 2 - paddingBottom;
    }

    RectF contentRect = new RectF();

    public void draw(Canvas canvas) {
        // 画半圆
        canvas.setDrawFilter(pfd);
        canvas.drawArc(contentRect, startAngle, endAngle, false, bgPaint);
        canvas.drawArc(contentRect, startAngle, progress * p(), false, fePaint);
        super.draw(canvas);
    }

    public int px(float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }

    private float p() {
        return endAngle / 100;
    }

    private Animator numberAnimator, percentAnimator;
    DecimalFormat format = new DecimalFormat("0.00");

    public void moving(String number, String desc, float percent) {
        tvTitle.setText("当前总步数");
        tvDesc.setText(desc);
        tvPercentDesc.setText("完成度");
        tvPercent.setText(percent + "%");
        if (numberAnimator != null && numberAnimator.isRunning()) {
            numberAnimator.cancel();
        }
        if (percentAnimator != null && percentAnimator.isRunning()) {
            percentAnimator.cancel();
        }
        if (TextUtils.isEmpty(number)) {
            tvNumber.setText("--");
        } else {
            numberAnimator = numberAnimator(0, NumberUtils.parseInt(number, 0), "stepNumber");
        }
        if (percent < 0.009) {
            tvPercent.setText("0.0%");
        } else {
            percentAnimator = numberAnimator(0.0f, percent, "percent");
        }
    }

    public void sleeping(String sleep, String desc, float percent) {
        tvTitle.setText("昨晚共睡眠");
        tvDesc.setText(desc);
        tvPercentDesc.setText("完成度");
        tvPercent.setText(percent + "%");

        if (numberAnimator != null && numberAnimator.isRunning()) {
            numberAnimator.cancel();
        }
        if (percentAnimator != null && percentAnimator.isRunning()) {
            percentAnimator.cancel();
        }
        if (TextUtils.isEmpty(sleep)) {
            tvNumber.setText("--");
        } else {
            numberAnimator = numberAnimator(0, NumberUtils.parseInt(sleep, 0), "stepNumber");
        }
        if (percent < 0.009) {
            tvPercent.setText("0.0%");
        } else {
            percentAnimator = numberAnimator(0.0f, percent, "percent");
        }
    }

    public void hearting(String heart, String desc) {
        tvDesc.setText(desc);
        tvPercentDesc.setText("");
        tvPercent.setText("重新测量");
    }

    public void setStepNumber(int number) {
        tvNumber.setText(String.valueOf(number));
    }

    public void setPercent(float percent) {
        if (percent == 100) {
            tvPercent.setText(" 100% ");
        } else {
            tvPercent.setText(format.format(percent) + "%");
        }
        progress = percent;
    }

    public Animator numberAnimator(int start, int end, String propertyName) {
        Animator animator = ObjectAnimator.ofInt(this, propertyName, start, end);
        animator.setDuration(500);
        animator.start();
        return animator;
    }

    public Animator numberAnimator(float start, float end, String propertyName) {
        Animator animator = ObjectAnimator.ofFloat(this, propertyName, start, end);
        animator.setDuration(500);
        animator.start();
        return animator;
    }


    public void setProgress(float progress) {
        this.progress = progress;
        invalidate();
    }
}
