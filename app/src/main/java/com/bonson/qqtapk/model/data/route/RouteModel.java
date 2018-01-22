package com.bonson.qqtapk.model.data.route;

import com.bonson.qqtapk.model.bean.Result;
import com.bonson.qqtapk.model.bean.Route;
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
 * Created by jiangjiancheng on 18/1/4.
 */

public class RouteModel implements RouteDataSource {
    ApiServer routeServer;

    @Inject
    public RouteModel(ApiServer routeServer) {
        this.routeServer = routeServer;
    }

    public Observable<Result<List<Route>>> routes(String bid, String start, String end) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("fbid", bid);
        map.put("start", start);
        map.put("end", end);
        Object args = QQtBuilder.build("35", map);
        return routeServer.routes(args)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(routes -> {
                    Result<List<Route>> result = new Result<>();
                    if (routes.isEmpty()) {
                        result.setCode("-1");
                        result.setMsg("没有路径");
                    } else {
                        result.setCode("0");
                        result.setBody(routes);
                        result.setMsg("获取成功");
                    }
                    return result;
                });
    }
}
