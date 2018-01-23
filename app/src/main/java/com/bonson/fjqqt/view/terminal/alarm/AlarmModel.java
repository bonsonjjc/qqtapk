package com.bonson.fjqqt.view.terminal.alarm;

import com.bonson.fjqqt.model.FApiServer;
import com.bonson.fjqqt.view.terminal.alarm.Alarm;
import com.bonson.qqtapk.model.bean.Base;
import com.bonson.qqtapk.model.bean.Result;
import com.bonson.resource.utils.EncodeUtils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class AlarmModel {
    private FApiServer apiServer;

    @Inject
    public AlarmModel(FApiServer apiServer) {
        this.apiServer = apiServer;
    }

    public Observable<Result<List<Alarm>>> alarms(String mobile) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("action", "CWA019");
        map.put("ftmobile", mobile);
        return apiServer.alarms(EncodeUtils.encode(map))
                .subscribeOn(Schedulers.io())
                .map(it -> {
                    Result<List<Alarm>> result = new Result<>();
                    if (it.isEmpty()) {
                        result.setCode("-1");
                        result.setMsg("没有生活提醒");
                    } else {
                        result.setCode("0");
                        result.setBody(it);
                        result.setMsg("获取成功");
                    }
                    return result;
                });
    }


    public Observable<Result<Alarm>> update(String type, Alarm alarm) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("action", "CWA020");
        map.put("ftmobile", alarm.getFtmobile());
        if (!type.equals("1")) {
            map.put("ids", alarm.getFid());
        }
        map.put("fcycle", alarm.getFcycle());
        map.put("fstate", alarm.getFstate());
        map.put("ftimes", alarm.getFtimes());
        map.put("fcontent", alarm.getFcontent());
        map.put("ftype", type);
        return apiServer.alarms(EncodeUtils.encode(map))
                .subscribeOn(Schedulers.io())
                .map(it -> {
                    Result<Alarm> result = new Result<>();
                    Alarm base = it.get(0);
                    result.setMsg(base.getFmsg());
                    if ("100".equals(base.getFresult())) {
                        result.setCode("0");
                        result.setBody(base);
                    } else {
                        result.setCode("-1");
                    }
                    return result;
                });
    }
}
