package com.bonson.qqtapk.view.ui.setting.map;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.resource.viewmodel.AndroidViewModel;

import dagger.Binds;
import dagger.Module;

/**
 * Created by jiangjiancheng on 18/1/7.
 */
@Module
public abstract class MapModule {
    @Binds
    @ActivityScope
    abstract AndroidViewModel viewModel(MapViewModel viewModel);
}
