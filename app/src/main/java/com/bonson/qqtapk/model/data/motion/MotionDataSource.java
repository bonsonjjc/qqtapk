package com.bonson.qqtapk.model.data.motion;

import com.bonson.qqtapk.model.bean.Motion;
import com.bonson.qqtapk.model.bean.Result;
import com.bonson.qqtapk.model.bean.Sleep;

import java.util.List;

import io.reactivex.Observable;

interface MotionDataSource {
    Observable<Result<List<Motion>>> motion(String bid);

    Observable<Result<List<Sleep>>> sleep(String bid);
}
