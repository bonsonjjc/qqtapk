package com.bonson.qqtapk.view.ui.info;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.bonson.qqtapk.app.Route;
import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.model.bean.Baby;
import com.bonson.qqtapk.model.data.UploadServer;
import com.bonson.qqtapk.model.data.baby.BabyModel;
import com.bonson.qqtapk.view.ui.info.input.InputViewModel;
import com.bonson.qqtapk.view.ui.info.select.Select;
import com.bonson.qqtapk.view.ui.info.select.SelectViewModel;
import com.bonson.resource.activity.ActivityUtils;
import com.bonson.resource.viewmodel.AndroidViewModel;

import java.io.File;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by jiangjiancheng on 17/12/31.
 */
@ActivityScope
public class InfoViewModel extends AndroidViewModel {
    @Inject
    SelectViewModel viewModel;
    @Inject
    InputViewModel inputViewModel;
    @Inject
    BabyModel babyModel;
    @Inject
    UploadServer uploadServer;

    @Inject
    public InfoViewModel(@NonNull Application application) {
        super(application);
        baby = Baby.baby;
    }

    public InputViewModel inputFragment(int type, String title, String hint) {
        inputViewModel.hint.set(hint);
        inputViewModel.title.set(title);
        switch (type) {
            case InfoActivity.Type.name:
                inputViewModel.digits.set(null);
                inputViewModel.value.set(Baby.baby.getFname());
                inputViewModel.length.set(4);
                break;
            case InfoActivity.Type.mobile:
                inputViewModel.digits.set("0123456789");
                inputViewModel.value.set(Baby.baby.getFtmobile());
                inputViewModel.length.set(11);
                break;
            case InfoActivity.Type.height:
                inputViewModel.digits.set("0123456789");
                inputViewModel.value.set(Baby.baby.getFheight());
                inputViewModel.length.set(3);
                break;
            case InfoActivity.Type.weight:
                inputViewModel.digits.set("0123456789");
                inputViewModel.value.set(Baby.baby.getFweight());
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
                    Baby.baby.setFname(inputViewModel.value.get());
                    break;
                case InfoActivity.Type.mobile:
                    Baby.baby.setFtmobile(inputViewModel.value.get());
                    break;
                case InfoActivity.Type.height:
                    Baby.baby.setFheight(inputViewModel.value.get());
                    break;
                case InfoActivity.Type.weight:
                    Baby.baby.setFweight(inputViewModel.value.get());
                    break;
            }
            view.back();
            notifyChange();
            update();
        });
        return inputViewModel;
    }

    private Baby baby;

    public Baby getBaby() {
        return baby;
    }

    public SelectViewModel selectViewModel() {
        viewModel.title.set("我是宝贝的");
        viewModel.setSingle(true);
        String[] parents = {"爸爸", "妈妈", "爷爷", "奶奶", "外公", "外婆", "家长"};
        ObservableArrayList<Select> selects = new ObservableArrayList<>();
        for (String parent1 : parents) {
            Select parent = new Select();
            parent.setName(parent1);
            parent.setChecked(parent.getName().equals(Baby.baby.getFrelative()));
            selects.add(parent);
        }
        viewModel.setSelects(selects);
        viewModel.setOnItemClickListener((v) -> {
            Baby.baby.setFrelative(viewModel.selects.get(v).getName());
            notifyChange();
            update();
            view.back();
        });
        return viewModel;
    }

    public void upload(File bytes) {
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        RequestBody requestBody = RequestBody.create(MultipartBody.FORM, bytes);
        MultipartBody multipartBody = new MultipartBody.Builder()
                .addFormDataPart("file2", Baby.baby.getFid() + ".jpg", requestBody)
                .build();
        Disposable disposable = uploadServer.icon(multipartBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    if (TextUtils.isEmpty(it)) {
                        view.toast("头像上传失败");
                        return;
                    }
                    Baby.baby.setFimg(it);
                    notifyChange();
                    update();
                }, e -> {
                    view.toast("头像上传失败");
                });
        compositeDisposable.add(disposable);
    }

    public void update() {
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        view.load();
        Disposable disposable = babyModel.update(Baby.baby)
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
                        List<Baby> babies = it.getBody();
                        if (babies == null || babies.isEmpty()) {
                            view.start(Route.login);
                            ActivityUtils.clear();
                        } else {
                            Baby.baby = babies.get(0);
                            view.back();
                        }
                    }
                }, e -> {
                    view.dismiss();
                    view.toast("出错了");
                });
        compositeDisposable.add(disposable);
    }
}
