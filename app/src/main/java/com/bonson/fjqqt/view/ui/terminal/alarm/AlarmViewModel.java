package com.bonson.fjqqt.view.ui.terminal.alarm;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.text.TextUtils;

import com.bonson.fjqqt.view.ui.terminal.alarm.add.AddAlarmViewModel;
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

    public final ObservableList<Alarm> alarms = new ObservableArrayList<>();

    @Inject
    public AlarmViewModel(Application application) {
        super(application);
    }

    private BaseView view;

    public void setView(BaseView view) {
        this.view = view;
    }


    @Inject
    AddAlarmViewModel addAlarmViewModel;

    public AddAlarmViewModel getAddAlarmViewModel() {
        return addAlarmViewModel;
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
                    } else {
                        Alarm alarm = new Alarm();
                        alarm.setFcycle("1!2!3!4!5");
                        alarm.setFtimes("12:33");
                        alarm.setFcontent(AlarmUtils.notifyTypes[3]);
                        alarm.setFstate("1");
                        alarm.setFid("666666");
                        alarms.add(alarm);
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
