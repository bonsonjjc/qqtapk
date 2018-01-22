package com.bonson.qqtapk.view.ui.info;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.text.TextUtils;

import com.bonson.qqtapk.app.Route;
import com.bonson.qqtapk.model.bean.Baby;
import com.bonson.qqtapk.model.bean.User;
import com.bonson.qqtapk.model.data.baby.BabyModel;
import com.bonson.qqtapk.view.ui.info.input.InputViewModel;
import com.bonson.qqtapk.view.ui.info.select.Select;
import com.bonson.qqtapk.view.ui.info.select.SelectViewModel;
import com.bonson.resource.activity.ActivityUtils;
import com.bonson.resource.activity.BaseView;
import com.bonson.resource.viewmodel.AndroidViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by jiangjiancheng on 17/12/31.
 */
public class InfoViewModel extends AndroidViewModel {
    private Baby baby;

    @Inject
    SelectViewModel viewModel;

    @Inject
    InputViewModel inputViewModel;

    private BabyModel babyModel;


    @Inject
    public InfoViewModel(Application application, BabyModel babyModel) {
        super(application);
        this.babyModel = babyModel;
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
                inputViewModel.length.set(3);
                break;
            case InfoActivity.Type.weight:
                inputViewModel.digits.set("0123456789");
                inputViewModel.value.set(baby.getFweight());
                inputViewModel.length.set(3);
                break;
        }
        inputViewModel.setOnSaveListener(() -> {
            if (TextUtils.isEmpty(inputViewModel.value.get())) {
                view.toast("请输入内容");
                return;
            }
            switch (type) {
                case InfoActivity.Type.name:
                    baby.setFname(inputViewModel.value.get());
                    break;
                case InfoActivity.Type.mobile:
                    baby.setFtmobile(inputViewModel.value.get());
                    break;
                case InfoActivity.Type.height:
                    baby.setFheight(inputViewModel.value.get());
                    break;
                case InfoActivity.Type.weight:
                    baby.setFweight(inputViewModel.value.get());
                    break;
            }
            view.back();
            notifyChange();
            update();
        });
        return inputViewModel;
    }

    public SelectViewModel selectViewModel() {
        viewModel.title.set("我是宝贝的");
        viewModel.setSingle(true);
        String[] parents = {"爸爸", "妈妈", "爷爷", "奶奶", "外公", "外婆", "家长"};
        ObservableArrayList<Select> selects = new ObservableArrayList<>();
        for (int i = 0; i < parents.length; i++) {
            Select parent = new Select();
            parent.setName(parents[i]);
            parent.setChecked(parent.getName().equals(baby.getFrelative()));
            selects.add(parent);
        }
        viewModel.setSelects(selects);
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
        view.load();
        Disposable disposable =
                babyModel.update(baby)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(it -> {
                            view.dismiss();
                            view.toast(it.getMsg());
                        }, e -> {
                            view.dismiss();
                            view.toast("出错了");
                        });
        compositeDisposable.add(disposable);
    }

    public void unbind() {
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        view.load();
        Disposable disposable = babyModel.unbind(Baby.baby.getFuser(), Baby.baby.getFid())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    view.dismiss();
                    view.toast(it.getMsg());
                    if (it.getCode().equals("0")) {
                        Disposable subscribe = babyModel.list(User.user.getUserId())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(list -> {
                                    if (list.isEmpty()) {
                                        view.start(Route.login);
                                        ActivityUtils.clear();
                                    } else {
                                        Baby.baby = list.get(0);
                                        view.back();
                                    }
                                }, e -> {
                                    view.toast("出错了");
                                    view.start(Route.login);
                                    ActivityUtils.clear();
                                });
                        compositeDisposable.add(subscribe);
                    }
                }, e -> {
                    view.dismiss();
                    view.toast("出错了");
                });
        compositeDisposable.add(disposable);
    }
}
