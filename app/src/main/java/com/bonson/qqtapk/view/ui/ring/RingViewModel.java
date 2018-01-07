package com.bonson.qqtapk.view.ui.ring;

import android.app.Application;

import com.bonson.qqtapk.model.data.ring.RingModel;
import com.bonson.resource.viewmodel.AndroidViewModel;

import javax.inject.Inject;

/**
 * Created by jiangjiancheng on 18/1/8.
 */

public class RingViewModel extends AndroidViewModel {
    private RingModel ringModel;

    @Inject

    public RingViewModel(Application application, RingModel ringModel) {
        super(application);
        this.ringModel = ringModel;
    }
}
