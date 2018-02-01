package com.bonson.qqtapk.view.ui.setting.about;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.viewmodel.UserViewModel;

import dagger.Binds;
import dagger.Module;

/**
 * Created by jiangjiancheng on 18/1/7.
 */
@Module
public abstract class AboutModule {
    @Binds
    @ActivityScope
    abstract UserViewModel viewModel(AboutViewModel viewModel);
}
