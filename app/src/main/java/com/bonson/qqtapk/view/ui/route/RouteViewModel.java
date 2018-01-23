package com.bonson.qqtapk.view.ui.route;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;

import com.bonson.library.utils.DateUtils;
import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.model.bean.Baby;
import com.bonson.qqtapk.model.bean.Route;
import com.bonson.qqtapk.model.data.route.RouteModel;
import com.bonson.resource.activity.BaseView;
import com.bonson.resource.viewmodel.AndroidViewModel;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * Created by jiangjiancheng on 17/12/31.
 */
@ActivityScope
public class RouteViewModel extends AndroidViewModel {
    public final ObservableList<Route> routes = new ObservableArrayList<>();
    public final ObservableField<String> title = new ObservableField<>(DateUtils.format(new Date(), "yyyy-MM-dd"));
    private RouteModel routeModel;

    @Inject
    public RouteViewModel(Application application, RouteModel routeModel) {
        super(application);
        this.routeModel = routeModel;
    }

    public void routes(String start, String end) {
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        view.load();
        Disposable disposable = routeModel.routes(Baby.baby.getFid(), start, end)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    view.toast(it.getMsg());
                    view.dismiss();
                    routes.clear();
                    if (it.getCode().equals("0")) {
                        routes.addAll(it.getBody());
                    }
                }, e -> {
                    view.dismiss();
                    view.toast("出错了");
                });
        compositeDisposable.add(disposable);
    }

}
