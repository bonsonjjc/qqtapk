package com.bonson.fjqqt.view.ui.terminal.limit;

import com.bonson.fjqqt.di.FjqqtScope;
import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.resource.viewmodel.AndroidViewModel;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class LimitModule {

    @ActivityScope
    @Binds
    abstract AndroidViewModel viewModel(LimitViewModel viewModel);
}
