package com.bonson.qqtapk.view.ui.route;

import android.app.Application;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.model.bean.Baby;
import com.bonson.qqtapk.model.bean.Route;
import com.bonson.qqtapk.model.data.route.RouteModel;
import com.bonson.resource.activity.BaseView;
import com.bonson.resource.viewmodel.AndroidViewModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * Created by jiangjiancheng on 17/12/31.
 */
@ActivityScope
public class RouteViewModel extends AndroidViewModel {
    private List<Route> routes;
    private BaseView view;

    private RouteModel routeModel;

    @Inject
    public RouteViewModel(Application application, RouteModel routeModel) {
        super(application);
        this.routeModel = routeModel;
    }

    public void setView(BaseView view) {
        this.view = view;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
        notifyChange();
    }

    public void routes(String start, String end) {
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        Disposable disposable = routeModel.routes(Baby.baby.getFid(), start, end)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    view.toast(it.getMsg());
                    if (it.getCode().equals("0")) {
                        setRoutes(it.getBody());
                    }
                }, e -> {
                    view.toast("出错了");
                });
        compositeDisposable.add(disposable);
    }

}
