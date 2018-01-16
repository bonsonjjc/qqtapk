package com.bonson.resource.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.bonson.resource.R;


public class LoadingDialog extends DialogFragment {
    private String msg;

    private TextView tvMsg;

    private boolean outside;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.loading_dialog, container, false);
        tvMsg = view.findViewById(R.id.tv_hint);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvMsg.setText(msg);
    }

    @Override
    public void onResume() {
        super.onResume();
        // 设置背景透明
        getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        getDialog().setCanceledOnTouchOutside(outside);
    }

    public LoadingDialog setCanceledOnTouchOutside(boolean cancel) {
        this.outside = cancel;
        return this;
    }

    public LoadingDialog setText(String text) {
        this.msg = text;
        return this;
    }

    public boolean isShowing() {
        Dialog dialog = getDialog();
        if (dialog != null) {
            return dialog.isShowing();
        }
        return false;
    }
}
