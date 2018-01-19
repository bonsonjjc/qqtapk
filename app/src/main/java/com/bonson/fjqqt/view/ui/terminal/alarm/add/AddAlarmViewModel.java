package com.bonson.fjqqt.view.ui.terminal.alarm.add;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ObservableList;

import com.bonson.fjqqt.view.ui.terminal.alarm.Alarm;
import com.bonson.fjqqt.view.ui.terminal.alarm.AlarmModel;
import com.bonson.fjqqt.view.ui.terminal.alarm.AlarmUtils;
import com.bonson.library.utils.NumberUtils;
import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.model.bean.Baby;
import com.bonson.qqtapk.view.ui.info.select.Select;
import com.bonson.resource.viewmodel.AndroidViewModel;

import javax.inject.Inject;

@ActivityScope
public class AddAlarmViewModel extends AndroidViewModel {
    public ObservableInt hour = new ObservableInt(0);
    public ObservableInt minute = new ObservableInt(0);

    public ObservableInt cycleType = new ObservableInt(1);

    public ObservableField<String> custom = new ObservableField<>("自定义");
    private String cycle = "0";

    public ObservableField<String> notifyType = new ObservableField<>("闹钟提醒");

    private Alarm alarm;

    @Inject
    AlarmModel alarmModel;

    @Inject
    public AddAlarmViewModel(Application application) {
        super(application);
    }

    public void setAlarm(Alarm alarm) {
        if (alarm == null) {
            alarm = AlarmUtils.newAlarm();
        }
        this.alarm = alarm;
        cycleType.set(AlarmUtils.getType(alarm.getFcycle()));
        int[] time = AlarmUtils.parseTime(alarm.getFtimes());
        hour.set(time[0]);
        minute.set(time[1]);
        notifyType.set(alarm.getFcontent());
    }

    public void setCycle(int type, String cycle) {
        cycleType.set(type);
        this.cycle = cycle;
    }

    public ObservableList<Select> daysList() {
        ObservableList<Select> selects = new ObservableArrayList<>();
        String[] split = alarm.getFcycle().split("!");
        for (int i = 1; i <= AlarmUtils.days.length; i++) {
            boolean check = false;
            for (String day : split) {
                if (day.equals(i)) {
                    check = true;
                    break;
                }
            }
            selects.add(new Select(AlarmUtils.days[i - 1], check, i + ""));
        }
        return selects;
    }

    public ObservableList<Select> selectNotifyTypes() {
        ObservableList<Select> selects = new ObservableArrayList<>();
        for (int i = 1; i < AlarmUtils.notifyTypes.length; i++) {
            selects.add(new Select(AlarmUtils.notifyTypes[i - 1], notifyType.get().equals(AlarmUtils.notifyTypes[i - 1]), i + ""));
        }
        return selects;
    }

    public Alarm getAlarm() {
        alarm.setFcycle(cycle);
        alarm.setFcontent(notifyType.get());
        alarm.setFtmobile(Baby.baby.getFtmobile());
        alarm.setFtimes(String.format("%02d:%02d", hour.get(), minute.get()));
        return alarm;
    }

}
