package com.bonson.fjqqt.model.data;

import com.bonson.fjqqt.model.FApiServer;
import com.bonson.fjqqt.model.bean.Route;
import com.bonson.fjqqt.model.bean.RouteTime;
import com.bonson.qqtapk.model.bean.Base;
import com.bonson.qqtapk.model.bean.Result;
import com.bonson.resource.utils.EncodeUtils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RouteModel {
    private FApiServer routeServer;

    @Inject
    public RouteModel(FApiServer routeServer) {
        this.routeServer = routeServer;
    }

    public Observable<Result<List<Route>>> times(String mobile) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("action", "CWA005");
        map.put("ftmobile", mobile);
        String json = EncodeUtils.encode(map);
        return routeServer.routes(json)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(routes -> {
                    Result<List<Route>> result = new Result<>();
                    if (routes.isEmpty()) {
                        result.setCode("-1");
                        result.setMsg("没有路径时间列表");
                    } else {
                        result.setCode("0");
                        result.setBody(routes);
                        result.setMsg("获取成功");
                    }
                    return result;
                });
    }

    public Observable<Result<List<Route>>> routes(String mobile, String time) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("action", "CWA006");
        map.put("ftmobile", mobile);
        map.put("fctime", time);
        String json = EncodeUtils.encode(map);
        return routeServer.routes(json)
                .subscribeOn(Schedulers.io())
                .map(routes -> {
                    Result<List<Route>> result = new Result<>();
                    if (routes.isEmpty()) {
                        result.setCode("-1");
                        result.setMsg("没有路径记录");
                    } else {
                        result.setCode("0");
                        result.setBody(routes);
                        result.setMsg("获取成功");
                    }
                    return result;
                });
    }

    public Observable<Result<RouteTime>> addTime(RouteTime time) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("action", "CWA007");
        map.put("ftmobile", time.getFtmobile());
        map.put("fbeghours", time.getFbeghours());
        map.put("fbegminutes", time.getFbegminutes());
        map.put("fendhours", time.getFendhours());
        map.put("fendminutes", time.getFendminutes());
        map.put("fstate", time.getFstate());
        map.put("ftype", time.getFtype());
        String json = EncodeUtils.encode(map);
        return routeServer.base(json)
                .subscribeOn(Schedulers.io())
                .map(baseList -> {
                    Result<RouteTime> result = new Result<>();
                    Base userBean = baseList.get(0);
                    if ("100".equals(userBean.getFresult())) {
                        result.setCode("0");
                        result.setBody(time);
                        result.setMsg("添加路径时间成功");
                    } else {
                        result.setCode("-1");
                        result.setMsg(userBean.getMsg());
                    }
                    return result;
                });
    }

    public Observable<Result<List<Route>>> routeTimes2(String mobile) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("action", "CWA021");
        map.put("ftmobile", mobile);
        String json = EncodeUtils.encode(map);
        return routeServer.routes(json)
                .subscribeOn(Schedulers.io())
                .map(routes -> {
                    Result<List<Route>> result = new Result<>();
                    if (routes.isEmpty()) {
                        result.setCode("-1");
                        result.setMsg("没有路径记录");
                    } else {
                        result.setCode("0");
                        result.setBody(routes);
                        result.setMsg("获取成功");
                    }
                    return result;
                });
    }
}
