package com.bonson.fjqqt.view.terminal.timer;

import com.bonson.fjqqt.model.FApiServer;
import com.bonson.fjqqt.view.terminal.timer.Timer;
import com.bonson.qqtapk.model.bean.Base;
import com.bonson.qqtapk.model.bean.Result;
import com.bonson.resource.utils.EncodeUtils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class TimerModel {
    private FApiServer apiServer;

    @Inject
    public TimerModel(FApiServer apiServer) {
        this.apiServer = apiServer;
    }

    public Observable<Result<List<Timer>>> timers(String mobile) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("action", "CWA014");
        map.put("ftmobile", mobile);
        return apiServer.timers(EncodeUtils.encode(map))
                .subscribeOn(Schedulers.io())
                .map(it -> {
                    Result<List<Timer>> result = new Result<>();
                    if (it.isEmpty()) {
                        result.setCode("-1");
                        result.setMsg("没有定时定位");
                    } else {
                        result.setCode("0");
                        result.setBody(it);
                        result.setMsg("获取成功");
                    }
                    return result;
                });
    }

    public Observable<Result<String>> update(String mobile, String fid, String ftimes, String ftype) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("action", "CWA015");
        map.put("ftmobile", mobile);
        map.put("fid", fid);
        map.put("ftimes", ftimes);
        map.put("ftype", ftype);
        return apiServer.timers(EncodeUtils.encode(map))
                .subscribeOn(Schedulers.io())
                .map(it -> {
                    Result<String> result = new Result<>();
                    Base base = it.get(0);
                    if ("100".equals(base.getFresult())) {
                        result.setCode("0");
                        result.setMsg("设置定时定位成功");
                        result.setBody("");
                    } else {
                        result.setCode("-1");
                        result.setMsg(base.getFmsg());
                    }
                    return result;
                });
    }
}
