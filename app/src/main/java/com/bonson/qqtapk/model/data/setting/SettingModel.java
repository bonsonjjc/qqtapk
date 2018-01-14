package com.bonson.qqtapk.model.data.setting;

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

public class SettingModel {
    private ApiServer apiServer;

    @Inject
    public SettingModel(ApiServer apiServer) {
        this.apiServer = apiServer;
    }

    public Observable<Result<String>> sleepTime(String uid, String bid, String sleepTime) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("fbid", bid);
        map.put("fuid", uid);
        map.put("fsleep", sleepTime);
        Object args = QQtBuilder.build("46", map);
        return apiServer.base(args).subscribeOn(Schedulers.io())
                .map(it -> {
                    Result<String> result = new Result<>();
                    Base bean = it.get(0);
                    if ("0".equals(bean.getFresult())) {
                        result.setCode("0");
                        result.setMsg("设置休眠时间成功");
                        result.setBody(sleepTime);
                    } else {
                        result.setCode("-1");
                        result.setMsg(ErrorCode.message(bean.getFresult()));
                    }
                    return result;
                });
    }
}
