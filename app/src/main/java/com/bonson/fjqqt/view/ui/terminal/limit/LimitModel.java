package com.bonson.fjqqt.view.ui.terminal.limit;

import com.bonson.fjqqt.model.FApiServer;
import com.bonson.qqtapk.model.bean.Limit;
import com.bonson.qqtapk.model.bean.Result;
import com.bonson.qqtapk.utils.QQtBuilder;
import com.bonson.resource.utils.EncodeUtils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class LimitModel {
    private FApiServer server;

    @Inject
    public LimitModel(FApiServer server) {
        this.server = server;
    }

    public Observable<Result<List<Limit>>> limits(String bid) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("fbid", bid);
        map.put("action", "");
        return server.limits(EncodeUtils.encode(map))
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
}
