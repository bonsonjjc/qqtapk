package com.bonson.qqtapk.view.ui.register;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.viewmodel.UserViewModel;

import dagger.Binds;
import dagger.Module;

/**
 * Created by zjw on 2017/12/29.
 */
@Module
public abstract class RegisterModule {

    @Binds
    @ActivityScope
    abstract UserViewModel viewModel(RegisterViewModel viewModel);

    @Binds
    @ActivityScope
    abstract UserViewModel verifyModel(VerifyViewModel viewModel);
}
