package com.bonson.fjqqt.view.terminal.silence;

import android.content.Context;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.viewmodel.UserViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class SilenceModule {

    @ActivityScope
    @Binds
    abstract UserViewModel viewModel(SilenceViewModel viewModel);

    @Provides
    @ActivityScope
    static RecyclerView.ItemDecoration itemDecoration(Context context) {
        return new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);
    }
}
