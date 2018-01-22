package com.bonson.qqtapk.model.data.location;

import android.text.TextUtils;

import com.bonson.qqtapk.app.ErrorCode;
import com.bonson.qqtapk.model.bean.Location;
import com.bonson.qqtapk.model.bean.Result;
import com.bonson.qqtapk.model.data.ApiServer;
import com.bonson.qqtapk.utils.QQtBuilder;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class LocationModel implements LocationModelSource {
    private ApiServer locationServer;

    @Inject
    public LocationModel(ApiServer locationServer) {
        this.locationServer = locationServer;
    }

    @Override
    public Observable<Result<String>> location(String bid) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("fbid", bid);
        Object args = QQtBuilder.build("21", map);
        return locationServer.location(args)
                .subscribeOn(Schedulers.io())
                .map(it -> {
                    Result<String> result = new Result<>();
                    Location location = it.get(0);
                    if ("0".equals(location.getFresult())) {
                        result.setCode("0");
                        result.setMsg("定位请求已发送");
                        result.setBody(location.getFseqid());
                    } else {
                        result.setCode("-1");
                        result.setMsg(ErrorCode.message(location.getFresult()));
                    }
                    return result;
                });
    }

    @Override
    public Observable<Result<Location>> result(String bid, String seqid, int count) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("fbid", bid);
        map.put("fseqid", seqid);
        map.put("fcount", count + "");
        Object args = QQtBuilder.build("43", map);
        return locationServer.location(args)
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
