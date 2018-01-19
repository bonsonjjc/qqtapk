package com.bonson.fjqqt.view.ui.area;

import com.bonson.fjqqt.model.FApiServer;
import com.bonson.fjqqt.model.data.SafeAreaModel;
import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.model.data.area.SafeAreaDataSource;
import com.bonson.qqtapk.view.ui.area.SafeAreaViewModel;
import com.bonson.qqtapk.view.ui.index.LocationViewModel;
import com.bonson.resource.viewmodel.AndroidViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class SafeAreaModule {
    @ActivityScope
    @Binds
    abstract AndroidViewModel viewModel(SafeAreaViewModel viewModel);


    @ActivityScope
    @Binds
    abstract AndroidViewModel locViewModel(LocationViewModel viewModel);


    @ActivityScope
    @Provides
    static SafeAreaDataSource providesAreaDataSource(FApiServer fapiServer) {
        return new SafeAreaModel(fapiServer);
    }
}
