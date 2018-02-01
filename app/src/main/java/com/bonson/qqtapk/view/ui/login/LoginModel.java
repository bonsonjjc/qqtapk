package com.bonson.qqtapk.view.ui.login;

import com.bonson.fjqqt.model.FApiServer;
import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.model.bean.Baby;
import com.bonson.qqtapk.model.bean.Result;
import com.bonson.qqtapk.model.bean.User;
import com.bonson.qqtapk.model.bean.UserBean;
import com.bonson.qqtapk.model.db.UserDao;
import com.bonson.resource.utils.EncodeUtils;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

@ActivityScope
public class LoginModel implements LoginServer {
    private FApiServer apiServer;
    private UserDao userDao;

    @Inject
    public LoginModel(FApiServer apiServer, UserDao userDao) {
        this.apiServer = apiServer;
        this.userDao = userDao;
    }

    @Override
    public Observable<Result<User>> login(final User user) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("action", "CWA001");
        map.put("fmobile", user.getMobile());
        map.put("fpasswd", user.getPassword());
        map.put("ftoken", user.getToken());
        return apiServer.user(EncodeUtils.encode(map))
                .subscribeOn(Schedulers.io())
                .map(userBeans -> {
                    UserBean userBean = userBeans.get(0);
                    Result<User> result = new Result<>();
                    if ("100".equals(userBean.getFresult())) {
                        result.setCode("0");
                        result.setMsg("登录成功");
                        User tempUser = userBean.user();
                        tempUser.setAuto(user.getAuto());
                        User locUser = userDao.user();
                        if (locUser != null) {
                            userDao.deleteUser(locUser);
                        }
                        userDao.insertUer(tempUser);
                        userDao.insertBaby(tempUser.getBabyList());
                        result.setBody(tempUser);
                    } else {
                        result.setCode("-1");
                        result.setMsg(userBean.getMsg());
                    }
                    return result;
                });
    }
}
