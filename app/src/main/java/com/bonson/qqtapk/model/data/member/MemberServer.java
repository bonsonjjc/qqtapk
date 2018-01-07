package com.bonson.qqtapk.model.data.member;


import com.bonson.qqtapk.model.bean.Member;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by jiangjiancheng on 18/1/4.
 */

public interface MemberServer {
    @POST("data.html")
    Observable<List<Member>> members(@Body Object body);

    @POST("data.html")
    Observable<List<Member>> update(@Body Object body);

    @POST("data.html")
    Observable<List<Member>> delete(@Body Object body);

    @POST("data.html")
    Observable<List<Member>> add(@Body Object body);
}
