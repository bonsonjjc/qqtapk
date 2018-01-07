package com.bonson.qqtapk.model.data.baby;

import com.bonson.qqtapk.model.bean.Baby;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by zjw on 2018/1/2.
 */

public interface BabyServer {
    @POST("data.html")
    Observable<List<Baby>> get(@Body Object o);

    @POST("data.html")
    Observable<List<Baby>> update(@Body Object o);

    @POST("data.html")
    Observable<List<Baby>> unbind(@Body Object o);

    @POST("data.html")
    Observable<List<Baby>> bind(@Body Object o);
}
