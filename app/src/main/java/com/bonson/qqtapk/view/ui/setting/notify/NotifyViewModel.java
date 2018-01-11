package com.bonson.qqtapk.view.ui.setting.notify;

import android.app.Application;
import android.databinding.Observable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import com.bonson.library.utils.PreferencesHelper;
import com.bonson.qqtapk.app.Const;
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
    public ObservableBoolean notify = new ObservableBoolean();
    public ObservableBoolean voice = new ObservableBoolean();
    public ObservableBoolean vibrate = new ObservableBoolean();
    public ObservableField<String> sleepTime = new ObservableField<>("");

    @Inject
    PreferencesHelper helper;

    @Inject
    public NotifyViewModel(Application application) {
        super(application);
    }

    public void init() {
        notify.set(helper.get(Const.NOTIFY_KEY, true));
        voice.set(helper.get(Const.VOICE_KEY, true));
        vibrate.set(helper.get(Const.VIBRATE_KEY, true));

        OnPropertyChangedCallback changedCallback = new OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                helper.put(Const.NOTIFY_KEY, notify.get());
                helper.put(Const.VOICE_KEY, voice.get());
                helper.put(Const.VIBRATE_KEY, vibrate.get());
                helper.commit();
            }
        };
        notify.addOnPropertyChangedCallback(changedCallback);
        voice.addOnPropertyChangedCallback(changedCallback);
        vibrate.addOnPropertyChangedCallback(changedCallback);
    }

    public void setHelper(PreferencesHelper helper) {
        this.helper = helper;
    }

    public void setView(BaseView view) {
        this.view = view;
    }

    public void sleepTime() {

    }

}
