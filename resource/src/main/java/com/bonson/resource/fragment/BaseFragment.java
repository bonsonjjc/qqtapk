package com.bonson.resource.fragment;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.bonson.resource.activity.BaseDaggerActivity;
import com.bonson.resource.activity.BaseView;

import dagger.android.support.DaggerFragment;

/**
 * Created by zjw on 2017/12/29.
 */

public abstract class BaseFragment extends DaggerFragment implements BaseView {
    @Override
    public void load() {
        Activity activity = getActivity();
        if (activity instanceof BaseDaggerActivity) {
            ((BaseDaggerActivity) activity).load();
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
}
