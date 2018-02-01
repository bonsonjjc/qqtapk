package com.bonson.qqtapk.view.ui.index;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.model.bean.Baby;
import com.bonson.qqtapk.model.bean.Menu;
import com.bonson.qqtapk.model.data.baby.BabyModel;
import com.bonson.qqtapk.viewmodel.UserViewModel;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * Created by zjw on 2018/1/2.
 */
@ActivityScope
public class IndexViewModel extends UserViewModel {
    public final ObservableList<Menu> menus = new ObservableArrayList<>();
    public final ObservableList<Baby> babies = new ObservableArrayList<>();
    public final ObservableField<String> icon = new ObservableField<>();

    @Inject
    MainViewModel viewModel;

    @Inject
    BabyModel babyServer;

    @Inject
    public IndexViewModel(Application application) {
        super(application);
    }

    public void initMenu() {
        menus.clear();
        menus.addAll(MenuHelper.createMenu(baby().getFmenus(),baby().getFtag()));
    }

    public void babies() {
        icon.set(baby().getFimg());
        Disposable disposable = babyServer.getUserDao().babyList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    Baby baby = new Baby();
                    baby.setFid("-1");
                    baby.setFname("添加宝贝");
                    it.add(baby);
                    babies.clear();
                    babies.addAll(it);
                }, Throwable::printStackTrace);
        compositeDisposable.add(disposable);
    }

    public void change(int index) {
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        view.load();
//        Disposable disposable = babyServer.switchBaby(babies.get(index).getFid())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(it -> {
//                    view.toast(it.getMsg());
//                    if (it.getCode().equals("0")) {
//                        Baby.baby = it.getBody();
//                        icon.set(Baby.baby.getFimg());
//                        device();
//                    }
//                }, e -> {
//                    view.dismiss();
//                    e.printStackTrace();
//                });
//        compositeDisposable.add(disposable);
    }

    public void device() {
        viewModel.device();
    }
}
