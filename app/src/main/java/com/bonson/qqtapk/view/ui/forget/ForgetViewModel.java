package com.bonson.qqtapk.view.ui.forget;

import android.app.Application;
import android.databinding.ObservableField;
import android.text.TextUtils;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.model.data.user.UserModel;
import com.bonson.qqtapk.viewmodel.UserViewModel;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * Created by zjw on 2017/12/29.
 */
@ActivityScope
public class ForgetViewModel extends UserViewModel {
    public ObservableField<String> mobile = new ObservableField<>("");
    public ObservableField<String> verify = new ObservableField<>("");
    @Inject
    UserModel userModel;

    @Inject
    ResetViewModel viewModel;

    @Inject
    public ForgetViewModel(Application application) {
        super(application);
    }

    public void forget() {
        if (TextUtils.isEmpty(mobile.get())) {
            view.toast("请输入手机号码");
            return;
        }
        if (TextUtils.isEmpty(verify.get())) {
            view.toast("请输入验证码");
            return;
        }
        viewModel.mobile.set(mobile.get());
        Disposable disposable = userModel.forget(mobile.get(), verify.get())
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

    public void reset() {
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        view.load();
        Disposable disposable = userModel.resetPassword(mobile.get(), viewModel.password.get())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    view.dismiss();
                    view.toast(it.getMsg());
                    if (it.getCode().equals("0")) {
                        view.back();
                    }
                }, e -> {
                    view.dismiss();
                    view.toast("出错了");
                    e.printStackTrace();
                });
        compositeDisposable.add(disposable);
    }
}
