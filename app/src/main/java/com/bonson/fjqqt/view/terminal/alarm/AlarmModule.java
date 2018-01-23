package com.bonson.fjqqt.view.terminal.alarm;

import android.content.Context;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;

import com.bonson.fjqqt.view.terminal.alarm.add.AddAlarmFragment;
import com.bonson.fjqqt.view.terminal.alarm.add.AddAlarmViewModel;
import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.di.FragmentScope;
import com.bonson.qqtapk.view.ui.info.select.SelectFragment;
import com.bonson.qqtapk.view.ui.info.select.SelectViewModel;
import com.bonson.resource.viewmodel.AndroidViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class AlarmModule {

    @ActivityScope
    @Binds
    abstract AndroidViewModel viewModel(AlarmViewModel viewModel);

    @ActivityScope
    @Binds
    abstract AndroidViewModel addAlarmViewModel(AddAlarmViewModel viewModel);

    @ActivityScope
    @Binds
    abstract AndroidViewModel addSelectViewModel(SelectViewModel viewModel);

    @Provides
    @ActivityScope
    static RecyclerView.ItemDecoration itemDecoration(Context context) {
        return new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);
    }

    @FragmentScope
    @ContributesAndroidInjector
    abstract AddAlarmFragment fragment();

    @FragmentScope
    @ContributesAndroidInjector
    abstract SelectFragment selectFragment();
}
