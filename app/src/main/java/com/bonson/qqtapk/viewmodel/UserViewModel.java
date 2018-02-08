package com.bonson.qqtapk.viewmodel;

import android.app.Application;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;
import android.support.annotation.NonNull;

import com.bonson.qqtapk.app.Route;
import com.bonson.qqtapk.model.bean.Baby;
import com.bonson.qqtapk.model.bean.User;
import com.bonson.qqtapk.model.db.UserDao;
import com.bonson.resource.activity.ActivityUtils;
import com.bonson.resource.viewmodel.AndroidViewModel;

import javax.inject.Inject;

/**
 * Created by jiangjiancheng on 17/12/31.
 */

public class UserViewModel extends AndroidViewModel {
    @Inject
    UserDao userDao;
    private static User mUser;
    private static Baby mBaby;

    public UserViewModel(@NonNull Application application) {
        super(application);
    }

    public User user() {
        if (mUser == null) {
            mUser = userDao.user();
        }
        return mUser;
    }

    public Baby baby() {
        if (mBaby == null) {
            mBaby = userDao.baby(user().getBabyId());
        }
        return mBaby;
    }

    public void setBaby(Baby baby) {
        mBaby = baby;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void exit() {
        mUser.setAuto(false);
        mUser.setPassword("");
        userDao.insertUer(mUser);
        view.start(Route.login);
        ActivityUtils.clear();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {

    }
}
