package com.bonson.qqtapk.view.ui.motion.target;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.model.data.ApiServer;
import com.bonson.qqtapk.model.data.motion.MotionDataSource;
import com.bonson.qqtapk.model.data.motion.MotionModel;
import com.bonson.qqtapk.viewmodel.UserViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class TargetModule {
    @Binds
    @ActivityScope
    abstract UserViewModel viewModel(TargetViewModel viewModel);

    @ActivityScope
    @Provides
    static MotionDataSource providesDataSource(ApiServer apiServer) {
        return new MotionModel(apiServer);
    }
}
