package com.bonson.fjqqt.view.ui.terminal.alarm;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;

import com.bonson.qqtapk.model.bean.Baby;
import com.bonson.resource.activity.BaseView;
import com.bonson.resource.viewmodel.AndroidViewModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;


public class AlarmViewModel extends AndroidViewModel {
    @Inject
    AlarmModel alarmModel;

    public final List<Alarm> alarms = new ObservableArrayList<>();

    @Inject
    public AlarmViewModel(Application application) {
        super(application);
    }

    private BaseView view;

    public void setView(BaseView view) {
        this.view = view;
    }

    public void alarms() {
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        Disposable disposable = alarmModel.alarms(Baby.baby.getFtmobile())
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
}
