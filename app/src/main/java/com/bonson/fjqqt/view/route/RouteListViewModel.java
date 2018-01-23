package com.bonson.fjqqt.view.route;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.bonson.fjqqt.model.bean.RouteTime;
import com.bonson.fjqqt.model.data.RouteModel;
import com.bonson.fjqqt.view.route.time.AddTimeViewModel;
import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.model.bean.Baby;
import com.bonson.resource.activity.BaseView;
import com.bonson.resource.viewmodel.AndroidViewModel;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

@ActivityScope
public class RouteListViewModel extends AndroidViewModel {

    public final ObservableList<RouteTime> routes = new ObservableArrayList<>();

    @Inject
    RouteModel routeModel;

    @Inject
    AddTimeViewModel viewModel;

    @Inject
    public RouteListViewModel(Application application) {
        super(application);
    }

    public void times() {
        if (!isNetWork()) {
            view.start("网络不可用");
            return;
        }
        Disposable disposable = routeModel.times(Baby.baby.getFtmobile())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    view.toast(it.getMsg());
                    if (it.getCode().equals("0")) {
                        routes.clear();
                        routes.addAll(it.getBody());
                    }
                    RouteTime route = new RouteTime();
                    route.setFctime("2015-01-02");
                    route.setFbeghours("11");
                    route.setFbegminutes("12");
                    route.setFendhours("18");
                    route.setFendminutes("38");
                    route.setFstate("1");
                    route.setFtype("1");
                    routes.add(route);
                }, e -> {
                    view.toast("出错了");
                });
        compositeDisposable.add(disposable);
    }

    AddTimeViewModel add() {
        viewModel.title.set("查询时间");
        RouteTime routeTime = new RouteTime();
        routeTime.setFbeghours("00");
        routeTime.setFbegminutes("00");
        routeTime.setFendhours("00");
        routeTime.setFendminutes("00");
        routeTime.setFtype("1");
        routeTime.setFstate("1");
        viewModel.setOnAddTimeListener(it -> {
            update(-1, it);
        });
        viewModel.setRouteTime(routeTime);
        return viewModel;
    }


    AddTimeViewModel edit(int v) {
        viewModel.setRouteTime(routes.get(v));
        viewModel.title.set("查询时间");
        viewModel.setOnAddTimeListener(it -> {
            update(v, it);
        });
        return viewModel;
    }

    public void update(int v, RouteTime routeTime) {
        if (!isNetWork()) {
            view.start("网络不可用");
            return;
        }
        view.load();
        routeTime.setFtmobile(Baby.baby.getFtmobile());
        Disposable disposable = routeModel.addTime(routeTime)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    view.dismiss();
                    view.toast(it.getMsg());
                    if (it.getCode().equals("0")) {
                        if (v == -1) {
                            routes.add(routeTime);
                        } else {
                            routes.set(v, routeTime);
                        }
                    }
                }, e -> {
                    view.dismiss();
                    view.toast("出错了");
                });
        compositeDisposable.add(disposable);
    }
}
