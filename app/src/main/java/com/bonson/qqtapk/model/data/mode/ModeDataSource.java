package com.bonson.qqtapk.model.data.mode;

import com.bonson.qqtapk.model.bean.Mode;
import com.bonson.qqtapk.model.bean.Result;

import io.reactivex.Observable;

interface ModeDataSource {
    Observable<Result<Mode>> model(String bid);

    Observable<Result<Mode>> update(Mode mode);
}
