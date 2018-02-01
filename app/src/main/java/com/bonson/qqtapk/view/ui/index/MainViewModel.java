package com.bonson.qqtapk.view.ui.index;

import android.app.Application;
import android.databinding.ObservableField;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.model.bean.Baby;
import com.bonson.qqtapk.model.bean.Device;
import com.bonson.qqtapk.model.bean.Location;
import com.bonson.qqtapk.model.data.index.MainModel;
import com.bonson.qqtapk.viewmodel.UserViewModel;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * Created by zjw on 2018/1/2.
 */
@ActivityScope
public class MainViewModel extends UserViewModel {
    private MainModel indexModel;

    public final ObservableField<String> type = new ObservableField<>("L08");
    public final ObservableField<String> battery = new ObservableField<>("0");
    public final ObservableField<String> stepNumber = new ObservableField<>("0步");
    public final ObservableField<String> sleepTime = new ObservableField<>("0小时");
    public final ObservableField<String> heart = new ObservableField<>("正常");

    @Inject
    LocationViewModel viewModel;

    @Inject
    public MainViewModel(Application application, MainModel indexModel) {
        super(application);
        this.indexModel = indexModel;
    }

    public LocationViewModel getViewModel() {
        return viewModel;
    }

    public void device() {
        type.set(baby().getFtag());
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        view.load();
        Disposable disposable = indexModel.device(user().getBabyId())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    view.dismiss();
                    view.toast(it.getMsg());
                    if (it.getCode().equals("0")) {
                        Device device = Device.device;
                        battery.set(device.getFpower());
                        Location location = it.getBody();
                        viewModel.address.set(location.getFshort());
                        viewModel.time.set(location.getFctime());
                    }
                }, e -> {
                    view.dismiss();
                    view.toast("出错了");
                });
        compositeDisposable.add(disposable);
    }


    public void listener() {
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        view.load();
        Disposable disposable = indexModel.listener(user().getBabyId(), user().getUserId())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    view.dismiss();
                    view.toast(it.getMsg());
                    if (it.getCode().equals("0")) {

                    }
                }, e -> {
                    view.dismiss();
                    view.toast("出错了");
                    e.printStackTrace();
                });
        compositeDisposable.add(disposable);
    }

    public void lockMachine() {
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        view.load();
        Disposable disposable = indexModel.lockMachine(user().getBabyId(), user().getUserId())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    view.dismiss();
                    view.toast(it.getMsg());
                    if (it.getCode().equals("0")) {

                    }
                }, e -> {
                    view.dismiss();
                    view.toast("出错了");
                    e.printStackTrace();
                });
        compositeDisposable.add(disposable);
    }
}
