package com.bonson.qqtapk.view.ui.motion.target;

import android.app.Application;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;

import com.bonson.library.utils.NumberUtils;
import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.model.data.motion.MotionDataSource;
import com.bonson.qqtapk.view.ui.motion.bean.Target;
import com.bonson.qqtapk.viewmodel.UserViewModel;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

@ActivityScope
public class TargetViewModel extends UserViewModel {
    public final ObservableInt type = new ObservableInt(1);
    public final ObservableInt progress = new ObservableInt(0);
    private MotionDataSource dataSource;

    private Target target = new Target();

    @Inject
    public TargetViewModel(@NonNull Application application, MotionDataSource dataSource) {
        super(application);
        this.dataSource = dataSource;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void motion() {
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        view.load();
        Disposable disposable = dataSource.target(user().getBabyId())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    view.dismiss();
                    view.toast(it.getMsg());
                    if (it.getCode().equals("0")) {
                        target = it.getBody();
                        progress.set(NumberUtils.parseInt(type.get() == 1 ? target.getFwalk() : target.getFsleep()));
                    }
                }, e -> {
                    view.dismiss();
                    view.toast("出错了");
                });
        compositeDisposable.add(disposable);
    }

    public void save() {
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        view.load();
        if (type.get() == 1) {
            target.setFwalk(progress.get() + "");
        } else {
            target.setFsleep(progress.get() + "");
        }
        Disposable disposable = dataSource.setTarget(user().getBabyId(), target)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    view.dismiss();
                    view.toast(it.getMsg());
                }, e -> {
                    view.dismiss();
                    view.toast("出错了");
                });
        compositeDisposable.add(disposable);
    }
}
