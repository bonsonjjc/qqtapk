package com.bonson.qqtapk.model.data.route;

import com.bonson.qqtapk.model.bean.Route;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by jiangjiancheng on 18/1/4.
 */

public interface RouteServer {
    @POST("data.html")
    Observable<List<Route>> routes(@Body Object body);
}
