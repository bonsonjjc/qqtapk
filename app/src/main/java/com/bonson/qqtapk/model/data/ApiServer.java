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
import com.bonson.qqtapk.model.bean.Motion;
import com.bonson.qqtapk.model.bean.Route;
import com.bonson.qqtapk.model.bean.SafeArea;
import com.bonson.qqtapk.model.bean.Sleep;
import com.bonson.qqtapk.model.bean.UserBean;
import com.bonson.qqtapk.model.bean.Voice;
import com.bonson.qqtapk.model.data.index.Index;

import com.bonson.qqtapk.view.ui.motion.bean.Target;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
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
    Observable<List<Sleep>> sleeps(@Body Object body);

    @POST("data.html")
    Observable<List<Motion>> motions(@Body Object body);

    @POST("data.html")
    Observable<List<Mode>> mode(@Body Object body);

    @POST("data.html")
    Observable<List<Route>> routes(@Body Object body);

    @POST("data.html")
    Observable<Map<String, String>> token(@Body Object body);

    @POST("data.html")
    Observable<List<Voice>> voices(@Body Object body);

    @POST("data.html")
    Observable<List<UserBean>> user(@Body Object body);

    @POST("data.html")
    Observable<List<Target>> target(@Body Object body);
}
