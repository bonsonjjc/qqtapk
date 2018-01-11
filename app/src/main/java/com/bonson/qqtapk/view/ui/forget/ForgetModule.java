package com.bonson.qqtapk.view.ui.forget;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.di.FragmentScope;
import com.bonson.qqtapk.view.ui.register.VerifyViewModel;
import com.bonson.resource.viewmodel.AndroidViewModel;

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
    abstract AndroidViewModel viewModel(ForgetViewModel viewModel);

    @Binds
    @ActivityScope
    abstract AndroidViewModel verifyModel(VerifyViewModel viewModel);

    @Binds
    @ActivityScope
    abstract AndroidViewModel resetModel(ResetViewModel viewModel);

    @ContributesAndroidInjector
    @FragmentScope
    abstract ResetFragment resetFragment();
}
