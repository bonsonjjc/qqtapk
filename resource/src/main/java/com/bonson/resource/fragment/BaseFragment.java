package com.bonson.resource.fragment;

import android.widget.Toast;

import com.bonson.resource.activity.BaseView;

import dagger.android.support.DaggerFragment;

/**
 * Created by zjw on 2017/12/29.
 */

public abstract class BaseFragment extends DaggerFragment implements BaseView {
    @Override
    public void load() {

    }

    @Override
    public void toast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void start(String url) {

    }
}
