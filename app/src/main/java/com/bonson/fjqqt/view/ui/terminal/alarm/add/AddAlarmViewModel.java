package com.bonson.fjqqt.view.ui.terminal.alarm.add;

import android.app.Application;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;

import com.bonson.resource.viewmodel.AndroidViewModel;

import javax.inject.Inject;


public class AddAlarmViewModel extends AndroidViewModel {
    public int hour = 0, minute = 0;

    public ObservableInt type = new ObservableInt(1);

    public String

    public ObservableInt notifyType = new ObservableInt(0);

    public ObservableField<String> custom = new ObservableField<>("自定义");

    public final String[] days = {"周一、", "周二、", "周三、", "周四、", "周五、", "周六、", "周日、"};
    public final String[] notifyTypes = new String[]{"闹钟提醒 ", "打针吃药 ", "买菜做饭 ", "接送孩子 ", "电视节目"};

    @Inject
    public AddAlarmViewModel(Application application) {
        super(application);
    }

    public void onSave() {

    }
}
