package com.bonson.qqtapk.model.data.flower;

import com.bonson.qqtapk.model.bean.Flower;
import com.bonson.qqtapk.model.bean.Lesson;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by zjw on 2018/1/3.
 */

public interface FlowerServer {
    @POST("data.html")
    Observable<List<Flower>> flowers(@Body Object body);

    @POST("data.html")
    Observable<List<Flower>> update(@Body Object body);
}
