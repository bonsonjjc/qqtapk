package com.bonson.qqtapk.view.ui.center.message;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.viewmodel.UserViewModel;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class MessageModule {
    @Binds
    @ActivityScope
    abstract UserViewModel viewModel(MessageViewModel viewModel);
}
