package com.bonson.qqtapk.view.ui.contacts.input;

import android.app.Application;
import android.databinding.ObservableField;

import com.bonson.resource.viewmodel.AndroidViewModel;

import javax.inject.Inject;

/**
 * Created by jiangjiancheng on 18/1/6.
 */

public class InputViewModel extends AndroidViewModel {
    public ObservableField<String> title = new ObservableField<>();
    public ObservableField<String> right = new ObservableField<>();

    public ObservableField<String> name = new ObservableField<>();
    public ObservableField<String> mobile = new ObservableField<>();

    public ObservableField<String> nameHint = new ObservableField<>();
    public ObservableField<String> mobileHint = new ObservableField<>();

    @Inject
    InputViewModel(Application application) {
        super(application);
    }

    public void onSave() {

    }

}
