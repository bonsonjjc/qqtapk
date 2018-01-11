package com.bonson.qqtapk.view.ui.info;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.text.TextUtils;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.model.bean.Baby;
import com.bonson.qqtapk.model.data.baby.BabyModel;
import com.bonson.qqtapk.view.ui.info.input.InputViewModel;
import com.bonson.qqtapk.view.ui.info.select.Select;
import com.bonson.qqtapk.view.ui.info.select.SelectViewModel;
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
public class InfoViewModel extends AndroidViewModel {
    private Baby baby;

    @Inject
    SelectViewModel viewModel;

    @Inject
    InputViewModel inputViewModel;

    private BabyModel babyModel;

    private BaseView view;

    @Inject
    public InfoViewModel(Application application, BabyModel babyModel) {
        super(application);
        this.babyModel = babyModel;
    }

    public void setView(BaseView view) {
        this.view = view;
    }

    public Baby getBaby() {
        return baby;
    }

    public void setBaby(Baby baby) {
        this.baby = baby;
    }

    public void setViewModel(SelectViewModel viewModel) {
        this.viewModel = viewModel;
    }


    public InputViewModel inputFragment(int type, String title, String hint) {
        inputViewModel.hint.set(hint);
        inputViewModel.title.set(title);
        switch (type) {
            case InfoActivity.Type.name:
                inputViewModel.digits.set(null);
                inputViewModel.value.set(baby.getFname());
                inputViewModel.length.set(4);
                break;
            case InfoActivity.Type.mobile:
                inputViewModel.digits.set("0123456789");
                inputViewModel.value.set(baby.getFtmobile());
                inputViewModel.length.set(11);
                break;
            case InfoActivity.Type.height:
                inputViewModel.digits.set("0123456789");
                inputViewModel.value.set(baby.getFheight());
                inputViewModel.length.set(2);
                break;
            case InfoActivity.Type.weight:
                inputViewModel.digits.set("0123456789");
                inputViewModel.value.set(baby.getFweight());
                inputViewModel.length.set(2);
                break;
        }
        inputViewModel.setOnSaveListener(() -> {
            if (TextUtils.isEmpty(inputViewModel.value.get())) {
                view.toast("请输入内容");
                return;
            }
            view.back();
            update();
        });
        return inputViewModel;
    }

    public SelectViewModel selectViewModel() {
        viewModel.title.set("我是宝贝的");
        viewModel.setSingle(true);
        List<Select> selects = new ObservableArrayList<>();
        Select select = new Select();
        select.setName("爸爸");
        select.setChecked(true);
        selects.add(select);
        viewModel.selects = selects;
        viewModel.setOnItemClickListener((v) -> {
            baby.setFrelative(viewModel.selects.get(v).getName());
            notifyChange();
            update();
            view.back();
        });
        return viewModel;
    }


    public void update() {
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        Disposable disposable = babyModel.update(baby)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    view.toast(it.getMsg());
                }, e -> {
                    view.toast("出错了");
                });
        compositeDisposable.add(disposable);
    }

    public void change() {

    }

    public void unbind() {

    }
}
