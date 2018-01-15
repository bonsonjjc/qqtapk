package com.bonson.qqtapk.view.ui.mode;

import android.app.Application;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableInt;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.model.bean.Baby;
import com.bonson.qqtapk.model.bean.Device;
import com.bonson.qqtapk.model.bean.Mode;
import com.bonson.qqtapk.model.data.mode.ModeModel;
import com.bonson.library.utils.NumberUtils;
import com.bonson.resource.activity.BaseView;
import com.bonson.resource.viewmodel.AndroidViewModel;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * Created by jiangjiancheng on 17/12/31.
 */
@ActivityScope
public class ModeViewModel extends AndroidViewModel {
    public ObservableBoolean powerSave = new ObservableBoolean();

    public ObservableBoolean safeMode = new ObservableBoolean();
    public ObservableBoolean powerMode = new ObservableBoolean();
    public ObservableBoolean customMode = new ObservableBoolean();

    public ObservableBoolean blendMode = new ObservableBoolean();
    public ObservableBoolean normalMode = new ObservableBoolean();
    public ObservableBoolean accurateMode = new ObservableBoolean();

    public ObservableInt interval = new ObservableInt();

    private ModeModel modeModel;

    private BaseView view;

    @Inject
    ModeViewModel(Application application, ModeModel modeModel) {
        super(application);
        this.modeModel = modeModel;
    }

    public void setView(BaseView view) {
        this.view = view;
    }

    public void mode() {
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        Disposable disposable = modeModel.model(Baby.baby.getFid())
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
        mode.setBid(Baby.baby.getFid());
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
