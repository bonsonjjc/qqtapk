package com.bonson.qqtapk.model.data.lesson;

import com.bonson.qqtapk.model.bean.Base;
import com.bonson.qqtapk.model.bean.Lesson;
import com.bonson.qqtapk.model.bean.Result;

import java.util.List;

import io.reactivex.Observable;

interface LessonDataSource {
    Observable<Result<List<Lesson>>> lessons(String id);

    Observable<Result<Base>> update(String bid, List<Lesson> lessons);
}
