package com.bonson.fjqqt.view.ui.terminal.alarm;

import com.bonson.fjqqt.view.ui.terminal.alarm.add.AddAlarmFragment;
import com.bonson.fjqqt.view.ui.terminal.alarm.add.AddAlarmViewModel;
import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.di.FragmentScope;
import com.bonson.qqtapk.view.ui.info.select.SelectFragment;
import com.bonson.qqtapk.view.ui.info.select.SelectViewModel;
import com.bonson.resource.viewmodel.AndroidViewModel;

import dagger.Binds;
import dagger.Module;
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

    @FragmentScope
    @ContributesAndroidInjector
    abstract AddAlarmFragment fragment();

    @FragmentScope
    @ContributesAndroidInjector
    abstract SelectFragment selectFragment();
}
