package com.bonson.fjqqt.view.ui.terminal.timer;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.bonson.qqtapk.BR;
import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.model.bean.Baby;
import com.bonson.resource.activity.BaseView;
import com.bonson.resource.viewmodel.AndroidViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

@ActivityScope
public class TimerViewModel extends AndroidViewModel {
    @Inject
    TimerModel timerModel;

    public final ObservableList<Timer> timers = new ObservableArrayList<>();

    @Inject
    public TimerViewModel(Application application) {
        super(application);
    }

    public void timers() {
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        Disposable disposable = timerModel.timers(Baby.baby.getFtmobile())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    view.toast(it.getMsg());
                    if (it.getCode().equals("0")) {
                        timers.clear();
                        timers.addAll(it.getBody());
                    } else {
                        fill();
                    }
                }, e -> {
                    view.toast("出错了");
                });
        compositeDisposable.add(disposable);
    }

    private void fill() {
        for (int i = 0; i < 6; i++) {
            Timer timer = new Timer();
            timer.setFtimes("00:00");
            timers.add(timer);
        }
    }

    public void update() {
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        StringBuilder fid = new StringBuilder();
        StringBuilder times = new StringBuilder();
        for (int i = 0; i < timers.size(); i++) {
            Timer timer = timers.get(i);
            fid.append(timer.getFid());
            times.append(timer.getFtimes());
            if (i != timers.size() - 1) {
                fid.append(",");
                times.append(";");
            }
        }
        view.load();
        Disposable disposable = timerModel.update(Baby.baby.getFtmobile(), fid.toString(), times.toString(), "1")
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
