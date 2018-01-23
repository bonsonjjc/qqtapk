package com.bonson.resource.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import com.bonson.resource.R;
import com.bonson.resource.dialog.LoadingDialog;
import com.bonson.resource.viewmodel.AndroidViewModel;

import dagger.android.support.DaggerAppCompatActivity;

/**
 * Created by zjw on 2017/12/29.
 */

public abstract class BaseDaggerActivity<Binding extends ViewDataBinding> extends DaggerAppCompatActivity implements BaseView {
    protected Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().findViewById(android.R.id.content).setBackgroundColor(color(R.color.background));
        ActivityUtils.push(this);
    }

    public void setBindingLayout(int layout) {
        binding = DataBindingUtil.setContentView(this, layout);
    }

    public void setViewModel(AndroidViewModel... viewModel) {
        if (viewModel != null && viewModel.length != 0) {
            for (int i = 0; i < viewModel.length; i++) {
                getLifecycle().addObserver(viewModel[i]);
                viewModel[i].setView(this);
            }
        }
    }

    @Override
    protected void onDestroy() {
        ActivityUtils.pop(this);
        super.onDestroy();
    }

    private LoadingDialog dialog;

    @Override
    public void load() {
        if (dialog == null) {
            dialog = new LoadingDialog();
        }
        dialog.setText("请稍后..");
        if (!dialog.isShowing()) {
            dialog.show(getSupportFragmentManager(), "load");
        }
    }

    @Override
    public void dismiss() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    @Override
    public void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void start(String url) {
        try {
            Class urlClass = Class.forName(url);
            startActivity(new Intent(this, urlClass));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void back() {
        onBackPressed();
    }

    public int color(@ColorRes int resId) {
        return ActivityCompat.getColor(this, resId);
    }

    public Drawable drawable(@DrawableRes int resId) {
        return ActivityCompat.getDrawable(this, resId);
    }
}
