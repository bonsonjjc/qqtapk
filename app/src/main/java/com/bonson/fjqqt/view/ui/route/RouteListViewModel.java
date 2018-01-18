package com.bonson.fjqqt.view.ui.route;

import android.app.Application;
import android.databinding.ObservableArrayList;

import com.bonson.fjqqt.model.bean.Route;
import com.bonson.fjqqt.model.data.RouteModel;
import com.bonson.qqtapk.model.bean.Baby;
import com.bonson.resource.activity.BaseView;
import com.bonson.resource.viewmodel.AndroidViewModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class RouteListViewModel extends AndroidViewModel {

    public final List<Route> routes = new ObservableArrayList<>();

    @Inject
    RouteModel routeModel;

    private BaseView view;

    @Inject
    public RouteListViewModel(Application application) {
        super(application);
    }

    public void setView(BaseView view) {
        this.view = view;
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
                }, e -> {
                    view.toast("出错了");
                });
        compositeDisposable.add(disposable);
    }
}
