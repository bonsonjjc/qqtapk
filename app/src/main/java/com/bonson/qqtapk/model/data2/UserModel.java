package com.bonson.qqtapk.model.data2;

import com.bonson.qqtapk.model.bean.Baby;
import com.bonson.qqtapk.model.bean.Result;
import com.bonson.qqtapk.model.bean.User;
import com.bonson.qqtapk.model.bean.UserBean;
import com.bonson.qqtapk.model.db.BabyDao;
import com.bonson.qqtapk.model.db.UserDao;
import com.google.gson.Gson;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class UserModel {
    @Inject
    UserDao userDao;
    @Inject
    BabyDao babyDao;

    private ApiServer apiServer;

    @Inject
    public UserModel(ApiServer apiServer) {
        this.apiServer = apiServer;
    }

    public Observable<Result<User>> login(String mobile, String password, String token, boolean isAuto) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("action", "CWA001");
        map.put("fmobile", mobile);
        map.put("fpasswd", password);
        map.put("ftoken", token);
        Gson gson=new Gson();
        return apiServer.user(gson.toJson(map))
                .subscribeOn(Schedulers.io())
                .map(userBeans -> {
                    UserBean userBean = userBeans.get(0);
                    Result<User> result = new Result<>();
                    if ("0".equals(userBean.getFresult())) {
                        result.setCode("0");
                        result.setMsg("登录成功");
                        Baby.baby = userBean.baby();
                        User user = userBean.user();
                        user.setAuto(isAuto);
                        User.user = user;
                        userDao.insert(user);
                        babyDao.insert(user.getBabyList());
                        result.setBody(user);
                    } else {
                        result.setCode("-1");
                        result.setMsg(userBean.getMsg());
                    }
                    return result;
                });
    }
}
