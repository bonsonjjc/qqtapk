package com.bonson.qqtapk.view.ui.voice;

import android.app.Application;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.model.data.voice.VoiceModel;
import com.bonson.resource.activity.BaseView;
import com.bonson.resource.viewmodel.AndroidViewModel;

import javax.inject.Inject;

/**
 * Created by jiangjiancheng on 17/12/31.
 */
@ActivityScope
public class VoiceViewModel extends AndroidViewModel {
    private VoiceModel voiceModel;
    private BaseView view;

    @Inject
    public VoiceViewModel(Application application, VoiceModel voiceModel) {
        super(application);
        this.voiceModel = voiceModel;
    }

    public void setView(BaseView view) {
        this.view = view;
    }
}
