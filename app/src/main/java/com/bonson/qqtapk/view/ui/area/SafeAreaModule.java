package com.bonson.qqtapk.view.ui.area;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.model.data.ApiServer;
import com.bonson.qqtapk.model.data.area.SafeAreaDataSource;
import com.bonson.qqtapk.model.data.area.SafeAreaModel;
import com.bonson.qqtapk.model.data.location.LocationModel;
import com.bonson.qqtapk.model.data.location.LocationModelSource;
import com.bonson.qqtapk.view.ui.index.LocationViewModel;
import com.bonson.resource.viewmodel.AndroidViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

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


    @ActivityScope
    @Provides
    static SafeAreaDataSource providesAreaDataSource(ApiServer apiServer) {
        return new SafeAreaModel(apiServer);
    }

    @ActivityScope
    @Provides
    static LocationModelSource locationModelSource(ApiServer apiServer) {
        return new LocationModel(apiServer);
    }
}
