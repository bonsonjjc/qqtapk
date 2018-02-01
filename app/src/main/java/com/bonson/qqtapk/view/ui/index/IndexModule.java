package com.bonson.qqtapk.view.ui.index;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.model.data.ApiServer;
import com.bonson.qqtapk.model.data.location.LocationModel;
import com.bonson.qqtapk.model.data.location.LocationModelSource;
import com.bonson.qqtapk.viewmodel.UserViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by zjw on 2017/12/29.
 */
@Module
public abstract class IndexModule {

    @Binds
    @ActivityScope
    abstract UserViewModel viewModel(IndexViewModel viewModel);

    @ActivityScope
    @Binds
    abstract UserViewModel mainViewModel(MainViewModel viewModel);

    @ActivityScope
    @Binds
    abstract UserViewModel locViewModel(LocationViewModel viewModel);

    @Provides
    @ActivityScope
    static LocationModelSource locationModelSource(ApiServer apiServer){
        return  new LocationModel(apiServer);
    }
//
//    @Provides
//    @ActivityScope
//    static LocationModelSource locationModelSource(FApiServer apiServer){
//        return  new com.bonson.fjqqt.model.data.LocationModel(apiServer);
//    }

}
