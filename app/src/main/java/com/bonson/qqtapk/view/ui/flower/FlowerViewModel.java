package com.bonson.qqtapk.view.ui.flower;

import android.app.Application;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;

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

    public ObservableInt flowerCount = new ObservableInt();

    public ObservableField<String> desc = new ObservableField<>();
    public ObservableField<String> count = new ObservableField<>();

    private FlowerModel flowerModel;

    private BaseView view;

    @Inject
    public FlowerViewModel(Application application, FlowerModel flowerModel) {
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
        Flower flower = new Flower();
        flower.setFctime("2017-02-20");
        flower.setFnum(count.get());
        flower.setFdesc(desc.get());
        flower.setFtype("1");
        Disposable disposable = flowerModel.pull(flower)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    view.toast(it.getMsg());
                    if (it.getCode().equals("0")) {
                        view.toast("奖励");
                        flowers.add(flower);
                        notifyChange();
                    }
                }, e -> {
                    view.toast("出错了");
                });
        compositeDisposable.add(disposable);
    }

    public void onPunishment() {
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        Flower flower = new Flower();
        flower.setFctime("2017-02-20");
        flower.setFnum(count.get());
        flower.setFdesc(desc.get());
        flower.setFtype("2");
        Disposable disposable = flowerModel.pull(flower)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    view.toast(it.getMsg());
                    if (it.getCode().equals("0")) {
                        flowers.add(flower);
                        view.toast("惩罚");
                        notifyChange();
                    }
                }, e -> {
                    view.toast("出错了");
                });
        compositeDisposable.add(disposable);
    }

}
