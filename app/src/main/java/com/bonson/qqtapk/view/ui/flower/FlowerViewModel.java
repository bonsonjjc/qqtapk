package com.bonson.qqtapk.view.ui.flower;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ObservableList;
import android.text.TextUtils;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.model.bean.Baby;
import com.bonson.qqtapk.model.bean.Flower;
import com.bonson.qqtapk.model.data.flower.FlowerModel;
import com.bonson.qqtapk.viewmodel.UserViewModel;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * Created by jiangjiancheng on 17/12/31.
 */
@ActivityScope
public class FlowerViewModel extends UserViewModel {
    public final ObservableList<Flower> flowers = new ObservableArrayList<>();

    public final ObservableInt flowerCount = new ObservableInt(0);

    public final ObservableField<String> desc = new ObservableField<>("");
    public final ObservableField<String> count = new ObservableField<>("");

    private FlowerModel flowerModel;

    @Inject
    public FlowerViewModel(Application application, FlowerModel flowerModel) {
        super(application);
        this.flowerModel = flowerModel;
    }

    public void flowers(int start, int pagerSize) {
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        Disposable disposable = flowerModel.flowers(user().getBabyId(), start, pagerSize)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    view.toast(it.getMsg());
                    if (it.getCode().equals("0")) {
                        flowers.addAll(it.getBody());
                        notifyChange();
                    }
                }, e -> {
                    view.toast("出错了");
                });
        compositeDisposable.add(disposable);
    }

    public void onAward() {
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        if (validate()) {
            return;
        }
        Flower flower = create("1");
        view.load();
        Disposable disposable = flowerModel.pull(flower)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    view.dismiss();
                    view.toast(it.getMsg());
                    if (it.getCode().equals("0")) {
                        view.toast("奖励" + it.getMsg());
                        flowers.add(flower);
                    }
                }, e -> {
                    view.dismiss();
                    view.toast("出错了");
                });
        compositeDisposable.add(disposable);
    }

    private boolean validate() {
        if (TextUtils.isEmpty(count.get())) {
            view.toast("请输入朵数");
            return true;
        }
        if (TextUtils.isEmpty(count.get())) {
            view.toast("请输入说明");
            return true;
        }
        return false;
    }

    private Flower create(String type) {
        Flower flower = new Flower();
        flower.setFctime("2017-02-20");
        flower.setFnum(count.get());
        flower.setFdesc(desc.get());
        flower.setFtype(type);
        flower.setFid(user().getBabyId());
        return flower;
    }


    public void onPunishment() {
        if (validate()) {
            return;
        }
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        Flower flower = create("2");
        view.load();
        Disposable disposable = flowerModel.pull(flower)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    view.dismiss();
                    view.toast(it.getMsg());
                    if (it.getCode().equals("0")) {
                        flowers.add(flower);
                        view.toast("惩罚" + it.getMsg());
                    }
                }, e -> {
                    view.dismiss();
                    view.toast("出错了");
                });
        compositeDisposable.add(disposable);
    }
}
