package com.bonson.qqtapk.model.data.motion;

import com.bonson.qqtapk.model.bean.Base;
import com.bonson.qqtapk.model.bean.Motion;
import com.bonson.qqtapk.model.bean.Result;
import com.bonson.qqtapk.model.bean.Sleep;
import com.bonson.qqtapk.view.ui.motion.bean.Target;

import java.util.List;

import io.reactivex.Observable;

public interface MotionDataSource {
    Observable<Result<List<Motion>>> motion(String bid);

    Observable<Result<List<Sleep>>> sleep(String bid);

    Observable<Result<Base>> setTarget(String bid, Target target);

    Observable<Result<Target>> target(String bid);
}
