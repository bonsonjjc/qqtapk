package com.bonson.qqtapk.view.ui.mode;

import android.app.Application;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableInt;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.model.bean.Baby;
import com.bonson.qqtapk.model.bean.Mode;
import com.bonson.qqtapk.model.data.mode.ModeModel;
import com.bonson.library.utils.NumberUtils;
import com.bonson.qqtapk.view.ui.info.select.SelectViewModel;
import com.bonson.qqtapk.viewmodel.UserViewModel;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * Created by jiangjiancheng on 17/12/31.
 */
@ActivityScope
public class ModeViewModel extends UserViewModel {
    public final ObservableBoolean powerSave = new ObservableBoolean();

    public final ObservableBoolean safeMode = new ObservableBoolean();
    public final ObservableBoolean powerMode = new ObservableBoolean();
    public final ObservableBoolean customMode = new ObservableBoolean();

    public final ObservableBoolean blendMode = new ObservableBoolean();
    public final ObservableBoolean normalMode = new ObservableBoolean();
    public final ObservableBoolean accurateMode = new ObservableBoolean();

    public final ObservableInt interval = new ObservableInt();

    private ModeModel modeModel;

    @Inject
    SelectViewModel viewModel;

    @Inject
    public ModeViewModel(Application application, ModeModel modeModel) {
        super(application);
        this.modeModel = modeModel;
    }

    public void mode() {
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        Disposable disposable = modeModel.model(user().getBabyId())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    view.toast(it.getMsg());
                    Mode mode = it.getBody();
                    powerSave.set("1".equals(mode.getFsavepower()));
                    locMode(mode.getFlmode());
                    locType(mode.getFloctype());
                    interval.set(NumberUtils.parseInt(mode.getFlocation()));
                }, e -> {
                    view.toast("出错了");
                });
        compositeDisposable.add(disposable);
    }

    public void locMode(String mode) {
        switch (mode) {
            case "1":
                safeMode.set(true);
                powerMode.set(false);
                customMode.set(false);
                interval.set(300);
                locType("1");
                break;
            case "2":
                safeMode.set(false);
                powerMode.set(true);
                customMode.set(false);
                interval.set(600);
                locType("2");
                break;
            case "3":
                safeMode.set(false);
                powerMode.set(false);
                customMode.set(true);
                break;
        }
    }

    public void locType(String type) {
        switch (type) {
            case "1":
                blendMode.set(true);
                normalMode.set(false);
                accurateMode.set(false);
                break;
            case "2":
                blendMode.set(false);
                normalMode.set(true);
                accurateMode.set(false);
                break;
            case "3":
                blendMode.set(false);
                normalMode.set(false);
                accurateMode.set(true);
                break;
        }
    }

    public void update() {
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        view.load();
        Mode mode = new Mode();
        mode.setFsavepower(powerSave.get() ? "1" : "0");
        mode.setBid(user().getBabyId());
        mode.setFlocation(interval.get() + "");
        mode.setFlmode(safeMode.get() ? "1" : powerMode.get() ? "2" : customMode.get() ? "3" : "1");
        mode.setFloctype(blendMode.get() ? "1" : normalMode.get() ? "2" : accurateMode.get() ? "3" : "1");
        Disposable disposable = modeModel.update(mode)
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
