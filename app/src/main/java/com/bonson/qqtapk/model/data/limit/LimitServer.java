package com.bonson.qqtapk.model.data.limit;

import com.bonson.qqtapk.model.bean.Limit;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by jiangjiancheng on 18/1/4.
 */

public interface LimitServer {
    @POST("data.html")
    Observable<List<Limit>> limits(@Body Object object);

    @POST("data.html")
    Observable<List<Limit>> add(@Body Object object);

    @POST("data.html")
    Observable<List<Limit>> delete(@Body Object object);

    @POST("data.html")
    Observable<List<Limit>> update(@Body Object object);
}
