package com.bonson.qqtapk.view.ui.limits;

import android.content.Context;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.di.FragmentScope;
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
    static RecyclerView.ItemDecoration itemDecoration(Context context) {
        return new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);
    }
}
