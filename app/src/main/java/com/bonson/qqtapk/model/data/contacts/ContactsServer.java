package com.bonson.qqtapk.model.data.contacts;

import com.bonson.qqtapk.model.bean.Contacts;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by zjw on 2018/1/5.
 */

public interface ContactsServer {

    @POST("data.html")
    Observable<List<Contacts>> contacts(@Body Object o);

    @POST("data.html")
    Observable<List<Contacts>> opelear(@Body Object o);

    @POST("data.html")
    Observable<List<Contacts>> imports(@Body Object o);
}
