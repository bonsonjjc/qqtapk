package com.bonson.qqtapk.view.ui.flower;

import android.content.Context;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.view.adapter.FlowerAdapter;
import com.bonson.resource.viewmodel.AndroidViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by jiangjiancheng on 17/12/31.
 */
@Module
public abstract class FlowerModule {
    @ActivityScope
    @Binds
    abstract AndroidViewModel viewModel(FlowerViewModel viewModel);

    @ActivityScope
    @Provides
    static FlowerAdapter providesAdapter(Context context) {
        return new FlowerAdapter(context);
    }
}
