package com.bonson.qqtapk.view.ui.login;

import android.app.Application;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.text.TextUtils;

import com.bonson.qqtapk.app.Route;
import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.model.data.user.UserModel;
import com.bonson.library.utils.Md5Utils;
import com.bonson.resource.activity.BaseView;
import com.bonson.resource.viewmodel.AndroidViewModel;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by zjw on 2017/12/29.
 */
@ActivityScope
public class LoginViewModel extends AndroidViewModel {
    public ObservableField<String> mobile = new ObservableField<>(), password = new ObservableField<>();
    private String token;
    public ObservableBoolean enable = new ObservableBoolean(true);
    public ObservableBoolean auto = new ObservableBoolean();
    private BaseView view;

    private UserModel userModel;

    @Inject
    LoginViewModel(Application application, UserModel userModel) {
        super(application);
        this.userModel = userModel;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setView(BaseView view) {
        this.view = view;
    }

    public void login() {
        if (TextUtils.isEmpty(mobile.get())) {
            view.toast("请输入账号");
            return;
        } else if (TextUtils.isEmpty(password.get())) {
            view.toast("请输入密码");
            return;
        }
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        view.load();
        enable.set(false);
        String pwd = password.get().length() == 32 ? password.get() : Md5Utils.toMD5(password.get());
        Disposable disposable = userModel.login(mobile.get(), pwd, token, auto.get())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    if ("0".equals(result.getCode())) {
                        view.start(Route.index);
                        view.back();
                    } else {
                        view.toast(result.getMsg());
                    }
                    enable.set(true);
                }, e -> {
                    enable.set(true);
                    view.toast("出错了");
                    e.printStackTrace();
                });
        compositeDisposable.add(disposable);
    }

    void init() {
        Disposable disposable = userModel.getUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(user -> {
                    if (user == null) return;
                    mobile.set(user.getMobile());
                    auto.set(user.getAuto());
                    password.set(auto.get() ? user.getPassword() : "");
                    token = user.getToken();
                }, Throwable::printStackTrace);
        compositeDisposable.add(disposable);
    }
}
