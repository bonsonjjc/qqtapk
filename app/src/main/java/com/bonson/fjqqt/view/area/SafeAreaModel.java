package com.bonson.fjqqt.view.area;

import com.bonson.fjqqt.model.FApiServer;
import com.bonson.qqtapk.model.bean.Baby;
import com.bonson.qqtapk.model.bean.Result;
import com.bonson.qqtapk.model.bean.SafeArea;
import com.bonson.qqtapk.view.ui.area.SafeAreaDataSource;
import com.bonson.qqtapk.model.db.UserDao;
import com.bonson.resource.utils.EncodeUtils;

import java.util.LinkedHashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class SafeAreaModel implements SafeAreaDataSource{
    private FApiServer apiServer;
    Baby baby;

    public SafeAreaModel(FApiServer apiServer,UserDao userDao) {
        this.apiServer = apiServer;
        baby = userDao.baby(userDao.user().getBabyId());
    }

    public Observable<Result<SafeArea>> safeArea(String type) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("action", "CWA008");
        map.put("ftmobile", baby.getFtmobile());
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
        map.put("ftmobile", baby.getFtmobile());
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
