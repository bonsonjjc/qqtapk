package com.bonson.qqtapk.view.ui.mode;

import android.content.Context;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.di.FragmentScope;
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
@Module public abstract class ModeModule {
  @ActivityScope
  @Binds
  abstract AndroidViewModel viewModel(ModeViewModel viewModel);

  @ActivityScope
  @Binds
  abstract AndroidViewModel selectViewModel(SelectViewModel viewModel);

  @FragmentScope
  @ContributesAndroidInjector
  abstract SelectFragment selectFragment();
}
