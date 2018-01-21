package com.bonson.resource.dialog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import com.bonson.resource.R;

/**
 * Created by jiangjiancheng on 18/1/14.
 */

public class AlertDialog extends DialogFragment {
  TextView tvTitle;
  TextView tvContent;

  TextView tvCancel;
  TextView tvSure;

  String title = "";
  String content = "";
  String sure = "";
  String cancel = "";

  View vBtnLine, vTitleLine;

  boolean outside;

  public static final int Sure = 1, Cancel = 2;

  @Nullable @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    // 去掉标题 死恶心死恶心的
    getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
    View view = inflater.inflate(R.layout.alert_dialog, container, false);
    tvTitle = view.findViewById(R.id.tv_title);
    tvContent = view.findViewById(R.id.tv_content);
    tvCancel = view.findViewById(R.id.tv_cancel);
    tvSure = view.findViewById(R.id.tv_sure);
    vTitleLine = view.findViewById(R.id.line_title);
    vBtnLine = view.findViewById(R.id.line_btn);
    return view;
  }
  @Override public void onResume() {
    super.onResume();
    // 设置背景透明
    getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    getDialog().setCanceledOnTouchOutside(outside);
  }
  @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    tvCancel.setText(cancel);
    tvSure.setText(sure);
    tvTitle.setText(title);
    tvContent.setText(content);

    View.OnClickListener listener = new View.OnClickListener() {
      @Override public void onClick(View v) {
        dismiss();
        if (onClickListener != null) {
          onClickListener.onClick(v, v.getId() == R.id.tv_cancel ? Cancel : Sure);
        }
      }
    };
    tvCancel.setOnClickListener(listener);
    tvSure.setOnClickListener(listener);
    builder();
  }

  public AlertDialog setCanceledOnTouchOutside(boolean outside) {
    this.outside = outside;
    if (getDialog() != null) {
      getDialog().setCanceledOnTouchOutside(outside);
    }
    return this;
  }

  private void builder() {
    if (tvCancel != null) {
      tvCancel.setVisibility(TextUtils.isEmpty(cancel) ? View.GONE : View.VISIBLE);
    }
    if (tvSure != null) {
      tvSure.setVisibility(TextUtils.isEmpty(sure) ? View.GONE : View.VISIBLE);
    }
    boolean showLine = !TextUtils.isEmpty(sure) && !TextUtils.isEmpty(cancel);
    if (vBtnLine != null) {
      if (showLine) {
        tvCancel.setBackgroundResource(R.drawable.btn_left_sel);
        tvSure.setBackgroundResource(R.drawable.btn_right_sel);
        vBtnLine.setVisibility(View.VISIBLE);
      } else {
        tvCancel.setBackgroundResource(R.drawable.btn_middle_sel);
        tvSure.setBackgroundResource(R.drawable.btn_middle_sel);
        vBtnLine.setVisibility(View.GONE);
      }
    }
    if (tvTitle != null) {
      if (!TextUtils.isEmpty(title)) {
        tvTitle.setVisibility(View.VISIBLE);
        vTitleLine.setVisibility(View.VISIBLE);
      }
    }
  }

  public AlertDialog setOnClickListener(OnClickListener onClickListener) {
    this.onClickListener = onClickListener;
    return this;
  }

  public AlertDialog setTitle(String title) {
    this.title = title;
    builder();
    return this;
  }

  public AlertDialog setContent(String content) {
    this.content = content;
    return this;
  }

  public AlertDialog setSure(String text) {
    this.sure = text;
    builder();
    return this;
  }

  public AlertDialog setCancel(String text) {
    this.cancel = text;
    builder();
    return this;
  }

  private OnClickListener onClickListener;

  public interface OnClickListener {
    void onClick(View view, int witch);
  }
}
