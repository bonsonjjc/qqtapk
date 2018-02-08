package com.bonson.qqtapk.view.ui.setting.map;

import com.baidu.mapapi.map.offline.MKOfflineMap;
import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.viewmodel.UserViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by jiangjiancheng on 18/1/7.
 */
@Module
public abstract class MapModule {
    @Binds
    @ActivityScope
    abstract UserViewModel viewModel(MapViewModel viewModel);

    @ActivityScope
    @Provides
    public static MKOfflineMap providesOfflineMap() {
        return new MKOfflineMap();
    }
}
