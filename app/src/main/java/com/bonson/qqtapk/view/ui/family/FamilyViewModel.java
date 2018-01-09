package com.bonson.qqtapk.view.ui.family;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.model.bean.Baby;
import com.bonson.qqtapk.model.bean.Family;
import com.bonson.qqtapk.model.data.family.FamilyModel;
import com.bonson.resource.activity.BaseView;
import com.bonson.resource.viewmodel.AndroidViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * Created by jiangjiancheng on 17/12/31.
 */
@ActivityScope
public class FamilyViewModel extends AndroidViewModel {
    private ObservableList<Family> families = new ObservableArrayList<>();
    @Inject
    FamilyModel familyModel;

    private String[] icons = {"ico_sos", "ico_01", "ico_02", "ico_03"};

    private BaseView view;

    @Inject
    FamilyViewModel(Application application) {
        super(application);
    }

    public List<Family> getFamilies() {
        return families;
    }

    public void setFamilies(List<Family> families) {
        this.families.addAll(families);
        notifyChange();
    }

    public void setView(BaseView view) {
        this.view = view;
    }

    void families() {
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        Disposable disposable = familyModel.families(Baby.baby.getFid())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    view.toast(result.getMsg());
                    if ("0".equals(result.getCode())) {
                        List<Family> families = result.getBody();
                        for (int i = 0; i < families.size(); i++) {
                            families.get(i).setIcon(icons[i]);
                        }
                        setFamilies(result.getBody());
                    }
                }, e -> {
                    view.toast("出错了");
                    e.printStackTrace();
                });
        compositeDisposable.add(disposable);
    }
}
