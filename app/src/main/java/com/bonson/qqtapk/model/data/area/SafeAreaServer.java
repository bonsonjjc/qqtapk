package com.bonson.qqtapk.model.data.area;

import com.bonson.qqtapk.model.bean.SafeArea;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by zjw on 2018/1/4.
 */

public interface SafeAreaServer {

    @POST("data.html")
    Observable<List<SafeArea>> safeArea(@Body Object obj);
}
