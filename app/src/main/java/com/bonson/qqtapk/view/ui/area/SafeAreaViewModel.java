package com.bonson.qqtapk.view.ui.area;

import android.app.Application;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;

import com.baidu.mapapi.model.LatLng;
import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.model.bean.Baby;
import com.bonson.qqtapk.model.bean.SafeArea;
import com.bonson.qqtapk.model.data.area.SafeAreaDataSource;
import com.bonson.library.utils.NumberUtils;
import com.bonson.qqtapk.view.ui.index.LocationViewModel;
import com.bonson.resource.activity.BaseView;
import com.bonson.resource.viewmodel.AndroidViewModel;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * Created by jiangjiancheng on 17/12/31.
 */
@ActivityScope
public class SafeAreaViewModel extends AndroidViewModel {
    public ObservableInt radius = new ObservableInt();
    public ObservableBoolean state = new ObservableBoolean();
    public ObservableField<LatLng> position = new ObservableField<>();

    public ObservableField<String> type = new ObservableField<>("1");
    private SafeAreaDataSource areaModel;
    private BaseView view;

    @Inject
    LocationViewModel viewModel;

    @Inject
    public SafeAreaViewModel(Application application, SafeAreaDataSource areaModel) {
        super(application);
        this.areaModel = areaModel;
        position.set(new LatLng(0, 0));
    }

    public LocationViewModel getViewModel() {
        return viewModel;
    }

    public void setViewModel(LocationViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void setView(BaseView view) {
        this.view = view;
        viewModel.setView(view);
    }

    public void init() {
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        view.load();
        Disposable disposable = areaModel.safeArea(type.get())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    view.dismiss();
                    view.toast(it.getMsg());
                    if ("0".equals(it.getCode())) {
                        SafeArea area = it.getBody();
                        position.set(new LatLng(NumberUtils.parseDouble(area.getFy()), NumberUtils.parseDouble(area.getFx())));
                        radius.set(NumberUtils.parseInt(area.getFradius(), 200));
                        state.set("1".equals(area.getFstate()));
                        type.set(area.getFtype());
                    }
                }, e -> {
                    view.dismiss();
                    view.toast("出错了");
                    e.printStackTrace();
                });
        compositeDisposable.add(disposable);
    }

    public void save() {
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        view.load();
        SafeArea safeArea = new SafeArea();
        safeArea.setFradius(radius.get() + 200 + "");
        safeArea.setFstate(state.get() ? "1" : "0");
        safeArea.setFy(position.get().latitude + "");
        safeArea.setFx(position.get().longitude + "");
        safeArea.setFtype(type.get());
        Disposable disposable = areaModel.update(safeArea)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    view.dismiss();
                    view.toast(it.getMsg());
                }, e -> {
                    view.dismiss();
                    view.toast("出错了");
                    e.printStackTrace();
                });
        compositeDisposable.add(disposable);
    }

    public void map(LatLng latLng) {
        position.set(latLng);
    }

    public void change() {
        state.set(!state.get());
    }
}
