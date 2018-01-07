package com.bonson.qqtapk.view.ui.index;

import android.app.Application;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.resource.viewmodel.AndroidViewModel;
import javax.inject.Inject;

/**
 * Created by zjw on 2018/1/2.
 */
@ActivityScope public class IndexViewModel extends AndroidViewModel {
  @Inject public IndexViewModel(Application application) {
    super(application);
  }
}
