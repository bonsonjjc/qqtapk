package com.bonson.qqtapk.view.ui.scan;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.resource.viewmodel.AndroidViewModel;
import dagger.Binds;
import dagger.Module;

@Module public abstract class ScanModule {
  @ActivityScope @Binds abstract AndroidViewModel viewModel(ScanViewModel scanViewModel);
}
