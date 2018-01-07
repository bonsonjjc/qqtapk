package com.bonson.resource.viewmodel;

import android.app.Application;
import android.content.Context;
import android.databinding.BaseObservable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.bonson.resource.activity.BaseView;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by jiangjiancheng on 17/12/31.
 */

public abstract class AndroidViewModel extends BaseObservable {
    private Application application;
    protected CompositeDisposable compositeDisposable;

    public AndroidViewModel(Application application) {
        this.application = application;
        compositeDisposable = new CompositeDisposable();
    }

    public Application getApplication() {
        return application;
    }

    protected boolean isNetWork() {
        // 获得网络状态管理器
        ConnectivityManager connectivityManager = (ConnectivityManager) getApplication()
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager == null) {
            return false;
        } else {
            // 建立网络数组
            NetworkInfo[] net_info = connectivityManager.getAllNetworkInfo();
            if (net_info != null) {
                for (NetworkInfo aNet_info : net_info) {
                    // 判断获得的网络状态是否是处于连接状态
                    if (aNet_info.getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void onDestroy() {
        compositeDisposable.clear();
    }
}
