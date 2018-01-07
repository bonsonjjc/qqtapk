package com.bonson.resource.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.widget.TextView;
import com.bonson.resource.R;

/**
 * Created by zjw on 2017/12/29.
 */

public class MToolbar extends Toolbar {
  TextView tvLeft, tvTitle, tvRight;
  //ImageView imgLeft, imgTitle, imgRight;

  public MToolbar(Context context) {
    this(context, null);
  }

  public MToolbar(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public MToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override protected void onFinishInflate() {
    super.onFinishInflate();
    tvLeft = findViewById(R.id.bar_left_text);
    tvTitle = findViewById(R.id.bar_title_text);
    tvRight = findViewById(R.id.bar_right_text);
  }

  @Override public void setTitle(int resId) {
    if (tvTitle != null) tvTitle.setText(resId);
  }

  @Override public void setTitle(CharSequence title) {
    if (tvTitle != null) tvTitle.setText(title);
  }

  @Override public void setTitleTextColor(int color) {
    if (tvTitle != null) tvTitle.setTextColor(color);
  }

  public TextView getTvLeft() {
    return tvLeft;
  }

  public TextView getTvTitle() {
    return tvTitle;
  }

  public TextView getTvRight() {
    return tvRight;
  }

  public void setLeftText(String text) {
    if (tvLeft != null) tvLeft.setText(text);
  }

  public void setRightText(String text) {
    if (tvRight != null) tvRight.setText(text);
  }

  public void setLeftTextColor(int color) {
    if (tvLeft != null) tvTitle.setTextColor(color);
  }

  public void setRightTextColor(int color) {
    if (tvRight != null) tvTitle.setTextColor(color);
  }

  public void setLeftText(int resId) {
    if (tvLeft != null) tvLeft.setText(resId);
  }

  public void setRightText(int resId) {
    if (tvRight != null) tvRight.setText(resId);
  }
}
