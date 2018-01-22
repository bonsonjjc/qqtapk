package com.bonson.qqtapk.view.ui.setting.about;

import android.app.Application;
import android.databinding.ObservableField;

import com.bonson.resource.activity.BaseView;
import com.bonson.resource.viewmodel.AndroidViewModel;

import javax.inject.Inject;

/**
 * Created by jiangjiancheng on 18/1/7.
 */

public class AboutViewModel extends AndroidViewModel {
    public final ObservableField<String> servicePhone = new ObservableField<>("15659149106");

    public final ObservableField<String> webUrl = new ObservableField<>("www.baidu.com");

    public final ObservableField<String> version = new ObservableField<>("3.0.0");

    @Inject
    public AboutViewModel(Application application) {
        super(application);
    }
}
