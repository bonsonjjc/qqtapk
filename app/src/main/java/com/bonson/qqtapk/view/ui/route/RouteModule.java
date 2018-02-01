package com.bonson.qqtapk.view.ui.route;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.viewmodel.UserViewModel;

import dagger.Binds;
import dagger.Module;

/**
 * Created by jiangjiancheng on 17/12/31.
 */
@Module
public abstract class RouteModule {
    @Binds
    @ActivityScope
    abstract UserViewModel viewModel(RouteViewModel viewModel);
}
