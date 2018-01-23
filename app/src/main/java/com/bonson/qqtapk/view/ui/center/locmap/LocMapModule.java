package com.bonson.qqtapk.view.ui.center.locmap;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.resource.viewmodel.AndroidViewModel;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class LocMapModule {
    @ActivityScope
    @Binds
    abstract AndroidViewModel viewModel(LocMapViewModel viewModel);
}
