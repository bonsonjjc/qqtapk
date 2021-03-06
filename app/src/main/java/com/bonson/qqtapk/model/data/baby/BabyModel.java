package com.bonson.qqtapk.model.data.baby;

import com.bonson.qqtapk.model.bean.Baby;
import com.bonson.qqtapk.model.bean.Base;
import com.bonson.qqtapk.model.bean.Result;
import com.bonson.qqtapk.model.data.ApiServer;
import com.bonson.qqtapk.model.db.UserDao;
import com.bonson.qqtapk.utils.QQtBuilder;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by zjw on 2018/1/5.
 */

public class BabyModel {
    private ApiServer apiServer;
    private UserDao userDao;

    @Inject
    public BabyModel(ApiServer apiServer, UserDao userDao) {
        this.apiServer = apiServer;
        this.userDao = userDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public Observable<Result<Baby>> bind(String uid, String imei) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("fuser", uid);
        map.put("fimei", imei);
        Object body = QQtBuilder.build("13", map);
        return apiServer.baby(body)
                .subscribeOn(Schedulers.io())
                .map(userBeans -> {
                    Baby baby = userBeans.get(0);
                    Result<Baby> result = new Result<>();
                    if ("0".equals(baby.getFresult())) {
                        result.setCode("0");
                        result.setMsg("绑定成功");
                        userDao.insertBaby(baby);
                    } else {
                        result.setCode("-1");
                        result.setMsg(baby.getMsg());
                    }
                    return result;
                });
    }

    public Observable<Result<List<Baby>>> unbind(String uid, String bid) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("fid", bid);
        map.put("fuser", uid);
        Object body = QQtBuilder.build("28", map);
        return apiServer.baby(body)
                .subscribeOn(Schedulers.io())
                .map(userBeans -> {
                    Baby baby = userBeans.get(0);
                    Result<List<Baby>> result = new Result<>();
                    if ("0".equals(baby.getFresult())) {
                        result.setCode("0");
                        result.setMsg("解绑成功");
                        userDao.deleteBaby(baby);
                        result.setBody(userDao.babies());
                    } else {
                        result.setCode("-1");
                        result.setMsg(baby.getMsg());
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
        return apiServer.baby(body)
                .subscribeOn(Schedulers.io())
                .map(userBeans -> {
                    Baby baby = userBeans.get(0);
                    Result<Baby> result = new Result<>();
                    if ("0".equals(baby.getFresult())) {
                        result.setCode("0");
                        result.setMsg("修改成功");
                        result.setBody(baby);
                        userDao.insertBaby(baby);
                    } else {
                        result.setCode("-1");
                        result.setMsg(baby.getMsg());
                    }
                    return result;
                });
    }

    public Observable<Result<Baby>> changeIMEI(Baby baby) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("fuid", baby.getFuser());
        map.put("fbid", baby.getFid());
        map.put("fimei", baby.getFimei());
        Object body = QQtBuilder.build("44", map);
        return apiServer.base(body)
                .subscribeOn(Schedulers.io())
                .map(babies -> {
                    Base base = babies.get(0);
                    Result<Baby> result = new Result<>();
                    if ("0".equals(base.getFresult())) {
                        result.setCode("0");
                        result.setMsg("切换成功");
                        userDao.insertBaby(baby);
                        result.setBody(baby);
                    } else {
                        result.setCode("-1");
                        result.setMsg(baby.getMsg());
                    }
                    return result;
                });
    }
}
