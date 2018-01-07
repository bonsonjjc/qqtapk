package com.bonson.qqtapk.model.data.lesson;

import com.bonson.qqtapk.app.ErrorCode;
import com.bonson.qqtapk.model.bean.Lesson;
import com.bonson.qqtapk.model.bean.Result;
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

public class LessonModel {
    private LessonServer lessonServer;

    @Inject
    public LessonModel(LessonServer lessonServer) {
        this.lessonServer = lessonServer;
    }

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
                        result.setMsg("没有亲情号码");
                    } else {
                        result.setBody(it);
                        result.setCode("0");
                        result.setMsg("获取成功");
                    }
                    return result;
                });
    }

    public Observable<Result<Lesson>> update(String bid, List<Lesson> lessons) {
        Map<String, String> map = new LinkedHashMap<>();
        StringBuilder builder = new StringBuilder();
        for (Lesson lesson : lessons) {
            builder.append(lesson.getFbegin());
            builder.append("-");
            builder.append(lesson.getFend());
            builder.append(lesson.getFstate());
            if (lessons.indexOf(lesson) != lessons.size() - 1) {
                builder.append("|");
            }
        }
        map.put("fid", bid);
        map.put("silences", builder.toString());
        Object args = QQtBuilder.build("26", map);
        return lessonServer.update(args)
                .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(it -> {
                    Result<Lesson> result = new Result<>();
                    Lesson inLimit = it.get(0);
                    if ("0".equals(inLimit.getFresult())) {
                        result.setCode("0");
                        result.setBody(inLimit);
                        result.setMsg("修改成功");
                    } else {
                        result.setCode("-1");
                        result.setMsg(ErrorCode.message(inLimit.getFresult()));
                    }
                    return null;
                });
    }
}
