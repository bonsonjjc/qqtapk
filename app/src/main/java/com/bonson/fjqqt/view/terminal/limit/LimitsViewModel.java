package com.bonson.fjqqt.view.terminal.limit;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableList;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.model.bean.Baby;
import com.bonson.qqtapk.model.bean.Device;
import com.bonson.qqtapk.model.bean.Limit;
import com.bonson.qqtapk.view.ui.limits.add.LimitViewModel;
import com.bonson.qqtapk.viewmodel.UserViewModel;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * Created by jiangjiancheng on 17/12/31.
 */
@ActivityScope
public class LimitsViewModel extends UserViewModel {
    private LimitModel limitModel;
    public final ObservableBoolean open = new ObservableBoolean(false);
    public final ObservableList<Limit> limits = new ObservableArrayList<>();

    private LimitViewModel viewModel;

    @Inject
    public LimitsViewModel(Application application, LimitModel limitModel, LimitViewModel viewModel) {
        super(application);
        this.viewModel = viewModel;
        this.limitModel = limitModel;
        open.set("0".equals(Device.device.getFcstate()));
    }

    public void limits() {
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        Disposable disposable = limitModel.limits(baby().getFid())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    view.toast(it.getMsg());
                    if (it.getCode().equals("0")) {
                        limits.addAll(it.getBody());
                    }
                }, e -> {
                    view.toast("出错了");
                });
        compositeDisposable.add(disposable);
    }

    public void add(Limit limit) {
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        view.load();
    }

    public void update(int position, Limit limit) {
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        view.load();
    }

    public com.bonson.qqtapk.view.ui.limits.add.LimitViewModel addViewModel() {
        viewModel.title.set("添加呼入限制");
        Limit limit = new Limit();
        limit.setFcstate("1");
        limit.setFbid(baby().getFid());
        viewModel.setLimit(limit);
        viewModel.setOnSaveListener(() -> {
            add(limit);
        });
        return viewModel;
    }

    public com.bonson.qqtapk.view.ui.limits.add.LimitViewModel updateViewModel(int position) {
        Limit limit = limits.get(position);
        limit.setFbid(baby().getFid());
        viewModel.title.set("修改呼入限制");
        viewModel.setLimit(limit);
        viewModel.setOnDeleteListener(() -> {
            delete(position);
        });
        viewModel.setOnSaveListener(() -> {
            update(position, viewModel.getLimit());
        });
        return viewModel;
    }

    public void delete(int position) {
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        view.load();
    }

    public void updateState() {
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        view.load();
    }

}
