package com.bonson.qqtapk.view.ui.index;

import android.content.Context;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.view.adapter.BabyAdapter;
import com.bonson.qqtapk.view.adapter.MenuAdapter;
import com.bonson.resource.viewmodel.AndroidViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

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
