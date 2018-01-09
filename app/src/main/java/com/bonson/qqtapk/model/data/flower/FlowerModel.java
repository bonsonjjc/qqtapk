package com.bonson.qqtapk.model.data.flower;

import com.bonson.qqtapk.app.ErrorCode;
import com.bonson.qqtapk.model.bean.Flower;
import com.bonson.qqtapk.model.bean.Result;
import com.bonson.qqtapk.utils.QQtBuilder;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by zjw on 2018/1/4.
 */

public class FlowerModel {
    private FlowerServer flowerServer;

    @Inject
    public FlowerModel(FlowerServer flowerServer) {
        this.flowerServer = flowerServer;
    }

    public Observable<Result<List<Flower>>> flowers(String id, int start, int pagerSize) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("fbid", id);
        map.put("start", start + "");
        map.put("end", pagerSize + "");
        Object body = QQtBuilder.build("10", map);
        return flowerServer.flowers(body)
                .subscribeOn(Schedulers.io())
                .map(it -> {
                    Result<List<Flower>> result = new Result<>();
                    if (it.isEmpty()) {
                        if (start == 0) {
                            result.setCode("-1");
                            result.setMsg("没有红花记录");
                        } else {
                            result.setCode("-2");
                            result.setMsg("已全部加载");
                        }

                    } else {
                        result.setBody(it);
                        result.setCode("0");
                        result.setMsg("获取成功");
                    }
                    return result;
                });
    }

    public Observable<Result<Flower>> pull(Flower flower) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("fbid", flower.getFid());
        map.put("fnum", flower.getFnum());
        map.put("fdesc", flower.getFdesc());
        map.put("fctime", flower.getFctime());
        map.put("ftype", flower.getFtype());
        Object body = QQtBuilder.build("34", map);
        return flowerServer.flowers(body)
                .subscribeOn(Schedulers.io())
                .map(it -> {
                    Result<Flower> result = new Result<>();
                    Flower r = it.get(0);
                    if ("0".equals(r.getFresult())) {
                        result.setBody(flower);
                        result.setCode("0");
                        result.setMsg("成功");
                    } else {
                        result.setCode("-1");
                        result.setMsg(ErrorCode.message(r.getFresult()));
                    }
                    return result;
                });
    }
}
