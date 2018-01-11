package com.bonson.qqtapk.view.ui.setting.password;

import android.app.Application;
import android.databinding.ObservableField;
import android.text.TextUtils;

import com.bonson.qqtapk.app.Route;
import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.model.data.user.UserModel;
import com.bonson.resource.activity.ActivityUtils;
import com.bonson.resource.activity.BaseView;
import com.bonson.resource.viewmodel.AndroidViewModel;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * Created by jiangjiancheng on 17/12/31.
 */
@ActivityScope
public class PasswordViewModel extends AndroidViewModel {
    public ObservableField<String> oldPassword = new ObservableField<>("");
    public ObservableField<String> newPassword = new ObservableField<>("");
    public ObservableField<String> surePassword = new ObservableField<>("");


    private BaseView view;
    private UserModel userModel;

    @Inject
    public PasswordViewModel(Application application, UserModel userModel) {
        super(application);
        this.userModel = userModel;
    }

    public void setView(BaseView view) {
        this.view = view;
    }

    public void modify() {
        String password = oldPassword.get();
        String newPwd = newPassword.get();
        if (TextUtils.isEmpty(password)) {
            view.toast("请输入旧密码");
            return;
        }
        if (password.matches("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$")) {
            view.toast("密码必须为(8到16位的数字+字母的组合)");
            return;
        }
        if (TextUtils.isEmpty(newPwd)) {
            view.toast("请输入新密码");
            return;
        }
        if (newPwd.matches("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$")) {
            view.toast("密码必须为(8到16位的数字+字母的组合)");
            return;
        }
        if (!TextUtils.equals(newPwd, surePassword.get())) {
            view.toast("两次密码不一致");
            return;
        }
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        Disposable disposable = userModel.password(oldPassword.get(), newPassword.get())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    view.toast(it.getMsg());
                    if (it.getCode().equals("0")) {
                        view.start(Route.login);
                        ActivityUtils.clear();
                    }
                }, e -> {
                    view.toast("出错了");
                });
        compositeDisposable.add(disposable);
    }
}
