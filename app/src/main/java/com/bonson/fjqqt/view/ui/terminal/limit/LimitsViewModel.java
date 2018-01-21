package com.bonson.fjqqt.view.ui.terminal.limit;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;

import com.bonson.qqtapk.model.bean.Baby;
import com.bonson.qqtapk.model.bean.Device;
import com.bonson.qqtapk.model.bean.Limit;
import com.bonson.qqtapk.model.data.limit.LimitModel;
import com.bonson.qqtapk.view.ui.limits.add.LimitViewModel;
import com.bonson.resource.activity.BaseView;
import com.bonson.resource.viewmodel.AndroidViewModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * Created by jiangjiancheng on 17/12/31.
 */
public class LimitsViewModel extends AndroidViewModel {
    private BaseView view;

    private LimitModel limitModel;
    public ObservableBoolean open = new ObservableBoolean(false);
    public List<Limit> limits = new ObservableArrayList<>();

    private LimitViewModel viewModel;

    @Inject
    public LimitsViewModel(Application application, LimitModel limitModel, LimitViewModel viewModel) {
        super(application);
        this.viewModel = viewModel;
        this.limitModel = limitModel;
        open.set("0".equals(Device.device.getFcstate()));
    }

    public void setView(BaseView view) {
        this.view = view;
        viewModel.setView(view);
    }

    public void limits() {
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        Disposable disposable = limitModel.limits(Baby.baby.getFid())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    view.toast(it.getMsg());
                    if (it.getCode().equals("0")) {
                        limits.addAll(it.getBody());
                        notifyChange();
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
        Disposable disposable = limitModel.add(limit)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    view.dismiss();
                    view.toast(it.getMsg());
                    if (it.getCode().equals("0")) {
                        limit.setFid(it.getBody().getFid());
                        limits.add(limit);
                        notifyChange();
                        view.back();
                    }
                }, e -> {
                    view.dismiss();
                    view.toast("出错了");
                });
        compositeDisposable.add(disposable);
    }

    public void update(int position, Limit limit) {
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        view.load();
        Disposable disposable = limitModel.update(limit)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    view.dismiss();
                    view.toast(it.getMsg());
                    if (it.getCode().equals("0")) {
                        limits.set(position, limit);
                        notifyChange();
                        view.back();
                    }
                }, e -> {
                    view.dismiss();
                    view.toast("出错了");
                });
        compositeDisposable.add(disposable);
    }

    public LimitViewModel addViewModel() {
        viewModel.title.set("添加呼入限制");
        Limit limit = new Limit();
        limit.setFcstate("1");
        limit.setFbid(Baby.baby.getFid());
        viewModel.setLimit(limit);
        viewModel.setOnSaveListener(() -> {
            add(limit);
        });
        return viewModel;
    }

    public LimitViewModel updateViewModel(int position) {
        Limit limit = limits.get(position);
        limit.setFbid(Baby.baby.getFid());
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
        Disposable disposable = limitModel.delete(limits.get(position))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    view.dismiss();
                    view.toast(it.getMsg());
                    if (it.getCode().equals("0")) {
                        limits.remove(position);
                        notifyChange();
                        view.back();
                    }
                }, e -> {
                    view.dismiss();
                    view.toast("出错了");
                });
        compositeDisposable.add(disposable);
    }

    public void updateState() {
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        view.load();
        Disposable disposable = limitModel.updateState(Baby.baby.getFid(), Baby.baby.getFuser(), open.get() ? "0" : "1")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    view.dismiss();
                    view.toast(it.getMsg());
                    if (!it.getCode().equals("0")) {
                        open.set(!open.get());
                        Device.device.setFcstate(it.getBody());
                    }
                }, e -> {
                    view.dismiss();
                    open.set(!open.get());
                    view.toast("出错了");
                });
        compositeDisposable.add(disposable);
    }

}
