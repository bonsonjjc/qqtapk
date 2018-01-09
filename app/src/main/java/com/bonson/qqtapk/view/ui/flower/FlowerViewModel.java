package com.bonson.qqtapk.view.ui.flower;

import android.app.Application;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.text.TextUtils;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.model.bean.Baby;
import com.bonson.qqtapk.model.bean.Flower;
import com.bonson.qqtapk.model.data.flower.FlowerModel;
import com.bonson.resource.activity.BaseView;
import com.bonson.resource.viewmodel.AndroidViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * Created by jiangjiancheng on 17/12/31.
 */
@ActivityScope
public class FlowerViewModel extends AndroidViewModel {
    private List<Flower> flowers = new ArrayList<>();

    public ObservableInt flowerCount = new ObservableInt(0);

    public ObservableField<String> desc = new ObservableField<>("");
    public ObservableField<String> count = new ObservableField<>("");

    private FlowerModel flowerModel;

    private BaseView view;

    @Inject
    FlowerViewModel(Application application, FlowerModel flowerModel) {
        super(application);
        this.flowerModel = flowerModel;
    }

    public List<Flower> getFlowers() {
        return flowers;
    }

    public void setFlowers(List<Flower> flowers) {
        this.flowers = flowers;
        notifyChange();
    }

    public void setView(BaseView view) {
        this.view = view;
    }

    public void flowers(int start, int pagerSize) {
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        Disposable disposable = flowerModel.flowers(Baby.baby.getFid(), start, pagerSize)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    view.toast(it.getMsg());
                    if (it.getCode().equals("0")) {
                        setFlowers(it.getBody());
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
        Disposable disposable =
                flowerModel.pull(flower).observeOn(AndroidSchedulers.mainThread()).subscribe(it -> {
                    view.toast(it.getMsg());
                    if (it.getCode().equals("0")) {
                        view.toast("奖励" + it.getMsg());
                        flowers.add(flower);
                        notifyChange();
                    }
                }, e -> {
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
        flower.setFid(Baby.baby.getFid());
        return flower;
    }


    public void onPunishment() {
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        if (validate()) {
            return;
        }
        Flower flower = create("2");
        Disposable disposable =
                flowerModel.pull(flower).observeOn(AndroidSchedulers.mainThread()).subscribe(it -> {
                    view.toast(it.getMsg());
                    if (it.getCode().equals("0")) {
                        flowers.add(flower);
                        view.toast("惩罚" + it.getMsg());
                        notifyChange();
                    }
                }, e -> {
                    view.toast("出错了");
                });
        compositeDisposable.add(disposable);
    }
}
