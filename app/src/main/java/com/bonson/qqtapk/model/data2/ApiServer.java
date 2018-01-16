package com.bonson.qqtapk.model.data2;

import com.bonson.qqtapk.model.bean.UserBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiServer {
    @FormUrlEncoded
    @POST("qqt3wap")
    Observable<List<UserBean>> user(@Field("data") String bean);
}
