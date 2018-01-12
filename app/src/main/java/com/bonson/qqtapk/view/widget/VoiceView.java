package com.bonson.qqtapk.view.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bonson.library.utils.DensityUtils;

public class VoiceView extends TextView {
    private VoiceView.OnListener onListener;
    private float downY;
    private int model = sendModel;
    private boolean enable;
    public static final int sendModel = 1;
    public static final int cancelModel = 2;

    public final VoiceView.OnListener getOnListener() {
        return this.onListener;
    }

    public final void setOnListener(VoiceView.OnListener var1) {
        this.onListener = var1;
    }

    public final float getDownY() {
        return this.downY;
    }

    public final void setDownY(float var1) {
        this.downY = var1;
    }

    public final int getModel() {
        return this.model;
    }

    public final void setModel(int var1) {
        this.model = var1;
    }

    public final boolean getEnable() {
        return this.enable;
    }

    public final void setEnable(boolean var1) {
        this.enable = var1;
    }

    public VoiceView(@NonNull Context context) {
        super(context);
    }

    public VoiceView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public VoiceView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public VoiceView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public final void send() {
        this.enable = false;
        if (this.onListener != null) {
            onListener.onUp();
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (this.onListener != null) {
                    onListener.onDown();
                }

                this.model = sendModel;
                this.downY = event.getY();
                this.enable = true;
                break;
            case MotionEvent.ACTION_MOVE:
                if (!this.enable) {
                    return false;
                }
                float curY = event.getY();
                if (this.downY - curY > (float) DensityUtils.dp(getResources(), 5.0F)) {
                    this.model = cancelModel;
                    if (this.onListener != null) {
                        onListener.onChange();
                    }
                } else if (this.downY - curY < (float) (-DensityUtils.dp(getResources(), 5.0F))) {
                    this.model = sendModel;
                    if (this.onListener != null) {
                        onListener.onChange();
                    }
                }

                this.downY = curY;
                break;
            case MotionEvent.ACTION_UP:
                if (!this.enable) {
                    return false;
                }
                enable = false;
                if (this.onListener != null) {
                    if (model == cancelModel) {
                        onListener.onCancel();
                    } else {
                        onListener.onUp();
                    }
                }
                this.model = cancelModel;
                break;
            case MotionEvent.ACTION_CANCEL:
                enable = false;
                if (this.onListener != null) {
                    onListener.onCancel();
                }
                this.model = cancelModel;
        }

        return true;
    }


    public interface OnListener {
        void onDown();

        void onChange();

        void onUp();

        void onCancel();
    }
}
