package com.bonson.fjqqt.view.ui.terminal.alarm;

import com.bonson.fjqqt.model.FApiServer;
import com.bonson.fjqqt.view.ui.terminal.alarm.Alarm;
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


    public Observable<Result<String>> update(String mobile,List<Alarm> alarms) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("action", "CWA020");
        map.put("ftmobile", mobile);
        /*map.put("ids", ftype);
        map.put("fcycle", fid);
        map.put("fstate", ftimes);
        map.put("ftimes", ftype);
        map.put("pstate", ftype);
        map.put("fcontent", ftype);
        map.put("ftype", ftype);*/
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
