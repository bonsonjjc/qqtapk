package com.bonson.fjqqt.view.ui.terminal.silence;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.resource.viewmodel.AndroidViewModel;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class SilenceModule {

    @ActivityScope
    @Binds
    abstract AndroidViewModel viewModel(SilenceViewModel viewModel);
}
