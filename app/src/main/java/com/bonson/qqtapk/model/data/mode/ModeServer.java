package com.bonson.qqtapk.model.data.mode;

import com.bonson.qqtapk.model.bean.Mode;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by jiangjiancheng on 18/1/4.
 */

public interface ModeServer {
    @POST("data.html")
    Observable<List<Mode>> mode(@Body Object body);

    @POST("data.html")
    Observable<List<Mode>> update(@Body Object body);
}
