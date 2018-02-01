package com.bonson.qqtapk.view.ui.setting.map;

import android.app.Application;

import com.bonson.qqtapk.viewmodel.UserViewModel;

import javax.inject.Inject;

/**
 * Created by jiangjiancheng on 18/1/7.
 */

public class MapViewModel extends UserViewModel {
    @Inject
    public MapViewModel(Application application) {
        super(application);
    }
}
