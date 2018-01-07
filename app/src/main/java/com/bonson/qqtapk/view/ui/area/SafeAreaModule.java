package com.bonson.qqtapk.view.ui.area;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.model.data.area.SafeAreaServer;
import com.bonson.resource.viewmodel.AndroidViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by jiangjiancheng on 17/12/31.
 */
@Module
public abstract class SafeAreaModule {
    @ActivityScope
    @Binds
    abstract AndroidViewModel viewModel(SafeAreaViewModel viewModel);
}
