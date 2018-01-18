package com.bonson.qqtapk.view.ui.area;

import com.bonson.fjqqt.model.FApiServer;
import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.model.bean.Baby;
import com.bonson.qqtapk.model.data.ApiServer;
import com.bonson.qqtapk.model.data.area.SafeAreaDataSource;
import com.bonson.qqtapk.model.data.area.SafeAreaModel;
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
    static SafeAreaDataSource providesAreaDataSource(ApiServer apiServer, FApiServer fapiServer) {
        if (Baby.baby.getFtag().equals("L08")) {
            return new SafeAreaModel(apiServer);
        }
        return new com.bonson.fjqqt.model.data.SafeAreaModel(fapiServer);
    }
}
