package com.bonson.qqtapk.model.data.index;

import com.bonson.qqtapk.app.ErrorCode;
import com.bonson.qqtapk.model.bean.Base;
import com.bonson.qqtapk.model.bean.Device;
import com.bonson.qqtapk.model.bean.Location;
import com.bonson.qqtapk.model.bean.Result;
import com.bonson.qqtapk.model.data.ApiServer;
import com.bonson.qqtapk.utils.QQtBuilder;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class IndexModel {
    private ApiServer indexServer;

    @Inject
    public IndexModel(ApiServer indexServer) {
        this.indexServer = indexServer;
    }

    public Observable<Result<Location>> device(String bid) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("fbid", bid);
        Object args = QQtBuilder.build("29", map);
        return indexServer.device(args)
                .subscribeOn(Schedulers.io())
                .map(it -> {
                    Result<Location> result = new Result<>();
                    if (it.getTernimal().isEmpty()) {
                        result.setCode("-1");
                        result.setMsg("获取设备信息失败");
                    } else {
                        result.setMsg("获取设备信息成功");
                        Device.device = it.getTernimal().get(0);
                        Location location = it.getLocation().get(0);
                        result.setCode("0");
                        result.setBody(location);
                    }
                    return result;
                });
    }

    public Observable<Result<Boolean>> listener(String bid, String user) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("fid", bid);
        map.put("fuser", user);
        Object args = QQtBuilder.build("32", map);
        return indexServer.base(args)
                .subscribeOn(Schedulers.io())
                .map(it -> {
                    Result<Boolean> result = new Result<>();
                    Base base = it.get(0);
                    if ("0".equals(base.getFresult())) {
                        result.setCode("0");
                        result.setMsg("监听请求已发送");
                        result.setBody(true);
                    } else {
                        result.setCode("-1");
                        result.setMsg(ErrorCode.message(base.getFresult()));
                        result.setBody(false);
                    }
                    return result;
                });
    }

    public Observable<Result<Boolean>> lockMachine(String bid, String user) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("fbid", bid);
        map.put("fuid", user);
        Object args = QQtBuilder.build("33", map);
        return indexServer.base(args)
                .subscribeOn(Schedulers.io())
                .map(it -> {
                    Result<Boolean> result = new Result<>();
                    Base base = it.get(0);
                    if ("0".equals(base.getFresult())) {
                        result.setCode("0");
                        result.setMsg("锁机请求已发送");
                        result.setBody(true);
                    } else {
                        result.setCode("-1");
                        result.setMsg(ErrorCode.message(base.getFresult()));
                        result.setBody(false);
                    }
                    return result;
                });
    }
}
