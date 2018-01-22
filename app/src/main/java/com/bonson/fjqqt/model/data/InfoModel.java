package com.bonson.fjqqt.model.data;

import com.bonson.fjqqt.model.FApiServer;
import com.bonson.fjqqt.model.bean.Info;
import com.bonson.qqtapk.model.bean.Base;
import com.bonson.qqtapk.model.bean.Result;
import com.bonson.resource.utils.EncodeUtils;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class InfoModel {
    private FApiServer apiServer;

    @Inject
    public InfoModel(FApiServer apiServer) {
        this.apiServer = apiServer;
    }

    public Observable<Result<Info>> info(String mobile) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("action", "CWA016");
        map.put("ftmobile", mobile);
        return apiServer.info(EncodeUtils.encode(map))
                .subscribeOn(Schedulers.io())
                .map(it -> {
                    Result<Info> result = new Result<>();
                    Info base = it.get(0);
                    if ("100".equals(base.getFresult())) {
                        result.setCode("0");
                        result.setBody(base);
                        result.setMsg("获取成功");
                    } else {
                        result.setCode("-1");
                        result.setMsg(base.getFmsg());
                    }
                    return result;
                });
    }

    public Observable<Result<String>> update(Info info) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("action", "CWA017");
        map.put("ftmobile", info.getFtmobile());
        map.put("ftname", info.getFtname());
        map.put("fsex", info.getFsex());
        map.put("fbirthday", info.getFbirthday());
        map.put("fheight", info.getFheight());
        map.put("fweight", info.getFweight());

        return apiServer.base(EncodeUtils.encode(map))
                .subscribeOn(Schedulers.io())
                .map(it -> {
                    Result<String> result = new Result<>();
                    Base base = it.get(0);
                    if ("100".equals(base.getFresult())) {
                        result.setCode("0");
                        result.setMsg("修改成功");
                    } else {
                        result.setCode("-1");
                        result.setMsg(base.getFmsg());
                    }
                    return result;
                });
    }
}
