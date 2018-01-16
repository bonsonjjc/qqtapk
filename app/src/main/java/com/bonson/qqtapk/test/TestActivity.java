package com.bonson.qqtapk.test;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.bonson.library.utils.security.Md5Utils;
import com.bonson.qqtapk.model.data2.UserModel;
import com.bonson.resource.activity.BaseDaggerActivity;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;


public class TestActivity extends BaseDaggerActivity {
    @Inject
    UserModel userModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Disposable disposable = userModel.login("13774567814", Md5Utils.toMD5("123456"), "", true)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    toast(it.getMsg());
                    if(it.getCode().equals("0")){

                    }
                }, e -> {

                });
    }
}
