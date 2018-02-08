package com.bonson.qqtapk.model.data.index;

import com.bonson.fjqqt.model.FApiServer;
import com.bonson.qqtapk.model.bean.Baby;
import com.bonson.qqtapk.model.bean.Result;
import com.bonson.qqtapk.model.bean.UserBean;
import com.bonson.qqtapk.model.data.ApiServer;
import com.bonson.qqtapk.utils.QQtBuilder;
import com.bonson.resource.utils.EncodeUtils;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class IndexModel {
    private FApiServer apiServer;

    @Inject
    public IndexModel(FApiServer apiServer) {
        this.apiServer = apiServer;
    }


    public Observable<Result<Baby>> switchBaby(String bid) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("action", "CWA018");
        map.put("fbid", bid);
        return apiServer.user(EncodeUtils.encode(map))
                .subscribeOn(Schedulers.io())
                .map(it -> {
                    Result<Baby> result = new Result<>();
                    UserBean baby = it.get(0);
                    if ("100".equals(baby.getFresult())) {
                        result.setCode("0");
                        result.setMsg("切换宝贝成功");
                        result.setBody(baby.baby());
                    } else {
                        result.setCode("-1");
                        result.setMsg(baby.getFmsg());
                    }
                    return result;
                });
    }
}
