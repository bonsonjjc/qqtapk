package com.bonson.qqtapk.view.ui.scan;

import android.app.Application;
import android.databinding.Bindable;

import com.bonson.qqtapk.BR;
import com.bonson.qqtapk.model.bean.Baby;
import com.bonson.qqtapk.model.bean.User;
import com.bonson.qqtapk.model.data.baby.BabyModel;
import com.bonson.qqtapk.model.data.user.UserModel;
import com.bonson.resource.activity.BaseView;
import com.bonson.resource.viewmodel.AndroidViewModel;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class ScanViewModel extends AndroidViewModel {
    private BabyModel babyModel;
    @Bindable
    String imei;
    @Inject
    UserModel userModel;
    private User user;

    private BaseView view;

    @Inject
    public ScanViewModel(Application application, BabyModel babyModel) {
        super(application);
        this.babyModel = babyModel;
    }

    public void setImei(String imei) {
        this.imei = imei;
        notifyPropertyChanged(BR.imei);
    }

    public void setView(BaseView view) {
        this.view = view;
    }

    public void init() {
        user = User.user;
        if (user == null) {
            user = userModel.getUser();
            if (user != null) {
                User.user = user;
            }
        }
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public void add() {
        view.load();
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        Disposable disposable = babyModel.bind(user.getUserId(), imei)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    view.dismiss();
                    view.toast(it.getMsg());
                    if (it.getCode().equals("0")) {

                    }
                }, e -> {
                    view.dismiss();
                    e.printStackTrace();
                });
        compositeDisposable.add(disposable);
    }

    public void change() {
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        view.load();
        Baby baby = Baby.baby.clone();
        baby.setFimei(imei);
        Disposable disposable = babyModel.changeIMEI(baby)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    view.dismiss();
                    view.toast(it.getMsg());
                    if (it.getCode().equals("0")) {
                        Baby.baby = baby;
                    }
                }, e -> {
                    view.dismiss();
                    e.printStackTrace();
                });
        compositeDisposable.add(disposable);
    }
}
