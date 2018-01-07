package com.bonson.qqtapk.view.ui.setting;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.resource.viewmodel.AndroidViewModel;

import dagger.Binds;
import dagger.Module;

/**
 * Created by jiangjiancheng on 17/12/31.
 */
@Module
public abstract class SettingModule {
    @ActivityScope
    @Binds
    abstract AndroidViewModel viewModel(SettingViewModel viewModel);
}