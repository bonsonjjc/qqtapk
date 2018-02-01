package com.bonson.qqtapk.view.ui.register;

import android.app.Application;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.text.TextUtils;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.model.data.user.UserModel;
import com.bonson.qqtapk.viewmodel.UserViewModel;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * Created by zjw on 2018/1/3.
 */
@ActivityScope
public class VerifyViewModel extends UserViewModel {

    public final ObservableField<String> verifyText = new ObservableField<>();
    public final ObservableBoolean verifyEnable = new ObservableBoolean();
    @Inject
    UserModel userModel;

    @Inject
    public VerifyViewModel(Application application) {
        super(application);
    }

    public void verify(String mobile, int type) {
        if (TextUtils.isEmpty(mobile)) {
            view.toast("请输入手机号码");
            return;
        }
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        view.load();
        Disposable disposable = userModel.verify(mobile, type + "")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    view.dismiss();
                    view.toast(it.getMsg());
                    if (it.getCode().equals("0")) {
                        shutdown(90);
                    }
                }, e -> {
                    view.dismiss();
                    view.toast("出错了");
                    e.printStackTrace();
                });
        compositeDisposable.add(disposable);
    }

    private void shutdown(int totalSecond) {
        Disposable disposable = Observable.interval(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .take(totalSecond)
                .subscribe(second -> {
                    verifyEnable.set(false);
                    verifyText.set(totalSecond - second + "秒");
                    System.out.println(totalSecond - second + "秒");
                    if (second == totalSecond - 1) {
                        verifyEnable.set(true);
                        verifyText.set("获取");
                    }
                }, e -> {
                    e.printStackTrace();
                });
        compositeDisposable.add(disposable);
    }
}
