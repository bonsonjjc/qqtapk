package com.bonson.qqtapk.view.ui.info;

import android.content.Context;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.di.FragmentScope;
import com.bonson.qqtapk.view.ui.info.input.InputFragment;
import com.bonson.qqtapk.view.ui.info.select.SelectAdapter;
import com.bonson.qqtapk.view.ui.info.select.SelectFragment;
import com.bonson.qqtapk.view.ui.info.select.SelectViewModel;
import com.bonson.resource.viewmodel.AndroidViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by jiangjiancheng on 17/12/31.
 */
@Module
public abstract class InfoModule {
    @ActivityScope
    @Binds
    abstract AndroidViewModel viewModel(InfoViewModel viewModel);


    @FragmentScope
    @ContributesAndroidInjector
    abstract SelectFragment selectFragment();

    @FragmentScope
    @ContributesAndroidInjector
    abstract InputFragment inputFragment();


    @Provides
    @ActivityScope
    static RecyclerView.ItemDecoration itemDecoration(Context context) {
        return new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);
    }

    @ActivityScope
    @Binds
    abstract AndroidViewModel selectViewModel(SelectViewModel selectViewModel);
}
