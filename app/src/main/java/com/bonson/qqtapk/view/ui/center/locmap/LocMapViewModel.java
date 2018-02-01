package com.bonson.qqtapk.view.ui.center.locmap;

import android.app.Application;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.baidu.mapapi.model.LatLng;
import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.model.bean.Message;
import com.bonson.qqtapk.model.data.center.MessageModel;
import com.bonson.qqtapk.viewmodel.UserViewModel;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

@ActivityScope
public class LocMapViewModel extends UserViewModel {
    @Inject
    MessageModel messageModel;
    public ObservableField<LatLng> position = new ObservableField<>();
    public String address;

    @Inject
    public LocMapViewModel(@NonNull Application application) {
        super(application);
    }

    public void map(String fid) {
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        view.load();
        Disposable disposable = messageModel.mapDetail(fid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    view.dismiss();
                    view.toast(it.getMsg());
                    if (it.getCode().equals("0")) {
                        Message body = it.getBody();
                    }
                    position.set(new LatLng(26.1447671166, 119.3322610285));
                }, e -> {
                    view.dismiss();
                    view.toast("出错了");
                    position.set(new LatLng(26.1447671166, 119.3322610285));
                });
        compositeDisposable.add(disposable);
    }
}
