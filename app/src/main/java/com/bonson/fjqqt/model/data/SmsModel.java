package com.bonson.fjqqt.model.data;

import com.bonson.fjqqt.model.FApiServer;
import com.bonson.fjqqt.model.bean.Sms;
import com.bonson.qqtapk.model.bean.Result;
import com.bonson.resource.utils.EncodeUtils;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class SmsModel {
    private FApiServer apiServer;

    @Inject
    public SmsModel(FApiServer apiServer) {
        this.apiServer = apiServer;
    }

    public Observable<Result<Sms>> timers(String mobile) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("action", "CWA023");
        map.put("fmobile", mobile);
        map.put("ftype", "3");
        return apiServer.sms(EncodeUtils.encode(map))
                .subscribeOn(Schedulers.io())
                .map(it -> {
                    Result<Sms> result = new Result<>();
                    Sms sms = it.get(0);
                    return result;
                });
    }

    public Observable<Result<Sms>> timers(Sms sms) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("action", "CWA023");
        map.put("fmobile", sms.getFtmobile());
        map.put("fmsgid", sms.getFmsgid());
        map.put("ftype", "1");
        return apiServer.sms(EncodeUtils.encode(map))
                .subscribeOn(Schedulers.io())
                .map(it -> {
                    Result<Sms> result = new Result<>();
                    Sms temp = it.get(0);
                    if ("100".equals(temp.getFresult())) {
                        result.setCode("0");
                        result.setBody(sms);
                        result.setMsg("设置成功");
                    } else {
                        result.setMsg(temp.getFmsg());
                        result.setCode("-1");
                    }
                    return result;
                });
    }

}
