package com.bonson.qqtapk.view.ui.motion;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.bonson.qqtapk.model.bean.Baby;
import com.bonson.qqtapk.model.data.motion.MotionModel;
import com.bonson.resource.activity.BaseView;
import com.bonson.resource.viewmodel.AndroidViewModel;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;


public class MotionViewModel extends AndroidViewModel {
    private MotionModel motionModel;

    public final ObservableList<Object> dataList = new ObservableArrayList<>();

    @Inject
    public MotionViewModel(Application application, MotionModel motionModel) {
        super(application);
        this.motionModel = motionModel;
    }

    public void motion() {
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        Disposable disposable = motionModel.motion(Baby.baby.getFid())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    if (it.getCode().equals("0")) {
                        dataList.clear();
                        dataList.addAll(it.getBody());
                    } else {
                        view.toast(it.getMsg());
                    }
                }, e -> {
                    view.toast("出错了");
                    e.printStackTrace();
                });
        compositeDisposable.add(disposable);
    }

    public void sleep() {
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        Disposable disposable = motionModel.sleep(Baby.baby.getFid())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    if (it.getCode().equals("0")) {
                        dataList.clear();
                        dataList.addAll(it.getBody());
                    } else {
                        view.toast(it.getMsg());
                    }
                }, e -> {
                    view.toast("出错了");
                    e.printStackTrace();
                });
        compositeDisposable.add(disposable);
    }
}
