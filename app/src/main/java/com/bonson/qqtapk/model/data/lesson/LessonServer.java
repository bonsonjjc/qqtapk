package com.bonson.qqtapk.model.data.lesson;

import com.bonson.qqtapk.model.bean.Family;
import com.bonson.qqtapk.model.bean.Lesson;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by zjw on 2018/1/3.
 */

public interface LessonServer {
    @POST("data.html")
    Observable<List<Lesson>> lessons(@Body Object body);

    @POST("data.html")
    Observable<List<Lesson>> update(@Body Object body);
}
