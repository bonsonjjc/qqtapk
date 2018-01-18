package com.bonson.fjqqt.view.ui.terminal.alarm;

import com.bonson.fjqqt.di.FjqqtScope;
import com.bonson.fjqqt.view.ui.terminal.alarm.add.AddAlarmFragment;
import com.bonson.fjqqt.view.ui.terminal.alarm.add.AddAlarmViewModel;
import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.di.FragmentScope;
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
    abstract AndroidViewModel addViewModel(AddAlarmViewModel viewModel);

    @FragmentScope
    @ContributesAndroidInjector
    abstract AddAlarmFragment fragment();
}
