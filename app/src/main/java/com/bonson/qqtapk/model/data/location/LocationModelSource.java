package com.bonson.qqtapk.model.data.location;

import com.bonson.qqtapk.model.bean.Location;
import com.bonson.qqtapk.model.bean.Result;

import io.reactivex.Observable;

public interface LocationModelSource {
    Observable<Result<String>> location(String bid);

    Observable<Result<Location>> result(String bid, String seqid, int count);
}
