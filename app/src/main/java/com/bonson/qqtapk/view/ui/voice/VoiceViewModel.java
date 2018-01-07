package com.bonson.qqtapk.view.ui.voice;

import android.app.Application;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.resource.viewmodel.AndroidViewModel;

import javax.inject.Inject;

/**
 * Created by jiangjiancheng on 17/12/31.
 */
@ActivityScope
public class VoiceViewModel extends AndroidViewModel {
    @Inject
    public VoiceViewModel(Application application) {
        super(application);
    }
}
