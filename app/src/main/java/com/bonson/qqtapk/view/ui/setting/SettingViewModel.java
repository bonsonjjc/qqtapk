package com.bonson.qqtapk.view.ui.setting;

import android.app.Application;
import android.databinding.ObservableField;

import com.bonson.qqtapk.app.Route;
import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.model.bean.Baby;
import com.bonson.qqtapk.model.bean.User;
import com.bonson.qqtapk.model.data.setting.SettingModel;
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

    private UserModel userModel;

    @Inject
    SettingModel settingModel;

    @Inject
    public SettingViewModel(Application application, UserModel userModel) {
        super(application);
        this.userModel = userModel;
        mobile.set(User.user.getMobile());
    }

    public void serverToken() {
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        view.load();
        Disposable disposable = settingModel.token(Baby.baby)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    if (it.getCode().equals("0")) {

                    } else {
                        view.toast(it.getMsg());
                    }
                }, e -> {
                    view.toast("出错了");
                    view.dismiss();
                });
        compositeDisposable.add(disposable);
    }

    public void exit() {
        Disposable disposable = userModel.exit(User.user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    view.start(Route.login);
                    ActivityUtils.clear();
                }, e -> {
                    view.toast("退出失败");
                });
        compositeDisposable.add(disposable);
    }
}
