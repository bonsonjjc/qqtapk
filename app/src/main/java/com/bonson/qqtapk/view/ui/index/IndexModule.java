package com.bonson.qqtapk.view.ui.index;

import android.content.Context;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.di.FragmentScope;
import com.bonson.qqtapk.view.adapter.BabyAdapter;
import com.bonson.qqtapk.view.adapter.MenuAdapter;
import com.bonson.qqtapk.view.ui.index.main.LocationViewModel;
import com.bonson.qqtapk.view.ui.index.main.MainFragment;
import com.bonson.qqtapk.view.ui.index.main.MainViewModel;
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

    @ActivityScope
    @Binds
    abstract AndroidViewModel locViewModel(LocationViewModel viewModel);

    @ContributesAndroidInjector
    @FragmentScope
    abstract MainFragment mainFragment();

    @ActivityScope
    @Provides
    static BabyAdapter providesAdapter(Context context) {
        return new BabyAdapter(context);
    }

    @Provides
    @ActivityScope
    static MenuAdapter menuAdapter(Context context) {
        MenuAdapter menuAdapter = new MenuAdapter(context);
        return menuAdapter;
    }
}
