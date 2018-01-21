package com.bonson.fjqqt.view.ui.terminal.limit;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.di.FragmentScope;
import com.bonson.qqtapk.view.ui.limits.add.LimitFragment;
import com.bonson.qqtapk.view.ui.limits.add.LimitViewModel;
import com.bonson.resource.viewmodel.AndroidViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class LimitsModule {

    @ActivityScope
    @Binds
    abstract AndroidViewModel viewModel(LimitsViewModel viewModel);


    @ActivityScope
    @Binds
    abstract AndroidViewModel limitViewModel(LimitViewModel viewModel);

    @FragmentScope
    @ContributesAndroidInjector
    abstract LimitFragment fragment();
}
