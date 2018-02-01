package com.bonson.qqtapk.view.ui.forget;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.di.FragmentScope;
import com.bonson.qqtapk.view.ui.register.VerifyViewModel;
import com.bonson.qqtapk.viewmodel.UserViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by zjw on 2017/12/29.
 */

@Module
public abstract class ForgetModule {
    @Binds
    @ActivityScope
    abstract UserViewModel viewModel(ForgetViewModel viewModel);

    @Binds
    @ActivityScope
    abstract UserViewModel verifyModel(VerifyViewModel viewModel);

    @Binds
    @ActivityScope
    abstract UserViewModel resetModel(ResetViewModel viewModel);

    @ContributesAndroidInjector
    @FragmentScope
    abstract ResetFragment resetFragment();
}
