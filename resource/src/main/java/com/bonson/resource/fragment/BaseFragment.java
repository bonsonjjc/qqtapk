package com.bonson.resource.fragment;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.inputmethodservice.InputMethodService;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.bonson.resource.activity.BaseDaggerActivity;
import com.bonson.resource.activity.BaseView;
import com.bonson.resource.viewmodel.AndroidViewModel;

import dagger.android.support.DaggerFragment;

/**
 * Created by zjw on 2017/12/29.
 */

public abstract class BaseFragment<Binding extends ViewDataBinding> extends DaggerFragment implements BaseView {
    protected Binding binding;

    public void setBindingLayout(LayoutInflater inflater, int layout, ViewGroup parent) {
        binding = DataBindingUtil.inflate(inflater, layout, parent, false);
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
    public void load() {
        Activity activity = getActivity();
        if (activity instanceof BaseDaggerActivity) {
            ((BaseDaggerActivity) activity).load();
        }
    }

    @Override
    public void dismiss() {
        Activity activity = getActivity();
        if (activity instanceof BaseDaggerActivity) {
            ((BaseDaggerActivity) activity).dismiss();
        }
    }

    @Override
    public void toast(String msg) {
        Activity activity = getActivity();
        if (activity instanceof BaseDaggerActivity) {
            ((BaseDaggerActivity) activity).toast(msg);
        }
    }

    @Override
    public void start(String url) {
        Activity activity = getActivity();
        if (activity instanceof BaseDaggerActivity) {
            ((BaseDaggerActivity) activity).start(url);
        }
    }

    @Override
    public void back() {
        Activity activity = getActivity();
        if (activity instanceof BaseDaggerActivity) {
            ((BaseDaggerActivity) activity).back();
        }
    }

    public void closeSoftKeyboard() {
        InputMethodManager inputManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        //得到InputMethodManager的实例
        inputManager.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }
}
