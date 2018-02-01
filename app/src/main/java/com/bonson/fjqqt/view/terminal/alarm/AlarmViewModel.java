package com.bonson.fjqqt.view.terminal.alarm;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.bonson.fjqqt.view.terminal.alarm.add.AddAlarmViewModel;
import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.model.bean.Baby;
import com.bonson.qqtapk.viewmodel.UserViewModel;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

@ActivityScope
public class AlarmViewModel extends UserViewModel {
    public final ObservableList<Alarm> alarms = new ObservableArrayList<>();
    @Inject
    AlarmModel alarmModel;
    @Inject
    AddAlarmViewModel addAlarmViewModel;

    @Inject
    public AlarmViewModel(Application application) {
        super(application);
    }

    public AddAlarmViewModel getAddAlarmViewModel() {
        return addAlarmViewModel;
    }

    public void alarms() {
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        Disposable disposable = alarmModel.alarms(baby().getFtmobile())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    view.toast(it.getMsg());
                    if (it.getCode().equals("0")) {
                        alarms.clear();
                        alarms.addAll(it.getBody());
                    }
                }, e -> {
                    view.toast("出错了");
                });
        compositeDisposable.add(disposable);
    }

    public void alarm(int position, Alarm alarm) {
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        view.load();
        Disposable disposable = alarmModel.update(alarm.getFtype(), alarm)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    view.toast(it.getMsg());
                    view.dismiss();
                    if (it.getCode().equals("0")) {
                        switch (alarm.getFtype()) {
                            case "1":
                                alarms.add(it.getBody());
                                break;
                            case "2":
                                alarms.set(position, it.getBody());
                                break;
                            default:
                                alarms.remove(position);
                        }
                    }
                }, e -> {
                    view.dismiss();
                    view.toast("出错了");
                });
        compositeDisposable.add(disposable);
    }
}
