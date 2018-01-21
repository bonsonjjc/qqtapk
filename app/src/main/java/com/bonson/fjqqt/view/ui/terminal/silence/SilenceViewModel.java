package com.bonson.fjqqt.view.ui.terminal.silence;

import android.app.Application;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.resource.activity.BaseView;
import com.bonson.resource.viewmodel.AndroidViewModel;

import javax.inject.Inject;

/**
 * Created by jiangjiancheng on 18/1/21.
 */
@ActivityScope
public class SilenceViewModel extends AndroidViewModel {
    private BaseView view;
    public ObservableInt cycleType = new ObservableInt(1);

    public ObservableField<String> custom = new ObservableField<>("自定义");
    private String cycle = "0";

    @Inject
    public SilenceViewModel(Application application) {
        super(application);
    }

    public void setView(BaseView view) {
        this.view = view;
    }

}
