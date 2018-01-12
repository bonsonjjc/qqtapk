package com.bonson.qqtapk.model.data.baby;

import com.bonson.qqtapk.app.ErrorCode;
import com.bonson.qqtapk.model.bean.Baby;
import com.bonson.qqtapk.model.bean.Result;
import com.bonson.qqtapk.model.bean.User;
import com.bonson.qqtapk.model.bean.UserBean;
import com.bonson.qqtapk.model.data.ApiServer;
import com.bonson.qqtapk.model.db.BabyDao;
import com.bonson.qqtapk.utils.QQtBuilder;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by zjw on 2018/1/5.
 */

public class BabyModel {
    private ApiServer babyServer;
    private BabyDao babyDao;

    @Inject
    public BabyModel(ApiServer babyServer, BabyDao babyDao) {
        this.babyServer = babyServer;
        this.babyDao = babyDao;
    }

    public Observable<Result<Baby>> bind(String uid, String imei) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("fuser", uid);
        map.put("fimei", imei);
        Object body = QQtBuilder.build("13", map);
        return babyServer.baby(body)
                .subscribeOn(Schedulers.io())
                .map(userBeans -> {
                    Baby baby = userBeans.get(0);
                    Result<Baby> result = new Result<>();
                    if ("0".equals(baby.getFresult())) {
                        result.setCode("0");
                        result.setMsg("绑定成功");
                        babyDao.insert(baby);
                    } else {
                        result.setCode("-1");
                        result.setMsg(ErrorCode.message(baby.getFresult()));
                    }
                    return result;
                });
    }

    public Observable<Result<Baby>> unbind(String uid, String bid) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("fid", uid);
        map.put("fuser", uid);
        Object body = QQtBuilder.build("28", map);
        return babyServer.baby(body)
                .subscribeOn(Schedulers.io())
                .map(userBeans -> {
                    Baby baby = userBeans.get(0);
                    Result<Baby> result = new Result<>();
                    if ("0".equals(baby.getFresult())) {
                        result.setCode("0");
                        result.setMsg("解绑成功");
                        babyDao.delete(baby);
                    } else {
                        result.setCode("-1");
                        result.setMsg(ErrorCode.message(baby.getFresult()));
                    }
                    return result;
                });
    }

    public Observable<Result<Baby>> update(Baby update) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("fid", update.getFid());
        map.put("fuser", update.getFuser());
        map.put("fimg", update.getFimg());
        map.put("fname", update.getFname());
        map.put("frelative", update.getFrelative());
        map.put("fsex", update.getFsex());
        map.put("fbirth", update.getFbirth());
        map.put("fareaname", update.getFareaname());
        map.put("farea", update.getFarea());
        map.put("fprovince", update.getFprovince());
        map.put("fcity", update.getFcity());
        map.put("ftmobile", update.getFtmobile());
        map.put("fheight", update.getFheight());
        map.put("fweight", update.getFweight());
        Object body = QQtBuilder.build("22", map);
        return babyServer.baby(body)
                .subscribeOn(Schedulers.io())
                .map(userBeans -> {
                    Baby baby = userBeans.get(0);
                    Result<Baby> result = new Result<>();
                    if ("0".equals(baby.getFresult())) {
                        result.setCode("0");
                        result.setMsg("修改成功");
                        babyDao.update(baby);
                    } else {
                        result.setCode("-1");
                        result.setMsg(ErrorCode.message(baby.getFresult()));
                    }
                    return result;
                });
    }

    public Baby getLocal(String bid) {
        return babyDao.getById(bid);
    }

    public Observable<Result<Baby>> getBaby(String bid) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("fmobile", User.user.getMobile());
        map.put("fpasswd", User.user.getPassword());
        map.put("fbid", bid);
        map.put("ftoken", User.user.getToken() + "");
        Object body = QQtBuilder.build("01", map);
        return babyServer.user(body)
                .subscribeOn(Schedulers.io())
                .map(babies -> {
                    UserBean baby = babies.get(0);
                    Result<Baby> result = new Result<>();
                    if ("0".equals(baby.getFresult())) {
                        result.setCode("0");
                        result.setMsg("切换成功");
                        Baby r=baby.baby();
                        babyDao.update(r);
                        result.setBody(r);
                    } else {
                        result.setCode("-1");
                        result.setMsg(baby.getMsg());
                    }
                    return result;
                });
    }

    public Flowable<List<Baby>> list(String userId) {
        return babyDao.getByUserId(userId)
                .subscribeOn(Schedulers.io());
    }
}
