package com.bonson.qqtapk.model.data.ring;

import com.bonson.qqtapk.app.ErrorCode;
import com.bonson.qqtapk.model.bean.Base;
import com.bonson.qqtapk.model.bean.Result;
import com.bonson.qqtapk.model.data.ApiServer;
import com.bonson.qqtapk.utils.QQtBuilder;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by jiangjiancheng on 18/1/8.
 */

public class RingModel {
    private ApiServer ringServer;

    @Inject
    public RingModel(ApiServer ringServer) {
        this.ringServer = ringServer;
    }

    public Observable<Result<Base>> ring(String fbid, String fuser, String caller, String callVolume, String callerVolume) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("fbid", fbid);
        map.put("fuid", fuser);
        map.put("fcaller", caller);
        map.put("fcvolume", callVolume);
        map.put("fivolume", callerVolume);
        Object args = QQtBuilder.build("47", map);
        return ringServer.base(args)
                .subscribeOn(Schedulers.io())
                .map(it -> {
                    Result<Base> result = new Result<>();
                    Base base = it.get(0);
                    if ("0".equals(base.getFresult())) {
                        result.setBody(base);
                        result.setCode("0");
                        result.setMsg("铃声设置成功");
                    } else {
                        result.setCode("-1");
                        result.setMsg(ErrorCode.message(base.getFresult()));
                    }
                    return result;
                });
    }
}
