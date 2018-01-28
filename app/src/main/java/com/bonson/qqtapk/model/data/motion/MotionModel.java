package com.bonson.qqtapk.model.data.motion;

import com.bonson.qqtapk.model.bean.Motion;
import com.bonson.qqtapk.model.bean.Result;
import com.bonson.qqtapk.model.bean.Sleep;
import com.bonson.qqtapk.model.data.ApiServer;
import com.bonson.qqtapk.utils.QQtBuilder;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class MotionModel implements MotionDataSource {
    private ApiServer apiServer;

    @Inject
    public MotionModel(ApiServer apiServer) {
        this.apiServer = apiServer;
    }

    @Override
    public Observable<Result<List<Motion>>> motion(String bid) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("fbaby", bid);
        Object body = QQtBuilder.build("59", map);
        return apiServer.motions(body)
                .subscribeOn(Schedulers.io())
                .map(it -> {
                    Result<List<Motion>> result = new Result<>();
                    if (it.isEmpty()) {
                        result.setCode("-1");
                        result.setMsg("没有运动记录");
                    } else {
                        result.setCode("0");
                        result.setMsg("获取运动成功");
                        result.setBody(it);
                    }
                    return result;
                });
    }

    @Override
    public Observable<Result<List<Sleep>>> sleep(String bid) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("fbaby", bid);
        Object body = QQtBuilder.build("60", map);
        return apiServer.sleeps(body)
                .subscribeOn(Schedulers.io())
                .map(it -> {
                    Result<List<Sleep>> result = new Result<>();
                    if (it.isEmpty()) {
                        result.setCode("-1");
                        result.setMsg("没有睡眠记录");
                    } else {
                        result.setCode("0");
                        result.setMsg("获取睡眠成功");
                        result.setBody(it);
                    }
                    return result;
                });
    }
}
