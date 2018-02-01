package com.bonson.qqtapk.view.ui.login;

import com.bonson.qqtapk.model.bean.Result;
import com.bonson.qqtapk.model.bean.User;

import io.reactivex.Observable;

public interface LoginServer {
    Observable<Result<User>> login(User user);
}
