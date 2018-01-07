package com.bonson.qqtapk.view.ui.setting.notify;

import android.app.Application;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.resource.activity.BaseView;
import com.bonson.resource.viewmodel.AndroidViewModel;

import javax.inject.Inject;

/**
 * Created by jiangjiancheng on 17/12/31.
 */
@ActivityScope
public class NotifyViewModel extends AndroidViewModel {
    private BaseView view;
    public ObservableBoolean notify = new ObservableBoolean(false);
    public ObservableBoolean voice = new ObservableBoolean(false);
    public ObservableBoolean vibrate = new ObservableBoolean(false);
    public ObservableField<String> sleepTime = new ObservableField("");

    @Inject
    public NotifyViewModel(Application application) {
        super(application);
    }

    public void setView(BaseView view) {
        this.view = view;
    }

    public void sleepTime() {
        
    }

}
