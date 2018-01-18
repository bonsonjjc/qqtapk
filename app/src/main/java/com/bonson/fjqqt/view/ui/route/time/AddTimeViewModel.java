package com.bonson.fjqqt.view.ui.route.time;

import android.app.Application;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;

import com.bonson.resource.activity.BaseView;
import com.bonson.resource.viewmodel.AndroidViewModel;

import javax.inject.Inject;


public class AddTimeViewModel extends AndroidViewModel {
    public final ObservableField<String> title = new ObservableField<>("");
    public final ObservableField<String> right = new ObservableField<>("");
    public final ObservableField<String> time = new ObservableField<>("00:00~00:00");
    public final ObservableBoolean state = new ObservableBoolean(false);
    public final ObservableInt type = new ObservableInt(1);

    private BaseView view;

    @Inject
    public AddTimeViewModel(Application application) {
        super(application);
    }

    public BaseView getView() {
        return view;
    }

    public void setView(BaseView view) {
        this.view = view;
    }

    public void onItem(int type) {
        this.type.set(type);
    }

    public void onSave() {

    }

}
