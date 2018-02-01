package com.bonson.qqtapk.view.ui.motion;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.viewmodel.UserViewModel;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class MotionModule {
    @ActivityScope
    @Binds
    abstract UserViewModel viewModel(MotionViewModel viewModel);
}
