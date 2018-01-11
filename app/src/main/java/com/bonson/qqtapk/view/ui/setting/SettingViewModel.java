package com.bonson.qqtapk.view.ui.setting;

import android.app.Application;
import android.databinding.ObservableField;

import com.bonson.qqtapk.app.Route;
import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.model.bean.User;
import com.bonson.qqtapk.model.data.user.UserModel;
import com.bonson.resource.activity.ActivityUtils;
import com.bonson.resource.activity.BaseView;
import com.bonson.resource.viewmodel.AndroidViewModel;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by jiangjiancheng on 17/12/31.
 */
@ActivityScope
public class SettingViewModel extends AndroidViewModel {
    public ObservableField<String> mobile = new ObservableField<>("");

    private BaseView view;

    private UserModel userModel;

    @Inject
    public SettingViewModel(Application application, UserModel userModel) {
        super(application);
        this.userModel = userModel;
        mobile.set(User.user.getMobile());
    }

    public void setView(BaseView view) {
        this.view = view;
    }

    public void exit() {
        Disposable disposable = userModel.exit(User.user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    view.start(Route.login);
                    ActivityUtils.clear();
                }, e -> {
                    view.toast("失败");
                });
        compositeDisposable.add(disposable);
    }
}
