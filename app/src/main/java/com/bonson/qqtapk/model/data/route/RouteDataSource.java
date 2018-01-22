package com.bonson.qqtapk.model.data.route;

import com.bonson.qqtapk.model.bean.Result;
import com.bonson.qqtapk.model.bean.Route;

import java.util.List;

import io.reactivex.Observable;

interface RouteDataSource {
    Observable<Result<List<Route>>> routes(String bid, String start, String end);
}
