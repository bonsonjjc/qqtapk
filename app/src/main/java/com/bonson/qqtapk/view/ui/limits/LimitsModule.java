package com.bonson.qqtapk.view.ui.limits;

import android.content.Context;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.di.FragmentScope;
import com.bonson.qqtapk.view.adapter.LimitAdapter;
import com.bonson.qqtapk.view.ui.limits.add.LimitFragment;
import com.bonson.qqtapk.view.ui.limits.add.LimitViewModel;
import com.bonson.resource.viewmodel.AndroidViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by jiangjiancheng on 17/12/31.
 */
@Module
public abstract class LimitsModule {
    @ActivityScope
    @Binds
    abstract AndroidViewModel viewModel(LimitsViewModel viewModel);

    @ActivityScope
    @Binds
    abstract AndroidViewModel limitViewModel(LimitViewModel viewModel);

    @FragmentScope
    @ContributesAndroidInjector
    abstract LimitFragment fragment();

    @ActivityScope
    @Provides
    static LimitAdapter providesAdapter(Context context, LimitsViewModel viewModel) {
        return new LimitAdapter(context, viewModel.limits);
    }
}
