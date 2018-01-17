package com.bonson.fjqqt.model.data;

import com.bonson.fjqqt.model.FApiServer;
import com.bonson.qqtapk.model.bean.Result;
import com.bonson.qqtapk.model.bean.SafeArea;
import com.bonson.resource.utils.EncodeUtils;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class SafeAreaModel {
    private FApiServer apiServer;

    @Inject
    public SafeAreaModel(FApiServer apiServer) {
        this.apiServer = apiServer;
    }

    public Observable<Result<SafeArea>> safeArea(String mobile, String type) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("action", "CWA008");
        map.put("ftmobile", mobile);
        map.put("ftype", type);
        return apiServer.safeArea(EncodeUtils.encode(map))
                .subscribeOn(Schedulers.io())
                .map(r -> {
                    Result<SafeArea> result = new Result<>();
                    SafeArea bean = r.get(0);
                    if ("100".equals(bean.getFresult())) {
                        result.setCode("0");
                        result.setBody(bean);
                        result.setMsg("获取成功");
                    } else {
                        result.setCode("-1");
                        result.setMsg(bean.getFmsg());
                    }
                    return result;
                });
    }

    public Observable<Result<SafeArea>> update(SafeArea safeArea) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("action", "CWA009");
        map.put("ftmobile", safeArea.getFbid());
        map.put("ftype", safeArea.getFtype());
        map.put("fx", safeArea.getFx());
        map.put("fy", safeArea.getFy());
        map.put("fradius", safeArea.getFradius());
        map.put("fstate", safeArea.getFstate());
        return apiServer.safeArea(EncodeUtils.encode(map))
                .subscribeOn(Schedulers.io())
                .map(r -> {
                    Result<SafeArea> result = new Result<>();
                    SafeArea bean = r.get(0);
                    if ("100".equals(bean.getFresult())) {
                        result.setCode("0");
                        result.setBody(bean);
                        result.setMsg("安全区域设置成功");
                    } else {
                        result.setCode("-1");
                        result.setMsg(bean.getFmsg());
                    }
                    return result;
                });
    }
}
