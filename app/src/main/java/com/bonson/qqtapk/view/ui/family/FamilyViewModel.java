package com.bonson.qqtapk.view.ui.family;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.model.bean.Family;
import com.bonson.qqtapk.model.data.family.FamilyModel;
import com.bonson.qqtapk.model.data.family.FamilyModelDataSource;
import com.bonson.qqtapk.view.ui.contacts.phone.PhoneViewModel;
import com.bonson.resource.activity.BaseView;
import com.bonson.resource.viewmodel.AndroidViewModel;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * Created by jiangjiancheng on 17/12/31.
 */
@ActivityScope
public class FamilyViewModel extends AndroidViewModel {
    public ObservableList<Family> families = new ObservableArrayList<>();

    @Inject
    FamilyModelDataSource familyModel;

    private PhoneViewModel viewModel;

    @Inject
    public FamilyViewModel(Application application, PhoneViewModel viewModel) {
        super(application);
        this.viewModel = viewModel;
    }

    public PhoneViewModel updateViewModel(int position) {
        Family family = families.get(position);
        viewModel.title.set(family.getFname());
        viewModel.name.set(family.getFname());
        viewModel.mobile.set(family.getFmobile());
        viewModel.right.set("保存");
        viewModel.setOnPhoneListener(() -> {
            family.setFname(viewModel.name.get());
            family.setFmobile(viewModel.mobile.get());
            update(position, family);
        });
        return viewModel;
    }

    public void families() {
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        Disposable disposable = familyModel.families()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    view.toast(it.getMsg());
                    if ("0".equals(it.getCode())) {
                        for (Family family : it.getBody()) {
                            for (int i = 0; i < families.size(); i++) {
                                Family temp = families.get(i);
                                if (family.getFkey().equals(temp.getFkey())) {
                                    temp.setFid(family.getFid());
                                    temp.setFname(family.getFname());
                                    temp.setFmobile(family.getFmobile());
                                    families.set(i, temp);
                                }
                            }
                        }
                    }
                }, e -> {
                    view.toast("出错了");
                    e.printStackTrace();
                });
        compositeDisposable.add(disposable);
    }

    public void update(int position, Family family) {
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        view.load();
        Disposable disposable = familyModel.update(family)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    view.dismiss();
                    view.toast(result.getMsg());
                    if ("0".equals(result.getCode())) {
                        families.set(position, family);
                        view.back();
                    }
                }, e -> {
                    view.dismiss();
                    view.toast("出错了");
                    e.printStackTrace();
                });
        compositeDisposable.add(disposable);
    }

}
