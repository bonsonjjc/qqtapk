package com.bonson.qqtapk.view.ui.center.locmap;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.viewmodel.UserViewModel;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class LocMapModule {
    @ActivityScope
    @Binds
    abstract UserViewModel viewModel(LocMapViewModel viewModel);
}
