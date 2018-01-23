package com.bonson.fjqqt.view.terminal.timer;

import android.content.Context;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.resource.viewmodel.AndroidViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class TimerModule {

    @ActivityScope
    @Provides
    static RecyclerView.ItemDecoration itemDecoration(Context context) {
        return new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);
    }


    @ActivityScope
    @Binds
    abstract AndroidViewModel viewModel(TimerViewModel viewModel);

}
