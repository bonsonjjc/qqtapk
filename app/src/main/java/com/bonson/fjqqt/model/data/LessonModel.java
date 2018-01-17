package com.bonson.fjqqt.model.data;
import com.bonson.fjqqt.model.FApiServer;
import com.bonson.qqtapk.model.bean.Base;
import com.bonson.qqtapk.model.bean.Lesson;
import com.bonson.qqtapk.model.bean.Result;
import com.bonson.resource.utils.EncodeUtils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class LessonModel {
    private FApiServer apiServer;

    @Inject
    public LessonModel(FApiServer apiServer) {
        this.apiServer = apiServer;
    }

    public Observable<Result<List<Lesson>>> lessons(String ftmobile) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("action", "CWA012");
        map.put("ftmobile", ftmobile);
        return apiServer.lessons(EncodeUtils.encode(map))
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

    public Observable<Result<Base>> update(String mobile, List<Lesson> lessons) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("action", "CWA013");
        map.put("ftmobile", mobile);
        return apiServer.base(EncodeUtils.encode(map))
                .subscribeOn(Schedulers.io())
                .map(it -> {
                    Result<Base> result = new Result<>();
                    Base base = it.get(0);
                    if ("100".equals(base.getFresult())) {
                        result.setCode("0");
                        result.setBody(base);
                        result.setMsg("修改成功");
                    } else {
                        result.setCode("-1");
                        result.setMsg(base.getFmsg());
                    }
                    return result;
                });
    }
}
