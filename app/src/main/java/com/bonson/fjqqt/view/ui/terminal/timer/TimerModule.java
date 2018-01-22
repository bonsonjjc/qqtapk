package com.bonson.fjqqt.view.ui.terminal.timer;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.resource.viewmodel.AndroidViewModel;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class TimerModule {

    @ActivityScope
    @Binds
    abstract AndroidViewModel viewModel(TimerViewModel viewModel);
}
