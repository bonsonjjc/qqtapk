package com.bonson.qqtapk.model.data.setting;

import com.bonson.qqtapk.app.ErrorCode;
import com.bonson.qqtapk.model.bean.Baby;
import com.bonson.qqtapk.model.bean.Base;
import com.bonson.qqtapk.model.bean.Result;
import com.bonson.qqtapk.model.data.ApiServer;
import com.bonson.qqtapk.model.data.TokenServer;
import com.bonson.qqtapk.utils.QQtBuilder;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class SettingModel {
    private ApiServer apiServer;

    TokenServer tokenServer;

    @Inject
    public SettingModel(ApiServer apiServer) {
        this.apiServer = apiServer;
    }

    public void setTokenServer(TokenServer tokenServer) {
        this.tokenServer = tokenServer;
    }

    public Observable<Result<String>> sleepTime(String uid, String bid, String sleepTime) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("fbid", bid);
        map.put("fuid", uid);
        map.put("fsleep", sleepTime);
        Object args = QQtBuilder.build("46", map);
        return apiServer.base(args)
                .subscribeOn(Schedulers.io())
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

    public Observable<Result<String>> token(Baby baby) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("fname", baby.getFaccount());
        map.put("fmobile", baby.getFaccount());
        map.put("portraitUri", baby.getFimg());
        Object args = QQtBuilder.build("101", map);
        return tokenServer.token(args)
                .subscribeOn(Schedulers.io())
                .map(it -> {
                    Result<String> result = new Result<>();
                    if (it.isEmpty()) {
                        result.setCode("-1");
                        result.setMsg("无法连接客服");
                    } else {
                        String token = it.get("token");
                        result.setCode("0");
                        result.setBody(token);
                    }
                    return result;
                });
    }
}
