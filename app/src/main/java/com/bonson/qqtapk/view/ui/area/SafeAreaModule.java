package com.bonson.qqtapk.view.ui.area;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.model.data.ApiServer;
import com.bonson.qqtapk.model.data.location.LocationModel;
import com.bonson.qqtapk.model.data.location.LocationModelSource;
import com.bonson.qqtapk.model.db.UserDao;
import com.bonson.qqtapk.view.ui.index.LocationViewModel;
import com.bonson.qqtapk.viewmodel.UserViewModel;

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
    abstract UserViewModel viewModel(SafeAreaViewModel viewModel);


    @ActivityScope
    @Binds
    abstract UserViewModel locViewModel(LocationViewModel viewModel);


    @ActivityScope
    @Provides
    static SafeAreaDataSource providesAreaDataSource(ApiServer apiServer, UserDao userDao) {
        return new SafeAreaModel(apiServer,userDao);
    }

    @ActivityScope
    @Provides
    static LocationModelSource locationModelSource(ApiServer apiServer) {
        return new LocationModel(apiServer);
    }
}
