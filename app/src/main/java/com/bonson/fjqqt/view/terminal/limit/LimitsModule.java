package com.bonson.fjqqt.view.terminal.limit;

import android.content.Context;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.di.FragmentScope;
import com.bonson.qqtapk.view.ui.limits.add.LimitFragment;
import com.bonson.resource.viewmodel.AndroidViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class LimitsModule {

    @ActivityScope
    @Provides
    static RecyclerView.ItemDecoration itemDecoration(Context context) {
        return new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);
    }

    @ActivityScope
    @Binds
    abstract AndroidViewModel viewModel(LimitsViewModel viewModel);


    @ActivityScope
    @Binds
    abstract AndroidViewModel limitViewModel(com.bonson.qqtapk.view.ui.limits.add.LimitViewModel viewModel);


    @FragmentScope
    @ContributesAndroidInjector
    abstract LimitFragment fragment();

}
