package com.bonson.qqtapk.view.ui.scan;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.viewmodel.UserViewModel;
import dagger.Binds;
import dagger.Module;

@Module public abstract class ScanModule {
  @ActivityScope @Binds abstract UserViewModel viewModel(ScanViewModel scanViewModel);
}
