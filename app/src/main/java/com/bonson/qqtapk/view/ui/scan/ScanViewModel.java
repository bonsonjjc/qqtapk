package com.bonson.qqtapk.view.ui.scan;

import android.app.Application;

import com.bonson.qqtapk.model.bean.Baby;
import com.bonson.qqtapk.model.bean.User;
import com.bonson.qqtapk.model.data.baby.BabyModel;
import com.bonson.qqtapk.viewmodel.UserViewModel;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class ScanViewModel extends UserViewModel {
    private BabyModel babyModel;

    @Inject
    public ScanViewModel(Application application, BabyModel babyModel) {
        super(application);
        this.babyModel = babyModel;
    }

    public void add(String imei) {
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        view.load();
        Disposable disposable = babyModel.bind(user().getUserId(), imei)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    view.dismiss();
                    view.toast(it.getMsg());
                    if (it.getCode().equals("0")) {
                        getUserDao().insertBaby(it.getBody());
                        user().setBabyId(it.getBody().getFid());
                        getUserDao().insertUer(user());
                    }
                }, e -> {
                    view.dismiss();
                    e.printStackTrace();
                });
        compositeDisposable.add(disposable);
    }

    public void change(String imei) {
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        view.load();
        baby().setFimei(imei);
        Disposable disposable = babyModel.changeIMEI(baby())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    view.dismiss();
                    view.toast(it.getMsg());
                    if (it.getCode().equals("0")) {
                        baby().setFimei(imei);
                        getUserDao().insertBaby(baby());
                    }
                }, e -> {
                    view.dismiss();
                    e.printStackTrace();
                });
        compositeDisposable.add(disposable);
    }
}
