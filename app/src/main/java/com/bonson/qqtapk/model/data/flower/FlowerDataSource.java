package com.bonson.qqtapk.model.data.flower;

import com.bonson.qqtapk.model.bean.Flower;
import com.bonson.qqtapk.model.bean.Result;

import java.util.List;

import io.reactivex.Observable;

interface FlowerDataSource {
    Observable<Result<List<Flower>>> flowers(String id, int start, int pagerSize);

    Observable<Result<Flower>> pull(Flower flower);
}
