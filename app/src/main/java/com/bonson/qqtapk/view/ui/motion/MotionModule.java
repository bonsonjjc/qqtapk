package com.bonson.qqtapk.view.ui.motion;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.resource.viewmodel.AndroidViewModel;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class MotionModule {
    @ActivityScope
    @Binds
    abstract AndroidViewModel viewModel(MotionViewModel viewModel);
}
