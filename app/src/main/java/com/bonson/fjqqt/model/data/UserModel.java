package com.bonson.fjqqt.model.data;

import com.bonson.fjqqt.model.FApiServer;
import com.bonson.qqtapk.model.bean.Baby;
import com.bonson.qqtapk.model.bean.Result;
import com.bonson.qqtapk.model.bean.User;
import com.bonson.qqtapk.model.bean.UserBean;
import com.bonson.qqtapk.model.db.BabyDao;
import com.bonson.qqtapk.model.db.UserDao;
import com.bonson.resource.utils.EncodeUtils;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class UserModel {
    private FApiServer apiServer;
    private UserDao userDao;
    private BabyDao babyDao;

    @Inject
    public UserModel(FApiServer apiServer, UserDao userDao, BabyDao babyDao) {
        this.apiServer = apiServer;
        this.userDao = userDao;
        this.babyDao = babyDao;
    }

    public User getUser() {
        return userDao.userFirst();
    }

    public Observable<Result<User>> login(String mobile, String password, String token, boolean isAuto) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("action", "CWA001");
        map.put("fmobile", mobile);
        map.put("fpasswd", password);
        map.put("ftoken", token);
        return apiServer.user(EncodeUtils.encode(map))
                .subscribeOn(Schedulers.io())
                .map(userBeans -> {
                    UserBean userBean = userBeans.get(0);
                    Result<User> result = new Result<>();
                    if ("100".equals(userBean.getFresult())) {
                        result.setCode("0");
                        result.setMsg("登录成功");
                        Baby.baby = userBean.baby();
                        User user = userBean.user();
                        user.setAuto(isAuto);
                        User.user = user;
                        userDao.delete(userDao.user());
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


    public Observable<Result<Baby>> baby(String bid) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("action", "CWA018");
        map.put("fbid", bid);
        return apiServer.user(EncodeUtils.encode(map))
                .subscribeOn(Schedulers.io())
                .map(userBeans -> {
                    UserBean userBean = userBeans.get(0);
                    Result<Baby> result = new Result<>();
                    if ("100".equals(userBean.getFresult())) {
                        result.setCode("0");
                        result.setMsg("切换成功");
                        Baby.baby = userBean.baby();
                        babyDao.insertOrUpdate(Baby.baby);
                        result.setBody(Baby.baby);
                    } else {
                        result.setCode("-1");
                        result.setMsg(userBean.getMsg());
                    }
                    return result;
                });
    }

}
