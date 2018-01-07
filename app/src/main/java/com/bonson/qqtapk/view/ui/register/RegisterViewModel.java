package com.bonson.qqtapk.view.ui.register;

import android.app.Application;
import android.text.TextUtils;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.model.data.user.UserModel;
import com.bonson.resource.activity.BaseView;
import com.bonson.resource.viewmodel.AndroidViewModel;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * Created by zjw on 2017/12/29.
 */
@ActivityScope
public class RegisterViewModel extends AndroidViewModel {
    private String mobile, password, verify;
    private boolean isAgree;
    private BaseView view;
    @Inject
    UserModel userModel;

    @Inject
    public RegisterViewModel(Application application) {
        super(application);
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerify() {
        return verify;
    }

    public void setVerify(String verify) {
        this.verify = verify;
    }

    public boolean isAgree() {
        return isAgree;
    }

    public void setAgree(boolean agree) {
        isAgree = agree;
    }

    public void setView(BaseView view) {
        this.view = view;
    }

    public void register() {
        if (TextUtils.isEmpty(mobile)) {
            view.toast("请输入手机号码");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            view.toast("请输入手机密码");
            return;
        }
        if (TextUtils.isEmpty(verify)) {
            view.toast("请输入手机验证码");
            return;
        }
        if (!isAgree) {
            view.toast("您还未同意用户协议");
            return;
        }
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        Disposable disposable = userModel.register(mobile, password, verify)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    view.toast(it.getMsg());
                    if (it.getCode().equals("0")) {
                        view.start("");
                    }
                }, e -> {
                    view.toast("出错了");
                    e.printStackTrace();
                });
        compositeDisposable.add(disposable);
    }
}
