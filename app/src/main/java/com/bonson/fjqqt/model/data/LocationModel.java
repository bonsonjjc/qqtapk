package com.bonson.fjqqt.model.data;

import android.text.TextUtils;

import com.bonson.fjqqt.model.FApiServer;
import com.bonson.qqtapk.model.bean.Location;
import com.bonson.qqtapk.model.bean.Result;
import com.bonson.qqtapk.model.data.location.LocationModelSource;
import com.bonson.resource.utils.EncodeUtils;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class LocationModel implements LocationModelSource {
    private FApiServer apiServer;

    @Inject
    public LocationModel(FApiServer apiServer) {
        this.apiServer = apiServer;
    }

    public Observable<Result<String>> location(String mobile) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("action", "CWA003");
        map.put("flmobile", mobile);
        String json = EncodeUtils.encode(map);
        return apiServer.location(json)
                .subscribeOn(Schedulers.io())
                .map(it -> {
                    Result<String> result = new Result<>();
                    Location location = it.get(0);
                    if ("100".equals(location.getFresult())) {
                        result.setCode("0");
                        result.setMsg("定位请求已发送");
                        result.setBody(location.getFseqid());
                    } else {
                        result.setCode("-1");
                        result.setMsg(location.getFid());
                    }
                    return result;
                });
    }

    public Observable<Result<Location>> result(String fid, String seqid, int count) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("fid", fid);
        map.put("action", "CWA004");
        String json = EncodeUtils.encode(map);
        return apiServer.location(json)
                .subscribeOn(Schedulers.io())
                .map(it -> {
                    Result<Location> result = new Result<>();
                    Location location = it.get(0);
                    result.setBody(location);
                    result.setCode("1");
                    if (!TextUtils.isEmpty(location.getFseqid()) && !seqid.equals(location.getFseqid())) {
                        result.setCode("0");
                        result.setMsg("定位成功");
                        return result;
                    }
                    if (count == 5) {
                        result.setCode("-1");
                        result.setMsg("定位失败");
                    }
                    return result;
                });
    }
}
