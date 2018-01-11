package com.bonson.qqtapk.view.ui.index;

import android.app.Application;
import android.databinding.ObservableArrayList;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.model.bean.Baby;
import com.bonson.qqtapk.model.bean.User;
import com.bonson.qqtapk.model.data.baby.BabyModel;
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

    public void babies() {
        Disposable disposable = babyModel.list(User.user.getUserId())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    babies.addAll(it);
                    notifyChange();
                }, e -> {
                    e.printStackTrace();
                });
        compositeDisposable.add(disposable);
    }
}
