package com.bonson.qqtapk.model.data.user;

import com.bonson.library.convert.qqtfactory.Encode;
import com.bonson.qqtapk.model.bean.UserBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by zjw on 2018/1/2.
 */

public interface UserServer {
    @POST("data.html")
//    @Encode(encoder = true, decoder = true)
    Observable<List<UserBean>> login(@Body Object requestBody);

    @POST("data.html")
    @Encode(encoder = true, decoder = true)
    Observable<List<UserBean>> register(@Body Object requestBody);

    @POST("data.html")
    @Encode(encoder = true, decoder = true)
    Observable<List<UserBean>> forget(@Body Object requestBody);

    @POST("data.html")
    @Encode(encoder = true, decoder = true)
    Observable<List<UserBean>> reset(@Body Object requestBody);

    @POST("data.html")
    @Encode(encoder = true, decoder = true)
    Observable<List<UserBean>> verify(@Body Object requestBody);
}
