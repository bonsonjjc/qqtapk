package com.bonson.qqtapk.model.data.lesson;

import com.bonson.qqtapk.app.ErrorCode;
import com.bonson.qqtapk.model.bean.Base;
import com.bonson.qqtapk.model.bean.Lesson;
import com.bonson.qqtapk.model.bean.Result;
import com.bonson.qqtapk.model.data.ApiServer;
import com.bonson.qqtapk.utils.QQtBuilder;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by zjw on 2018/1/4.
 */

public class LessonModel implements LessonDataSource {
    private ApiServer lessonServer;

    @Inject
    public LessonModel(ApiServer lessonServer) {
        this.lessonServer = lessonServer;
    }

    @Override
    public Observable<Result<List<Lesson>>> lessons(String id) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("fbid", id);
        Object body = QQtBuilder.build("05", map);
        return lessonServer.lessons(body)
                .subscribeOn(Schedulers.io())
                .map(it -> {
                    Result<List<Lesson>> result = new Result<>();
                    if (it.isEmpty()) {
                        result.setCode("-1");
                        result.setMsg("没有设置上课静默");
                    } else {
                        result.setBody(it);
                        result.setCode("0");
                        result.setMsg("获取成功");
                    }
                    return result;
                });
    }

    @Override
    public Observable<Result<Base>> update(String bid, List<Lesson> lessons) {
        Map<String, String> map = new LinkedHashMap<>();
        StringBuilder builder = new StringBuilder();
        for (Lesson lesson : lessons) {
            builder.append(lesson.getFbegin());
            builder.append("-");
            builder.append(lesson.getFend());
            builder.append("-");
            builder.append(lesson.getFstate());
            if (lessons.indexOf(lesson) != lessons.size() - 1) {
                builder.append("|");
            }
        }
        map.put("fbid", bid);
        map.put("silences", builder.toString());
        Object args = QQtBuilder.build("26", map);
        return lessonServer.base(args)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(it -> {
                    Result<Base> result = new Result<>();
                    Base base = it.get(0);
                    if ("0".equals(base.getFresult())) {
                        result.setCode("0");
                        result.setBody(base);
                        result.setMsg("修改成功");
                    } else {
                        result.setCode("-1");
                        result.setMsg(ErrorCode.message(base.getFresult()));
                    }
                    return result;
                });
    }
}
