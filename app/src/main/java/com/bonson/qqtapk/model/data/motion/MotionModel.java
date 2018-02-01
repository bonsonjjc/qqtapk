package com.bonson.qqtapk.model.data.motion;

import com.bonson.qqtapk.model.bean.Base;
import com.bonson.qqtapk.model.bean.Motion;
import com.bonson.qqtapk.model.bean.Result;
import com.bonson.qqtapk.model.bean.Sleep;
import com.bonson.qqtapk.model.data.ApiServer;
import com.bonson.qqtapk.utils.QQtBuilder;
import com.bonson.qqtapk.view.ui.motion.bean.Target;

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
        return apiServer.motions(body).subscribeOn(Schedulers.io()).map(it -> {
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
        return apiServer.sleeps(body).subscribeOn(Schedulers.io()).map(it -> {
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

    @Override
    public Observable<Result<Base>> setTarget(String bid, Target target) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("fbaby", bid);
        map.put("fwalk", target.getFwalk());
        map.put("fsleep", target.getFsleep());
        map.put("frequency", "30");
        Object body = QQtBuilder.build("56", map);
        return apiServer.base(body).subscribeOn(Schedulers.io()).map(it -> {
            Result<Base> result = new Result<>();
            Base base = it.get(0);
            if ("0".equals(base.getFresult())) {
                result.setCode("0");
                result.setMsg("设置成功");
                result.setBody(base);
            } else {
                result.setCode("-1");
                result.setMsg(base.getMsg());
            }
            return result;
        });
    }

    @Override
    public Observable<Result<Target>> target(String bid) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("fbaby", bid);
        Object body = QQtBuilder.build("58", map);
        return apiServer.target(body).subscribeOn(Schedulers.io()).map(it -> {
            Result<Target> result = new Result<>();
            if (!it.isEmpty()) {
                result.setCode("0");
                result.setMsg("获取成功");
                Target base = it.get(0);
                result.setBody(base);
            } else {
                result.setCode("-1");
                result.setMsg("获取失败");
            }
            return result;
        });
    }
}
