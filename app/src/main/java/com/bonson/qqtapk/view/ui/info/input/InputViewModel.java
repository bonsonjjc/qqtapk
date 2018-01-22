package com.bonson.qqtapk.view.ui.info.input;

import android.app.Application;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;

import com.bonson.resource.fragment.OnSaveListener;
import com.bonson.resource.viewmodel.AndroidViewModel;

import javax.inject.Inject;


public class InputViewModel extends AndroidViewModel {
    public final ObservableField<String> hint = new ObservableField<>("");
    public final ObservableField<String> value = new ObservableField<>("");
    public final ObservableField<String> title = new ObservableField<>("");
    public final ObservableField<String> digits = new ObservableField<>("");
    public final ObservableInt length = new ObservableInt(20);

    @Inject
    public InputViewModel(Application application) {
        super(application);
    }

    public void onSave() {
        if (onSaveListener != null) {
            onSaveListener.onSave();
        }
    }

    private OnSaveListener onSaveListener;

    public void setOnSaveListener(OnSaveListener onSaveListener) {
        this.onSaveListener = onSaveListener;
    }
}
