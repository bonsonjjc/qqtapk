package com.bonson.qqtapk.view.ui.area;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.view.ui.index.main.LocationViewModel;
import com.bonson.resource.viewmodel.AndroidViewModel;

import dagger.Binds;
import dagger.Module;

/**
 * Created by jiangjiancheng on 17/12/31.
 */
@Module
public abstract class SafeAreaModule {
    @ActivityScope
    @Binds
    abstract AndroidViewModel viewModel(SafeAreaViewModel viewModel);


    @ActivityScope
    @Binds
    abstract AndroidViewModel locViewModel(LocationViewModel viewModel);
}
