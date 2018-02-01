package com.bonson.qqtapk.view.ui.login;

import android.app.Application;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.text.TextUtils;

import com.bonson.library.utils.security.Md5Utils;
import com.bonson.qqtapk.app.Route;
import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.model.bean.User;
import com.bonson.qqtapk.viewmodel.UserViewModel;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * Created by zjw on 2017/12/29.
 */
@ActivityScope
public class LoginViewModel extends UserViewModel {
    public final ObservableField<String> mobile = new ObservableField<>("");
    public final ObservableField<String> password = new ObservableField<>("");
    public final ObservableBoolean enable = new ObservableBoolean(true);
    public final ObservableBoolean auto = new ObservableBoolean(false);
    public String token = "";
    private User user;
    private LoginServer loginServer;

    @Inject
    public LoginViewModel(Application application, LoginServer loginServer) {
        super(application);
        this.loginServer = loginServer;
    }

    public void onCreate() {
        user=user();
        if (user== null) {
            user = new User();
            user.setMobile("");
            user.setPassword("");
            user.setAuto(false);
            user.setToken("");
        }
        token = user.getToken();
        mobile.set(user.getMobile());
        password.set(user.getAuto() ? user.getPassword() : "");
        auto.set(user.getAuto());
    }

    public void login() {
        if (TextUtils.isEmpty(mobile.get())) {
            view.toast("请输入账号");
            return;
        }
        if (TextUtils.isEmpty(password.get())) {
            view.toast("请输入密码");
            return;
        }
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        view.load();
        enable.set(false);
        String pwd = user.getPassword().length() == 32 ? password.get() : Md5Utils.toMD5(password.get());
        user.setPassword(pwd);
        user.setMobile(mobile.get());
        user.setAuto(auto.get());
        user.setToken(token);
        Disposable disposable = loginServer.login(user)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    view.dismiss();
                    if ("0".equals(result.getCode())) {
                        view.start(Route.index);
                        view.back();
                    } else {
                        view.toast(result.getMsg());
                    }
                    enable.set(true);
                }, e -> {
                    view.dismiss();
                    enable.set(true);
                    view.toast("出错了");
                    e.printStackTrace();
                });
        compositeDisposable.add(disposable);
    }
}
