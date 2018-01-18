package com.bonson.qqtapk.model.data.family;

import com.bonson.qqtapk.app.ErrorCode;
import com.bonson.qqtapk.model.bean.Baby;
import com.bonson.qqtapk.model.bean.Family;
import com.bonson.qqtapk.model.bean.Result;
import com.bonson.qqtapk.model.data.ApiServer;
import com.bonson.qqtapk.utils.QQtBuilder;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by zjw on 2018/1/3.
 */

public class FamilyModel implements FamilyModelDataSource {
    private ApiServer familyServer;

    @Inject
    public FamilyModel(ApiServer familyServer) {
        this.familyServer = familyServer;
    }

    @Override
    public Observable<Result<List<Family>>> families() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("fbid", Baby.baby.getFid());
        Object body = QQtBuilder.build("03", map);
        return familyServer.families(body)
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

    @Override
    public Observable<Result<Family>> update(Family family) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("fbid", Baby.baby.getFid());
        map.put("fkey", family.getFkey());
        map.put("fmobile", family.getFmobile());
        map.put("fname", family.getFname());
        Object body = QQtBuilder.build("15", map);
        return familyServer.families(body)
                .subscribeOn(Schedulers.io())
                .map(it -> {
                    Family bean = it.get(0);
                    Result<Family> result = new Result<>();
                    if ("0".equals(bean.getFresult())) {
                        result.setCode("0");
                        result.setBody(bean);
                        result.setMsg("设置成功");
                    } else {
                        result.setCode("-1");
                        result.setBody(bean);
                        result.setMsg(ErrorCode.message(bean.getFresult()));
                    }
                    return result;
                });
    }

}
