package com.bonson.qqtapk.view.ui.center.message;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.resource.viewmodel.AndroidViewModel;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class MessageModule {
    @Binds
    @ActivityScope
    abstract AndroidViewModel viewModel(MessageViewModel viewModel);
}
