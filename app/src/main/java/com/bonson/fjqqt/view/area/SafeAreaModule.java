package com.bonson.fjqqt.view.area;

import com.bonson.fjqqt.model.FApiServer;
import com.bonson.fjqqt.model.data.LocationModel;
import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.view.ui.area.SafeAreaDataSource;
import com.bonson.qqtapk.model.data.location.LocationModelSource;
import com.bonson.qqtapk.model.db.UserDao;
import com.bonson.qqtapk.view.ui.area.SafeAreaViewModel;
import com.bonson.qqtapk.view.ui.index.LocationViewModel;
import com.bonson.qqtapk.viewmodel.UserViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class SafeAreaModule {
    @ActivityScope
    @Binds
    abstract UserViewModel viewModel(SafeAreaViewModel viewModel);


    @ActivityScope
    @Binds
    abstract UserViewModel locViewModel(LocationViewModel viewModel);


    @ActivityScope
    @Provides
    static SafeAreaDataSource providesAreaDataSource(FApiServer fapiServer, UserDao userDao) {
        return new SafeAreaModel(fapiServer,userDao);
    }

    @ActivityScope
    @Provides
    static LocationModelSource locationModelSource(FApiServer apiServer) {
        return new LocationModel(apiServer);
    }
}
