package com.bonson.qqtapk.view.ui.ring;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.resource.viewmodel.AndroidViewModel;

import dagger.Binds;
import dagger.Module;

/**
 * Created by jiangjiancheng on 18/1/8.
 */
@Module
public abstract class RingModule {
    @ActivityScope
    @Binds
    abstract AndroidViewModel viewModel(RingViewModel viewModel);
}
