package com.bonson.qqtapk.view.ui.scan;

import android.app.Application;
import com.bonson.qqtapk.model.data.baby.BabyModel;
import com.bonson.resource.viewmodel.AndroidViewModel;

public class ScanViewModel extends AndroidViewModel {
  private BabyModel babyModel;

  public ScanViewModel(Application application, BabyModel babyModel) {
    super(application);
    this.babyModel = babyModel;
  }
}
