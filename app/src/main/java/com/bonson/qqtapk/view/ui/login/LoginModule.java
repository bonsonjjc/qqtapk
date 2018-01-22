package com.bonson.qqtapk.view.ui.login;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.resource.viewmodel.AndroidViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by zjw on 2017/12/29.
 */
@Module
public abstract class LoginModule {
    @Binds
    @ActivityScope
    abstract AndroidViewModel viewModel(LoginViewModel viewModel);

    @ActivityScope
    @Provides
    static LoginServer providesLoginServer(LoginModel loginModel) {
        return loginModel;
    }
}
