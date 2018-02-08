package com.bonson.qqtapk.view.ui.setting.about;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.databinding.ObservableField;

import com.bonson.qqtapk.viewmodel.UserViewModel;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * Created by jiangjiancheng on 18/1/7.
 */

public class AboutViewModel extends UserViewModel {
    public final ObservableField<String> servicePhone = new ObservableField<>("15659149106");

    public final ObservableField<String> webUrl = new ObservableField<>("www.baidu.com");

    public final ObservableField<String> version = new ObservableField<>("3.0.0");

    @Inject
    AboutModel aboutModel;

    @Inject
    public AboutViewModel(Application application) {
        super(application);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        getVersion();
        about();
    }

    public void getVersion() {
        PackageManager packageManager = getApplication().getPackageManager();
        String packageName = getApplication().getPackageName();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);
            version.set(packageInfo.versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void about() {
        Disposable disposable = aboutModel.about(user().getMobile())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    view.toast(it.getMsg());
                    if (it.getCode().equals("0")) {
                        About body = it.getBody();
                        servicePhone.set(body.getFtel());
                        webUrl.set(body.getFurl());
                    }
                }, e -> {
                    view.toast("出错了");
                    e.printStackTrace();
                });
        compositeDisposable.add(disposable);
    }
}
