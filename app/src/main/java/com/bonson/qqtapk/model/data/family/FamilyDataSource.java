package com.bonson.qqtapk.model.data.family;

import com.bonson.qqtapk.model.bean.Family;
import com.bonson.qqtapk.model.bean.Result;

import java.util.List;

import io.reactivex.Observable;

public interface FamilyDataSource {
    Observable<Result<List<Family>>> families();

    Observable<Result<Family>> update(Family family);
}
