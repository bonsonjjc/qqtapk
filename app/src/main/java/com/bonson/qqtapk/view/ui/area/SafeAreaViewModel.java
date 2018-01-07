package com.bonson.qqtapk.view.ui.area;

import android.app.Application;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;

import com.baidu.mapapi.model.LatLng;
import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.model.bean.Baby;
import com.bonson.qqtapk.model.bean.SafeArea;
import com.bonson.qqtapk.model.data.area.SafeAreaModel;
import com.bonson.qqtapk.utils.NumberUtils;
import com.bonson.resource.activity.BaseView;
import com.bonson.resource.viewmodel.AndroidViewModel;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by jiangjiancheng on 17/12/31.
 */
@ActivityScope
public class SafeAreaViewModel extends AndroidViewModel {
    public ObservableField<String> address = new ObservableField<>("");
    public ObservableInt radius = new ObservableInt();
    public ObservableBoolean state = new ObservableBoolean();
    ObservableField<LatLng> position = new ObservableField<>();
    private SafeAreaModel areaModel;

    private BaseView view;

    @Inject
    SafeAreaViewModel(Application application, SafeAreaModel areaModel) {
        super(application);
        this.areaModel = areaModel;
        position.set(new LatLng(0, 0));
    }

    public void setView(BaseView view) {
        this.view = view;
    }

    void init() {
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        areaModel.safeArea(Baby.baby.getFid())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    view.toast(it.getMsg());
                    if ("0".equals(it.getCode())) {
                        SafeArea area = it.getBody();
                        position.set(new LatLng(NumberUtils.parseDouble(area.getFy()), NumberUtils.parseDouble(area.getFx())));
                        radius.set(NumberUtils.parseInt(area.getFradius(), 200));
                        state.set("1".equals(area.getFstate()));
                    }
                }, e -> {
                    view.toast("出错了");
                    e.printStackTrace();
                });
    }

    public void save() {
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        SafeArea safeArea = new SafeArea();
        safeArea.setFbid(Baby.baby.getFid());
        safeArea.setFradius(radius.get() + 200 + "");
        safeArea.setFstate(state.get() ? "1" : "0");
        safeArea.setFy(position.get().latitude + "");
        safeArea.setFx(position.get().longitude + "");
        areaModel.update(safeArea)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    view.toast(it.getMsg());
                }, e -> {
                    view.toast("出错了");
                    e.printStackTrace();
                });
    }

    public void map(LatLng latLng) {
        position.set(latLng);
    }

    public void location() {

    }

    public void change() {
        state.set(!state.get());
    }
}
