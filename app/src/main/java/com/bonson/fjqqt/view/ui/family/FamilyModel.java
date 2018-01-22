package com.bonson.fjqqt.view.ui.family;

import com.bonson.fjqqt.model.FApiServer;
import com.bonson.qqtapk.model.bean.Baby;
import com.bonson.qqtapk.model.bean.Family;
import com.bonson.qqtapk.model.bean.Result;
import com.bonson.qqtapk.model.data.family.FamilyDataSource;
import com.bonson.resource.utils.EncodeUtils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by zjw on 2018/1/3.
 */

public class FamilyModel implements FamilyDataSource {
    private FApiServer apiServer;

    @Inject
    public FamilyModel(FApiServer apiServer) {
        this.apiServer = apiServer;
    }

    public Observable<Result<List<Family>>> families() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("action", "CWA010");
        map.put("ftmobile", Baby.baby.getFtmobile());
        return apiServer.families(EncodeUtils.encode(map))
                .subscribeOn(Schedulers.io())
                .map(it -> {
                    Result<List<Family>> result = new Result<>();
                    if (it.isEmpty()) {
                        result.setCode("-1");
                        result.setMsg("没有亲情号码");
                    } else {
                        result.setBody(it);
                        result.setCode("0");
                        result.setMsg("获取成功");
                    }
                    return result;
                });
    }

    public Observable<Result<Family>> update(Family family) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("action", "CWA011");
        map.put("ftmobile", Baby.baby.getFtmobile());
        map.put("fkey", family.getFkey());
        map.put("fmobile", family.getFmobile());
        map.put("fname", family.getFname());
        return apiServer.families(EncodeUtils.encode(map))
                .subscribeOn(Schedulers.io())
                .map(it -> {
                    Family bean = it.get(0);
                    Result<Family> result = new Result<>();
                    if ("100".equals(bean.getFresult())) {
                        result.setCode("0");
                        result.setBody(bean);
                        result.setMsg("设置成功");
                    } else {
                        result.setCode("-1");
                        result.setBody(bean);
                        result.setMsg(bean.getFmsg());
                    }
                    return result;
                });
    }

}
