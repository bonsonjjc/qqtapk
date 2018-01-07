package com.bonson.qqtapk.model.data.family;

import com.bonson.qqtapk.model.bean.Family;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by zjw on 2018/1/3.
 */

public interface FamilyServer {
    @POST("data.html")
    Observable<List<Family>> families(@Body Object body);

    @POST("data.html")
    Observable<List<Family>> update(@Body Object body);
}
