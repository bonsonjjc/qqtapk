package com.bonson.qqtapk.model.data;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface TokenServer {

    @POST("data.html")
    Observable<Map<String, String>> token(@Body Object body);
}
