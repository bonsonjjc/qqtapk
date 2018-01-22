package com.bonson.qqtapk.model.data.ring;

import com.bonson.qqtapk.model.bean.Base;
import com.bonson.qqtapk.model.bean.Result;

import io.reactivex.Observable;

interface RingDataSource {
    Observable<Result<Base>> ring(String fbid, String fuser, String caller, String callVolume, String callerVolume);
}
