package com.bonson.qqtapk.model.data.limit;

import com.bonson.qqtapk.app.ErrorCode;
import com.bonson.qqtapk.model.bean.Limit;
import com.bonson.qqtapk.model.bean.Result;
import com.bonson.qqtapk.model.data.ApiServer;
import com.bonson.qqtapk.utils.QQtBuilder;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by jiangjiancheng on 18/1/4.
 */

public class LimitModel {
    private ApiServer server;

    @Inject
    public LimitModel(ApiServer limitServer) {
        server = limitServer;
    }

    public Observable<Result<List<Limit>>> limits(String bid) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("fbid", bid);
        Object args = QQtBuilder.build("49", map);
        return server.limits(args)
                .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(limits -> {
                    Result<List<Limit>> result = new Result<>();
                    if (limits.isEmpty()) {
                        result.setCode("-1");
                        result.setMsg("没有呼入限制");
                    } else {
                        result.setCode("0");
                        result.setBody(limits);
                        result.setMsg("获取成功");
                    }
                    return result;
                });
    }

    public Observable<Result<Limit>> updateState(String bid, String userId, String state) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("fid", bid);
        map.put("fuser", userId);
        map.put("fcstate", state);
        Object args = QQtBuilder.build("51", map);
        return server.limits(args)
                .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(limits -> {
                    Result<Limit> result = new Result<>();
                    Limit inLimit = limits.get(0);
                    if ("0".equals(inLimit.getFresult())) {
                        result.setCode("0");
                        result.setBody(inLimit);
                        result.setMsg("修改呼入限制状态成功");
                    } else {
                        result.setCode("-1");
                        result.setMsg(ErrorCode.message(inLimit.getFresult()));
                    }
                    return result;
                });
    }

    public Observable<Result<Limit>> update(Limit limit) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("fid", limit.getFid());
        map.put("fbid", limit.getFbid());
        map.put("fname", limit.getFname());
        map.put("fmobile", limit.getFmobile());
        map.put("fbegin", limit.getFbegin());
        map.put("fend", limit.getFend());
        map.put("ffbegin", limit.getFfbegin());
        map.put("ffend", limit.getFfend());
        map.put("fcstate", limit.getFcstate());
        map.put("foptype", "3");
        Object args = QQtBuilder.build("48", map);
        return server.limits(args)
                .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(limits -> {
                    Result<Limit> result = new Result<>();
                    Limit inLimit = limits.get(0);
                    if ("0".equals(inLimit.getFresult())) {
                        result.setCode("0");
                        result.setBody(inLimit);
                        result.setMsg("修改呼入限制成功");
                    } else {
                        result.setCode("-1");
                        result.setMsg(ErrorCode.message(inLimit.getFresult()));
                    }
                    return result;
                });
    }


    public Observable<Result<Limit>> delete(Limit limit) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("fid", limit.getFid());
        map.put("fbid", limit.getFbid());
        map.put("foptype", "2");
        Object args = QQtBuilder.build("48", map);
        return server.limits(args)
                .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(limits -> {
                    Result<Limit> result = new Result<>();
                    Limit inLimit = limits.get(0);
                    if ("0".equals(inLimit.getFresult())) {
                        result.setCode("0");
                        result.setBody(inLimit);
                        result.setMsg("删除呼入限制成功");
                    } else {
                        result.setCode("-1");
                        result.setMsg(ErrorCode.message(inLimit.getFresult()));
                    }
                    return result;
                });
    }

    public Observable<Result<Limit>> add(Limit limit) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("fbid", limit.getFbid());
        map.put("fname", limit.getFname());
        map.put("fmobile", limit.getFmobile());
        map.put("fbegin", limit.getFbegin());
        map.put("fend", limit.getFend());
        map.put("ffbegin", limit.getFfbegin());
        map.put("ffend", limit.getFfend());
        map.put("fcstate", limit.getFcstate());
        map.put("foptype", "1");
        Object args = QQtBuilder.build("48", map);
        return server.limits(args)
                .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(limits -> {
                    Result<Limit> result = new Result<>();
                    Limit inLimit = limits.get(0);
                    if ("0".equals(inLimit.getFresult())) {
                        result.setCode("0");
                        result.setBody(inLimit);
                        result.setMsg("添加呼入限制成功");
                    } else {
                        result.setCode("-1");
                        result.setMsg(ErrorCode.message(inLimit.getFresult()));
                    }
                    return result;
                });
    }
}
