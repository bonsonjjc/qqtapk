package com.bonson.qqtapk.view.ui.setting.password;

import android.app.Application;
import android.databinding.ObservableField;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.resource.activity.BaseView;
import com.bonson.resource.viewmodel.AndroidViewModel;

import javax.inject.Inject;

/**
 * Created by jiangjiancheng on 17/12/31.
 */
@ActivityScope
public class PasswordViewModel extends AndroidViewModel {
    public ObservableField<String> oldPassword = new ObservableField<>("");
    public ObservableField<String> newPassword = new ObservableField<>("");
    public ObservableField<String> surePassword = new ObservableField<>("");

    @Inject
    public PasswordViewModel(Application application) {
        super(application);
    }

    private BaseView view;

    public void setView(BaseView view) {
        this.view = view;
    }

    public void modify() {
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
    }
}
