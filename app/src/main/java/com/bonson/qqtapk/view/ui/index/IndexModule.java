package com.bonson.qqtapk.view.ui.index;

import android.content.Context;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.di.FragmentScope;
import com.bonson.qqtapk.view.adapter.MenuAdapter;
import com.bonson.qqtapk.view.ui.main.MainFragment;
import com.bonson.qqtapk.view.ui.main.MainViewModel;
import com.bonson.resource.viewmodel.AndroidViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by zjw on 2017/12/29.
 */
@Module
public abstract class IndexModule {

    @Binds
    @ActivityScope
    abstract AndroidViewModel viewModel(IndexViewModel viewModel);

    @ActivityScope
    @Binds
    abstract AndroidViewModel mainViewModel(MainViewModel viewModel);

    @ContributesAndroidInjector
    @FragmentScope
    abstract MainFragment mainFragment();

    @Provides
    @ActivityScope
    static MenuAdapter menuAdapter(MainViewModel viewModel, Context context) {
        MenuAdapter menuAdapter = new MenuAdapter(context, viewModel.menus);
        return menuAdapter;
    }
}
