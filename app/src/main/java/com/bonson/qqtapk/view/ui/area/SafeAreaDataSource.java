package com.bonson.qqtapk.view.ui.area;

import com.bonson.qqtapk.model.bean.Result;
import com.bonson.qqtapk.model.bean.SafeArea;

import io.reactivex.Observable;

public interface SafeAreaDataSource {
    Observable<Result<SafeArea>> safeArea(String type);

    Observable<Result<SafeArea>> update(SafeArea safeArea);
}
