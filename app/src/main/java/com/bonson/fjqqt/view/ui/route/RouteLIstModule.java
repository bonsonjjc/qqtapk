package com.bonson.fjqqt.view.ui.route;

import com.bonson.fjqqt.model.bean.RouteTime;
import com.bonson.fjqqt.view.ui.route.time.AddTimeFragment;
import com.bonson.fjqqt.view.ui.route.time.AddTimeViewModel;
import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.di.FragmentScope;
import com.bonson.resource.viewmodel.AndroidViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class RouteLIstModule {
    @ActivityScope
    @Binds
    abstract AndroidViewModel viewModel(RouteListViewModel viewModel);

    @ActivityScope
    @Binds
    abstract AndroidViewModel addViewModel(AddTimeViewModel viewModel);

    @FragmentScope
    @ContributesAndroidInjector
    abstract AddTimeFragment addTimeFragment();
}
