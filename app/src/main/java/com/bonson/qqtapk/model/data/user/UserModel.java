package com.bonson.qqtapk.model.data.user;

import com.bonson.qqtapk.model.bean.Baby;
import com.bonson.qqtapk.model.bean.Result;
import com.bonson.qqtapk.model.bean.User;
import com.bonson.qqtapk.model.bean.UserBean;
import com.bonson.qqtapk.model.data.ApiServer;
import com.bonson.qqtapk.model.db.UserDao;
import com.bonson.library.utils.security.Md5Utils;
import com.bonson.qqtapk.utils.QQtBuilder;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by zjw on 2018/1/2.
 */
public class UserModel {
    private UserDao userDao;
    private ApiServer apiServer;

    @Inject
    public UserModel(UserDao userDao, ApiServer apiServer) {
        this.userDao = userDao;
        this.apiServer = apiServer;
    }

    public Observable<Result<User>> verify(String mobile, String type) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("fmobile", mobile);
        map.put("ftype", type);
        Object body = QQtBuilder.build("14", map);
        return apiServer.user(body)
                .subscribeOn(Schedulers.io())
                .map(userBeans -> {
                    UserBean userBean = userBeans.get(0);
                    Result<User> result = new Result<>();
                    if ("0".equals(userBean.getFresult())) {
                        result.setCode("0");
                        result.setMsg("验证码发送成功,请注意查收");
                    } else {
                        result.setCode("-1");
                        result.setMsg(userBean.getMsg());
                    }
                    return result;
                });
    }

    public Observable<Result<User>> register(String mobile, String password, String verify) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("fmobile", mobile);
        map.put("fpasswd", Md5Utils.toMD5(password));
        map.put("fverify", verify);
        Object body = QQtBuilder.build("02", map);
        return apiServer.user(body)
                .subscribeOn(Schedulers.io())
                .map(userBeans -> {
                    UserBean userBean = userBeans.get(0);
                    Result<User> result = new Result<>();
                    if ("0".equals(userBean.getFresult())) {
                        result.setCode("0");
                        result.setMsg("注册成功");
                    } else {
                        result.setCode("-1");
                        result.setMsg(userBean.getMsg());
                    }
                    return result;
                });
    }

    public Observable<Result<User>> forget(String mobile, String verify) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("fmobile", mobile);
        map.put("fverify", verify);
        Object body = QQtBuilder.build("38", map);
        return apiServer.user(body)
                .subscribeOn(Schedulers.io())
                .map(userBeans -> {
                    UserBean userBean = userBeans.get(0);
                    Result<User> result = new Result<>();
                    if ("0".equals(userBean.getFresult())) {
                        result.setCode("0");
                        result.setMsg("成功");
                    } else {
                        result.setCode("-1");
                        result.setMsg(userBean.getMsg());
                    }
                    return result;
                });
    }


    public Observable<Result<User>> resetPassword(String mobile, String password) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("fmobile", mobile);
        map.put("fpasswd", password);
        Object body = QQtBuilder.build("39", map);
        return apiServer.user(body)
                .subscribeOn(Schedulers.io())
                .map(userBeans -> {
                    UserBean userBean = userBeans.get(0);
                    Result<User> result = new Result<>();
                    if ("0".equals(userBean.getFresult())) {
                        result.setCode("0");
                        result.setMsg("重置密码成功");
                    } else {
                        result.setCode("-1");
                        result.setMsg(userBean.getMsg());
                    }
                    return result;
                });
    }

    public Observable<Result<User>> password(String userId,String password, String newPassword) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("fid", userId);
        map.put("fpasswd", Md5Utils.toMD5(password));
        map.put("fnewpasswd", Md5Utils.toMD5(newPassword));
        Object body = QQtBuilder.build("12", map);
        return apiServer.user(body)
                .subscribeOn(Schedulers.io())
                .map(userBeans -> {
                    UserBean userBean = userBeans.get(0);
                    Result<User> result = new Result<>();
                    if ("0".equals(userBean.getFresult())) {
                        result.setCode("0");
                        result.setMsg("修改密码成功");
                        User user = userDao.user();
                        user.setAuto(false);
                        user.setPassword("");
                        userDao.insertUer(user);
                    } else {
                        result.setCode("-1");
                        result.setMsg(userBean.getMsg());
                    }
                    return result;
                });
    }
}
