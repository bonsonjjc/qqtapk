package com.bonson.qqtapk.view.ui.forget;

import android.app.Application;
import android.text.TextUtils;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.model.data.user.UserModel;
import com.bonson.resource.activity.BaseView;
import com.bonson.resource.viewmodel.AndroidViewModel;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by zjw on 2018/1/3.
 */
@ActivityScope
public class ResetViewModel extends AndroidViewModel {
    @Inject
    UserModel userModel;
    BaseView view;
    private String mobile, password, newPassword;

    @Inject
    ResetViewModel(Application application) {
        super(application);
    }

    public void setView(BaseView view) {
        this.view = view;
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

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public void reset() {
        if (TextUtils.isEmpty(mobile)) {
            view.toast("请输入手机号码");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            view.toast("请输入新密码");
            return;
        }
        if (TextUtils.isEmpty(newPassword)) {
            view.toast("再次输入新密码");
            return;
        }
        if (!password.equals(newPassword)) {
            view.toast("两次密码不一至");
            return;
        }
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        userModel.resetPassword(mobile, password)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    view.toast(it.getMsg());
                    if (it.getCode().equals("0")) {
                        view.start("finish");
                    }
                }, e -> {
                    view.toast("出错了");
                    e.printStackTrace();
                });
    }
}
