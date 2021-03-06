package com.bonson.qqtapk.view.ui.index;

import android.app.Application;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;
import android.databinding.ObservableField;
import android.os.Handler;

import com.bonson.qqtapk.model.bean.Baby;
import com.bonson.qqtapk.model.bean.Location;
import com.bonson.qqtapk.model.data.location.LocationModelSource;
import com.bonson.qqtapk.viewmodel.UserViewModel;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;


public class LocationViewModel extends UserViewModel {
    public final ObservableField<String> address = new ObservableField<>("");
    public final ObservableField<String> time = new ObservableField<>("当前:");

    private LocationModelSource locationModel;

    @Inject
    public LocationViewModel(Application application, LocationModelSource locationModel) {
        super(application);
        this.locationModel = locationModel;
    }

    public void location() {
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        view.load();
        Disposable disposable = locationModel.location(user().getBabyId())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    view.toast(it.getMsg());
                    if (it.getCode().equals("0")) {
                        result(it.getBody(), 1);
                    } else {
                        view.dismiss();
                    }
                }, e -> {
                    view.dismiss();
                    view.toast("出错了");
                });
        compositeDisposable.add(disposable);
    }

    private Handler handler = new Handler();

    public void result(String seqid, int count) {
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        handler.postDelayed(() -> {
            Disposable disposable = locationModel.result(user().getBabyId(), seqid, count)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(result -> {
                        switch (result.getCode()) {
                            case "0":
                                Location location = result.getBody();
                                view.toast(result.getMsg());
                                address.set(location.getFshort());
                                address.set(location.getFctime());
                                break;
                            case "1":
                                result(seqid, count + 1);
                                break;
                            default:
                                view.dismiss();
                                view.toast(result.getMsg());
                                break;
                        }
                    }, e -> {
                        view.dismiss();
                        view.toast("定位失败");
                    });
            compositeDisposable.add(disposable);
        }, 8000);
    }

    @Override
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
