package com.bonson.qqtapk.model.data.area;

import com.bonson.qqtapk.app.ErrorCode;
import com.bonson.qqtapk.model.bean.Baby;
import com.bonson.qqtapk.model.bean.Result;
import com.bonson.qqtapk.model.bean.SafeArea;
import com.bonson.qqtapk.model.data.ApiServer;
import com.bonson.qqtapk.utils.QQtBuilder;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by jiangjiancheng on 17/12/31.
 */

public class SafeAreaModel implements SafeAreaDataSource {
    private ApiServer areaServer;

    public SafeAreaModel(ApiServer areaServer) {
        this.areaServer = areaServer;
    }

    @Override
    public Observable<Result<SafeArea>> safeArea( String type) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("fbid", Baby.baby.getFid());
        Object body = QQtBuilder.build("04", map);
        return areaServer.safeArea(body)
                .subscribeOn(Schedulers.io())
                .map(r -> {
                    Result<SafeArea> result = new Result<>();
                    if (!r.isEmpty()) {
                        result.setCode("0");
                        result.setBody(r.get(0));
                        result.setMsg("获取成功");
                    } else {
                        result.setCode("-1");
                        result.setMsg(ErrorCode.message("-2"));
                    }
                    return result;
                });
    }

    @Override
    public Observable<Result<SafeArea>> update(SafeArea safeArea) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("fbid", Baby.baby.getFid());
        map.put("fx", safeArea.getFx());
        map.put("fy", safeArea.getFy());
        map.put("fradius", safeArea.getFradius());
        map.put("fstate", safeArea.getFstate());
        Object body = QQtBuilder.build("37", map);
        return areaServer.safeArea(body)
                .subscribeOn(Schedulers.io())
                .map(r -> {
                    Result<SafeArea> result = new Result<>();
                    SafeArea bean = r.get(0);
                    if ("0".equals(bean.getFresult())) {
                        result.setCode("0");
                        result.setBody(bean);
                        result.setMsg("安全区域设置成功");
                    } else {
                        result.setCode("-1");
                        result.setMsg(bean.getMsg());
                    }
                    return result;
                });
    }
}
