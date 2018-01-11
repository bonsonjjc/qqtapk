package com.bonson.qqtapk.view.ui.index.main;

import android.app.Application;
import android.databinding.ObservableField;
import android.os.Handler;

import com.bonson.qqtapk.model.bean.Baby;
import com.bonson.qqtapk.model.bean.Location;
import com.bonson.qqtapk.model.data.location.LocationModel;
import com.bonson.resource.activity.BaseView;
import com.bonson.resource.viewmodel.AndroidViewModel;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;


public class LocationViewModel extends AndroidViewModel {
    public final ObservableField<String> address = new ObservableField<>("");
    public final ObservableField<String> time = new ObservableField<>("当前:");

    private BaseView view;

    private LocationModel locationModel;

    @Inject
    public LocationViewModel(Application application, LocationModel locationModel) {
        super(application);
        this.locationModel = locationModel;
    }

    public void setView(BaseView view) {
        this.view = view;
    }

    public void location() {
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        view.load();
        Disposable disposable = locationModel.location(Baby.baby.getFid())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    view.toast(it.getMsg());
                    if (it.getCode().equals("0")) {
                        result(it.getBody(), 1);
                    }
                }, e -> {
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
            Disposable disposable = locationModel.result(Baby.baby.getFid(), seqid, count)
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
                                view.toast(result.getMsg());
                                break;
                        }
                    }, e -> {
                        view.toast("定位失败");
                    });
            compositeDisposable.add(disposable);
        }, 8000);
    }

    @Override
    public void onDestroy() {
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}
