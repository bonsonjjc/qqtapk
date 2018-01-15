package com.bonson.qqtapk.model.data;

import com.bonson.qqtapk.model.bean.Baby;
import com.bonson.qqtapk.model.bean.Base;
import com.bonson.qqtapk.model.bean.Contact;
import com.bonson.qqtapk.model.bean.Family;
import com.bonson.qqtapk.model.bean.Flower;
import com.bonson.qqtapk.model.bean.Lesson;
import com.bonson.qqtapk.model.bean.Limit;
import com.bonson.qqtapk.model.bean.Location;
import com.bonson.qqtapk.model.bean.Member;
import com.bonson.qqtapk.model.bean.Message;
import com.bonson.qqtapk.model.bean.Mode;
import com.bonson.qqtapk.model.bean.Route;
import com.bonson.qqtapk.model.bean.SafeArea;
import com.bonson.qqtapk.model.bean.UserBean;
import com.bonson.qqtapk.model.bean.Voice;
import com.bonson.qqtapk.model.data.index.Index;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiServer {
    @POST("data.html")
    Observable<List<Base>> base(@Body Object body);

    @POST("data.html")
    Observable<List<SafeArea>> safeArea(@Body Object body);

    @POST("data.html")
    Observable<List<Baby>> baby(@Body Object body);

    @POST("data.html")
    Observable<List<Contact>> contacts(@Body Object body);

    @POST("data.html")
    Observable<List<Message>> messages(@Body Object body);

    @POST("data.html")
    Observable<List<Family>> families(@Body Object body);

    @POST("data.html")
    Observable<Index> device(@Body Object body);

    @POST("data.html")
    Observable<List<Flower>> flowers(@Body Object body);

    @POST("data.html")
    Observable<List<Lesson>> lessons(@Body Object body);

    @POST("data.html")
    Observable<List<Member>> members(@Body Object body);

    @POST("data.html")
    Observable<List<Limit>> limits(@Body Object object);

    @POST("data.html")
    Observable<List<Location>> location(@Body Object object);

    @POST("data.html")
    Observable<List<Mode>> mode(@Body Object body);

    @POST("data.html")
    Observable<List<Base>> ring(@Body Object body);

    @POST("data.html")
    Observable<List<Route>> routes(@Body Object body);

    @POST("data.html")
//    @Encode(encoder = true, decoder = true)
    Observable<List<Voice>> voices(@Body Object body);


    @POST("data.html")
//    @Encode(encoder = true, decoder = true)
    Observable<String> upload(@Body Object body);


    @POST("data.html")
//    @Encode(encoder = true, decoder = true)
    Observable<List<UserBean>> user(@Body Object body);
}
