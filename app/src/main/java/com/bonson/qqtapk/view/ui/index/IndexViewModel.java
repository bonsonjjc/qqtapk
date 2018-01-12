package com.bonson.qqtapk.view.ui.index;

import android.app.Application;
import android.databinding.ObservableArrayList;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.model.bean.Baby;
import com.bonson.qqtapk.model.bean.User;
import com.bonson.qqtapk.model.data.baby.BabyModel;
import com.bonson.qqtapk.model.data.user.UserModel;
import com.bonson.resource.activity.BaseView;
import com.bonson.resource.viewmodel.AndroidViewModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * Created by zjw on 2018/1/2.
 */
@ActivityScope
public class IndexViewModel extends AndroidViewModel {
    public List<Baby> babies = new ObservableArrayList<>();

    private BabyModel babyModel;

    @Inject
    public IndexViewModel(BabyModel babyModel, Application application) {
        super(application);
        this.babyModel = babyModel;
    }

    BaseView view;

    public void babies() {
        Disposable disposable = babyModel.list(User.user.getUserId())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    babies.clear();
                    babies.addAll(it);
                    notifyChange();
                }, e -> {
                    e.printStackTrace();
                });
        compositeDisposable.add(disposable);
    }

    public void setView(BaseView view) {
        this.view = view;
    }

    public void change(int index) {
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        view.load();
        Disposable disposable = babyModel.getBaby(babies.get(index).getFid())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    view.dismiss();
                    view.toast(it.getMsg());
                    if (it.getCode().equals("0")) {
                        Baby.baby = it.getBody();
                    }
                }, e -> {
                    view.dismiss();
                    e.printStackTrace();
                });
        compositeDisposable.add(disposable);
    }
}
