package com.bonson.fjqqt.model;

import com.bonson.fjqqt.model.bean.RouteTime;
import com.bonson.fjqqt.view.ui.terminal.alarm.Alarm;
import com.bonson.fjqqt.model.bean.Info;
import com.bonson.fjqqt.model.bean.Route;
import com.bonson.fjqqt.model.bean.Sms;
import com.bonson.fjqqt.view.ui.terminal.timer.Timer;
import com.bonson.qqtapk.model.bean.Base;
import com.bonson.qqtapk.model.bean.Family;
import com.bonson.qqtapk.model.bean.Lesson;
import com.bonson.qqtapk.model.bean.Limit;
import com.bonson.qqtapk.model.bean.Location;
import com.bonson.qqtapk.model.bean.SafeArea;
import com.bonson.qqtapk.model.bean.UserBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface FApiServer {
    @FormUrlEncoded
    @POST("qqt3wap")
    Observable<List<Base>> base(@Field("data") String bean);

    @FormUrlEncoded
    @POST("qqt3wap")
    Observable<List<Info>> info(@Field("data") String bean);

    @FormUrlEncoded
    @POST("qqt3wap")
    Observable<List<UserBean>> user(@Field("data") String bean);

    @FormUrlEncoded
    @POST("qqt3wap")
    Observable<List<Location>> location(@Field("data") String bean);

    @FormUrlEncoded
    @POST("qqt3wap")
    Observable<List<Route>> routes(@Field("data") String bean);

    @FormUrlEncoded
    @POST("qqt3wap")
    Observable<List<RouteTime>> routeTimes(@Field("data") String bean);

    @FormUrlEncoded
    @POST("qqt3wap")
    Observable<List<SafeArea>> safeArea(@Field("data") String bean);

    @FormUrlEncoded
    @POST("qqt3wap")
    Observable<List<Family>> families(@Field("data") String bean);

    @FormUrlEncoded
    @POST("qqt3wap")
    Observable<List<Limit>> limits(@Field("data") String bean);

    @FormUrlEncoded
    @POST("qqt3wap")
    Observable<List<Timer>> timers(@Field("data") String bean);

    @FormUrlEncoded
    @POST("qqt3wap")
    Observable<List<Alarm>> alarms(@Field("data") String bean);

    @FormUrlEncoded
    @POST("qqt3wap")
    Observable<List<Lesson>> lessons(@Field("data") String bean);

    @FormUrlEncoded
    @POST("qqt3wap")
    Observable<List<Sms>> sms(@Field("data") String bean);
}
