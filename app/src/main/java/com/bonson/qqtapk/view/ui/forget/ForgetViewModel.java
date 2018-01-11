package com.bonson.qqtapk.view.ui.forget;

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
public class ForgetViewModel extends AndroidViewModel {
    private String mobile, verify;
    private BaseView view;
    @Inject
    UserModel userModel;

    @Inject
    ForgetViewModel(Application application) {
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

    public String getVerify() {
        return verify;
    }

    public void setVerify(String verify) {
        this.verify = verify;
    }

    public void forget() {
        if (TextUtils.isEmpty(mobile)) {
            view.toast("请输入手机号码");
            return;
        }
        if (TextUtils.isEmpty(verify)) {
            view.toast("请输入验证码");
            return;
        }
        Disposable disposable = userModel.forget(mobile, verify)
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
