package com.bonson.qqtapk.model.data.setting;

import com.bonson.qqtapk.model.bean.Baby;
import com.bonson.qqtapk.model.bean.Result;

import io.reactivex.Observable;

interface SettingDataSource {
    Observable<Result<String>> sleepTime(String uid, String bid, String sleepTime);

    Observable<Result<String>> token(Baby baby);
}
