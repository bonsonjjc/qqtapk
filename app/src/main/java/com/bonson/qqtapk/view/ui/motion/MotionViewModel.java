package com.bonson.qqtapk.view.ui.motion;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableInt;
import android.databinding.ObservableList;

import com.bonson.qqtapk.model.bean.Baby;
import com.bonson.qqtapk.model.bean.Motion;
import com.bonson.qqtapk.model.bean.Sleep;
import com.bonson.qqtapk.model.data.motion.MotionModel;
import com.bonson.qqtapk.view.ui.motion.bean.Table;
import com.bonson.qqtapk.viewmodel.UserViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;


public class MotionViewModel extends UserViewModel {
    private MotionModel motionModel;
    public final ObservableInt type = new ObservableInt(1);
    public final ObservableList<Table> tables = new ObservableArrayList<>();

    public List<Motion> motions = new ArrayList<>();
    public List<Sleep> sleeps = new ArrayList<>();

    @Inject
    public MotionViewModel(Application application, MotionModel motionModel) {
        super(application);
        this.motionModel = motionModel;
    }

    public void motion() {
        type.set(1);
        tables.clear();
        if (!isNetWork()) {
            view.toast("网络不可用");
        }
        if (!motions.isEmpty()) {
            for (Motion motion : motions) {
                tables.add(TablesUtils.motion(motion));
            }
            return;
        }
        view.load();
        Disposable disposable = motionModel.motion(user().getBabyId())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    view.dismiss();
                    view.toast(it.getMsg());
                    if (it.getCode().equals("0")) {
                        motions.clear();
                        motions.addAll(it.getBody());
                        for (Motion motion : motions) {
                            tables.add(TablesUtils.motion(motion));
                        }
                    }
                }, e -> {
                    view.dismiss();
                    e.printStackTrace();
                });
        compositeDisposable.add(disposable);
    }

    public void sleep() {
        type.set(2);
        tables.clear();
        if (!isNetWork()) {
            view.toast("网络不可用");
        }
        if (!sleeps.isEmpty()) {
            for (Sleep motion : sleeps) {
                tables.add(TablesUtils.sleep(motion));
            }
            return;
        }
        Disposable disposable = motionModel.sleep(user().getBabyId())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    view.dismiss();
                    view.toast(it.getMsg());
                    if (it.getCode().equals("0")) {
                        sleeps.clear();
                        sleeps.addAll(it.getBody());
                        for (Sleep sleep : sleeps) {
                            tables.add(TablesUtils.sleep(sleep));
                        }
                    }
                }, e -> {
                    view.dismiss();
                    e.printStackTrace();
                });
        compositeDisposable.add(disposable);
    }
}
