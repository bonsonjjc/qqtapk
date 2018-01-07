package com.bonson.qqtapk.view.ui.setting;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.databinding.ObservableField;

import com.bonson.qqtapk.app.Route;
import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.model.bean.User;
import com.bonson.resource.activity.BaseDaggerActivity;
import com.bonson.resource.viewmodel.AndroidViewModel;

import javax.inject.Inject;

/**
 * Created by jiangjiancheng on 17/12/31.
 */
@ActivityScope
public class SettingViewModel extends AndroidViewModel {
    public ObservableField<String> mobile = new ObservableField<>("");

    @Inject
    public SettingViewModel(Application application) {
        super(application);
    }

    public void onItem(int state) {
        Activity activity = BaseDaggerActivity.activityTask.get(0);
        Intent intent = new Intent();
        switch (state) {
            case 1:
                intent.setClassName(activity, Route.password);
                activity.startActivity(intent);
                break;
            case 2:
                intent.setClassName(activity, Route.notify);
                activity.startActivity(intent);
                break;
            case 3:
                break;
            case 4:
                intent.setClassName(activity, Route.about);
                activity.startActivity(intent);
                break;
            case 5:
                break;
            default:
                intent.setClassName(activity, Route.map);
                activity.startActivity(intent);
        }
    }
}
