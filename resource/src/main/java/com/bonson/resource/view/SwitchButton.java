package com.bonson.resource.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Property;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.bonson.resource.R;

/**
 * Created by 7heaven on 15/3/14.
 */
public class SwitchButton extends View {

    private static final long commonDuration = 300L;

    private ObjectAnimator innerContentAnimator;
    private Property<SwitchButton, Float> innerContentProperty =
            new Property<SwitchButton, Float>(Float.class, "innerBound") {
                @Override
                public void set(SwitchButton sv, Float innerContentRate) {
                    sv.setInnerContentRate(innerContentRate);
                }

                @Override
                public Float get(SwitchButton sv) {
                    return sv.getInnerContentRate();
                }
            };

    private ObjectAnimator knobExpandAnimator;
    private Property<SwitchButton, Float> knobExpandProperty =
            new Property<SwitchButton, Float>(Float.class, "knobExpand") {
                @Override
                public void set(SwitchButton sv, Float knobExpandRate) {
                    sv.setKnobExpandRate(knobExpandRate);
                }

                @Override
                public Float get(SwitchButton sv) {
                    return sv.getKnobExpandRate();
                }
            };

    private ObjectAnimator knobMoveAnimator;
    private Property<SwitchButton, Float> knobMoveProperty =
            new Property<SwitchButton, Float>(Float.class, "knobMove") {
                @Override
                public void set(SwitchButton sv, Float knobMoveRate) {
                    sv.setKnobMoveRate(knobMoveRate);
                }

                @Override
                public Float get(SwitchButton sv) {
                    return sv.getKnobMoveRate();
                }
            };

    private GestureDetector gestureDetector;
    private GestureDetector.SimpleOnGestureListener gestureListener =
            new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onDown(MotionEvent event) {

                    if (!isEnabled()) return false;

                    preIsOn = check;

                    innerContentAnimator.setFloatValues(innerContentRate, 0.0F);
                    innerContentAnimator.start();

                    knobExpandAnimator.setFloatValues(knobExpandRate, 1.0F);
                    knobExpandAnimator.start();

                    return true;
                }

                @Override
                public void onShowPress(MotionEvent event) {

                }

                @Override
                public boolean onSingleTapUp(MotionEvent event) {

                    check = knobState;

                    if (preIsOn == check) {
                        check = !check;
                        knobState = !knobState;
                    }

                    if (knobState) {

                        knobMoveAnimator.setFloatValues(knobMoveRate, 1.0F);
                        knobMoveAnimator.start();

                        innerContentAnimator.setFloatValues(innerContentRate, 0.0F);
                        innerContentAnimator.start();
                    } else {

                        knobMoveAnimator.setFloatValues(knobMoveRate, 0.0F);
                        knobMoveAnimator.start();

                        innerContentAnimator.setFloatValues(innerContentRate, 1.0F);
                        innerContentAnimator.start();
                    }

                    knobExpandAnimator.setFloatValues(knobExpandRate, 0.0F);
                    knobExpandAnimator.start();

                    if (SwitchButton.this.onCheckedChangeListener != null && check != preIsOn) {
                        SwitchButton.this.onCheckedChangeListener.onSwitchStateChange(check);
                    }

                    return true;
                }

                @Override
                public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

                    if (e2.getX() > centerX) {
                        if (!knobState) {
                            knobState = true;

                            knobMoveAnimator.setFloatValues(knobMoveRate, 1.0F);
                            knobMoveAnimator.start();

                            innerContentAnimator.setFloatValues(innerContentRate, 0.0F);
                            innerContentAnimator.start();
                        }
                    } else {
                        if (knobState) {
                            knobState = false;

                            knobMoveAnimator.setFloatValues(knobMoveRate, 0.0F);
                            knobMoveAnimator.start();
                        }
                    }

                    return true;
                }
            };

    private int width;
    private int height;

    private int centerX;
    private int centerY;

    private float cornerRadius;

    private int shadowSpace;
    private int outerStrokeWidth;

    private RectF knobBound;
    private float knobMaxExpandWidth;
    private float intrinsicKnobWidth;
    private float knobExpandRate;
    private float knobMoveRate;

    private boolean knobState;
    private boolean check;
    private boolean preIsOn;

    private RectF innerContentBound;
    private float innerContentRate = 1.0F;
    private float intrinsicInnerWidth;
    private float intrinsicInnerHeight;

    private int tintColor;

    private int tempTintColor;

    private static final int backgroundColor = 0xFFCCCCCC;
    private static final int foregroundColor = Color.WHITE;

    private Paint paint;

    private RectF tempForRoundRect;

    private boolean dirtyAnimation = false;
    private boolean isAttachedToWindow = false;
    public interface OnCheckedChangeListener {
        void onSwitchStateChange(boolean isOn);
    }

    private OnCheckedChangeListener onCheckedChangeListener;

    public SwitchButton(Context context) {
        this(context, null);
    }

    public SwitchButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwitchButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SwitchButton);

        tintColor = ta.getColor(R.styleable.SwitchButton_tintColor, 0xFF4FB33F);
        tempTintColor = tintColor;

        int defaultOuterStrokeWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1.5F,
                context.getResources().getDisplayMetrics());
        int defaultShadowSpace = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5,
                context.getResources().getDisplayMetrics());

        outerStrokeWidth = ta.getDimensionPixelOffset(R.styleable.SwitchButton_outerStrokeWidth,
                defaultOuterStrokeWidth);
        shadowSpace =
                ta.getDimensionPixelOffset(R.styleable.SwitchButton_shadowSpace, defaultShadowSpace);

        ta.recycle();

        knobBound = new RectF();
        innerContentBound = new RectF();

        tempForRoundRect = new RectF();

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        gestureDetector = new GestureDetector(context, gestureListener);
        gestureDetector.setIsLongpressEnabled(false);

        if (Build.VERSION.SDK_INT >= 14) {
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }

        innerContentAnimator =
                ObjectAnimator.ofFloat(SwitchButton.this, innerContentProperty, innerContentRate, 1.0F);
        innerContentAnimator.setDuration(commonDuration);
        innerContentAnimator.setInterpolator(new DecelerateInterpolator());

        knobExpandAnimator =
                ObjectAnimator.ofFloat(SwitchButton.this, knobExpandProperty, knobExpandRate, 1.0F);
        knobExpandAnimator.setDuration(commonDuration);
        knobExpandAnimator.setInterpolator(new DecelerateInterpolator());

        knobMoveAnimator =
                ObjectAnimator.ofFloat(SwitchButton.this, knobMoveProperty, knobMoveRate, 1.0F);
        knobMoveAnimator.setDuration(commonDuration);
        knobMoveAnimator.setInterpolator(new DecelerateInterpolator());

        Drawable shadowDrawable = new ColorDrawable(Color.TRANSPARENT);
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener onCheckedChangeListener) {
        this.onCheckedChangeListener = onCheckedChangeListener;
    }

    public OnCheckedChangeListener getOnCheckedChangeListener() {
        return this.onCheckedChangeListener;
    }

    void setInnerContentRate(float rate) {
        this.innerContentRate = rate;

        invalidate();
    }

    float getInnerContentRate() {
        return this.innerContentRate;
    }

    void setKnobExpandRate(float rate) {
        this.knobExpandRate = rate;

        invalidate();
    }

    float getKnobExpandRate() {
        return this.knobExpandRate;
    }

    void setKnobMoveRate(float rate) {
        this.knobMoveRate = rate;

        invalidate();
    }

    float getKnobMoveRate() {
        return knobMoveRate;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        isAttachedToWindow = true;

        if (dirtyAnimation) {
            knobState = this.check;
            if (knobState) {

                knobMoveAnimator.setFloatValues(knobMoveRate, 1.0F);
                knobMoveAnimator.start();

                innerContentAnimator.setFloatValues(innerContentRate, 0.0F);
                innerContentAnimator.start();
            } else {

                knobMoveAnimator.setFloatValues(knobMoveRate, 0.0F);
                knobMoveAnimator.start();

                innerContentAnimator.setFloatValues(innerContentRate, 1.0F);
                innerContentAnimator.start();
            }

            knobExpandAnimator.setFloatValues(knobExpandRate, 0.0F);
            knobExpandAnimator.start();
            dirtyAnimation = false;
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        isAttachedToWindow = false;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);

        // make sure widget remain in a good appearance
        if ((float) height / (float) width < 0.33333F) {
            height = (int) ((float) width * 0.33333F);

            widthMeasureSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.getMode(widthMeasureSpec));
            heightMeasureSpec =
                    MeasureSpec.makeMeasureSpec(height, MeasureSpec.getMode(heightMeasureSpec));

            super.setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
        }

        centerX = width / 2;
        centerY = height / 2;

        cornerRadius = centerY - shadowSpace;

        innerContentBound.left = outerStrokeWidth + shadowSpace;
        innerContentBound.top = outerStrokeWidth + shadowSpace;
        innerContentBound.right = width - outerStrokeWidth - shadowSpace;
        innerContentBound.bottom = height - outerStrokeWidth - shadowSpace;

        intrinsicInnerWidth = innerContentBound.width();
        intrinsicInnerHeight = innerContentBound.height();

        knobBound.left = outerStrokeWidth + shadowSpace;
        knobBound.top = outerStrokeWidth + shadowSpace;
        knobBound.right = height - outerStrokeWidth - shadowSpace;
        knobBound.bottom = height - outerStrokeWidth - shadowSpace;

        intrinsicKnobWidth = knobBound.height();
        knobMaxExpandWidth = (float) width * 0.7F;
        if (knobMaxExpandWidth > knobBound.width() * 1.25F) {
            knobMaxExpandWidth = knobBound.width() * 1.25F;
        }
    }

    public boolean isChecked() {
        return this.check;
    }

    public void setChecked(boolean check) {
        setChecked(check, false);
    }

    public void setChecked(boolean check, boolean animated) {

        if (this.check == check) return;

        if (!isAttachedToWindow && animated) {
            dirtyAnimation = true;
            this.check = check;

            return;
        }

        this.check = check;
        knobState = this.check;

        if (!animated) {

            if (check) {
                setKnobMoveRate(1.0F);
                setInnerContentRate(0.0F);
            } else {
                setKnobMoveRate(0.0F);
                setInnerContentRate(1.0F);
            }

            setKnobExpandRate(0.0F);
        } else {
            if (knobState) {

                knobMoveAnimator.setFloatValues(knobMoveRate, 1.0F);
                knobMoveAnimator.start();

                innerContentAnimator.setFloatValues(innerContentRate, 0.0F);
                innerContentAnimator.start();
            } else {

                knobMoveAnimator.setFloatValues(knobMoveRate, 0.0F);
                knobMoveAnimator.start();

                innerContentAnimator.setFloatValues(innerContentRate, 1.0F);
                innerContentAnimator.start();
            }

            knobExpandAnimator.setFloatValues(knobExpandRate, 0.0F);
            knobExpandAnimator.start();
        }
    }

    public void setTintColor(int tintColor) {
        this.tintColor = tintColor;
        tempTintColor = this.tintColor;
    }

    public int getTintColor() {
        return this.tintColor;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (!isEnabled()) return false;

        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if (!knobState) {
                    innerContentAnimator =
                            ObjectAnimator.ofFloat(SwitchButton.this, innerContentProperty, innerContentRate,
                                    1.0F);
                    innerContentAnimator.setDuration(300L);
                    innerContentAnimator.setInterpolator(new DecelerateInterpolator());

                    innerContentAnimator.start();
                }

                knobExpandAnimator =
                        ObjectAnimator.ofFloat(SwitchButton.this, knobExpandProperty, knobExpandRate, 0.0F);
                knobExpandAnimator.setDuration(300L);
                knobExpandAnimator.setInterpolator(new DecelerateInterpolator());

                knobExpandAnimator.start();

                check = knobState;

                if (SwitchButton.this.onCheckedChangeListener != null && check != preIsOn) {
                    SwitchButton.this.onCheckedChangeListener.onSwitchStateChange(check);
                }

                break;
        }

        return gestureDetector.onTouchEvent(event);
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);

        if (enabled) {
            this.tintColor = tempTintColor;
        } else {
            this.tintColor = this.RGBColorTransform(0.5F, tempTintColor, Color.WHITE);
        }
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // innerContentCalculation begin
        float w = (float) intrinsicInnerWidth / 2.0F * innerContentRate;
        float h = (float) intrinsicInnerHeight / 2.0F * innerContentRate;

        this.innerContentBound.left = centerX - w;
        this.innerContentBound.top = centerY - h;
        this.innerContentBound.right = centerX + w;
        this.innerContentBound.bottom = centerY + h;
        // innerContentCalculation end

        // knobExpandCalculation begin
        w = intrinsicKnobWidth + (float) (knobMaxExpandWidth - intrinsicKnobWidth) * knobExpandRate;

        boolean left = knobBound.left + knobBound.width() / 2 > centerX;

        if (left) {
            knobBound.left = knobBound.right - w;
        } else {
            knobBound.right = knobBound.left + w;
        }
        // knobExpandCalculation end

        // knobMoveCalculation begin
        float kw = knobBound.width();
        w = (float) (width - kw - ((shadowSpace + outerStrokeWidth) * 2)) * knobMoveRate;

        int colorStep = RGBColorTransform(knobMoveRate, backgroundColor, tintColor);

        knobBound.left = shadowSpace + outerStrokeWidth + w;
        knobBound.right = knobBound.left + kw;
        // knobMoveCalculation end

        // background
        paint.setColor(colorStep);
        paint.setStyle(Paint.Style.FILL);

        drawRoundRect(shadowSpace, shadowSpace, width - shadowSpace, height - shadowSpace, cornerRadius,
                canvas, paint);

        // innerContent
        paint.setColor(foregroundColor);
        canvas.drawRoundRect(innerContentBound, innerContentBound.height() / 2,
                innerContentBound.height() / 2, paint);

        // knob
        // shadowDrawable.setBounds((int) (knobBound.left - shadowSpace), (int)
        // (knobBound.top - shadowSpace), (int) (knobBound.right + shadowSpace),
        // (int) (knobBound.bottom + shadowSpace));
        // shadowDrawable.draw(canvas);
        paint.setShadowLayer(2, 0, shadowSpace / 2, isEnabled() ? 0x20000000 : 0x10000000);

        // paint.setColor(isEnabled() ? 0x20000000 : 0x10000000);
        // drawRoundRect(knobBound.left, knobBound.top + shadowSpace / 2,
        // knobBound.right, knobBound.bottom + shadowSpace / 2, cornerRadius -
        // outerStrokeWidth, canvas, paint);
        //
        // paint.setColor(foregroundColor);
        canvas.drawRoundRect(knobBound, cornerRadius - outerStrokeWidth,
                cornerRadius - outerStrokeWidth, paint);
        paint.setShadowLayer(0, 0, 0, 0);

        paint.setColor(backgroundColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);

        canvas.drawRoundRect(knobBound, cornerRadius - outerStrokeWidth,
                cornerRadius - outerStrokeWidth, paint);
    }

    private void drawRoundRect(float left, float top, float right, float bottom, float radius,
                               Canvas canvas, Paint paint) {

        tempForRoundRect.left = left;
        tempForRoundRect.top = top;
        tempForRoundRect.right = right;
        tempForRoundRect.bottom = bottom;

        canvas.drawRoundRect(tempForRoundRect, radius, radius, paint);
    }

    // seperate RGB channels and calculate new value for each channel
    // ignore alpha channel
    private int RGBColorTransform(float progress, int fromColor, int toColor) {
        int or = (fromColor >> 16) & 0xFF;
        int og = (fromColor >> 8) & 0xFF;
        int ob = fromColor & 0xFF;

        int nr = (toColor >> 16) & 0xFF;
        int ng = (toColor >> 8) & 0xFF;
        int nb = toColor & 0xFF;

        int rGap = (int) ((float) (nr - or) * progress);
        int gGap = (int) ((float) (ng - og) * progress);
        int bGap = (int) ((float) (nb - ob) * progress);

        return Color.BLACK | ((or + rGap) << 16) | ((og + gGap) << 8) | (ob + bGap);
    }
}
