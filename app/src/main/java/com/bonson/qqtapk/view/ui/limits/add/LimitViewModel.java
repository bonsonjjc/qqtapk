package com.bonson.qqtapk.view.ui.limits.add;

import android.app.Application;
import android.databinding.Bindable;
import android.databinding.ObservableField;

import com.bonson.qqtapk.BR;
import com.bonson.qqtapk.model.bean.Limit;
import com.bonson.resource.fragment.OnSaveListener;
import com.bonson.resource.viewmodel.AndroidViewModel;

import javax.inject.Inject;

public class LimitViewModel extends AndroidViewModel {
    public final ObservableField<String> title = new ObservableField<>("");
    private Limit limit;

    @Inject
    public LimitViewModel(Application application) {
        super(application);
    }

    @Bindable
    public Limit getLimit() {
        return limit;
    }

    public void setLimit(Limit limit) {
        this.limit = limit;
        notifyPropertyChanged(BR.limit);
    }

    public void delete() {
        if (onSaveListener != null) {
            onDeleteListener.onSave();
        }
    }

    public void onSave() {
        if (onSaveListener != null) {
            onSaveListener.onSave();
        }
    }


    private OnSaveListener onDeleteListener;
    private OnSaveListener onSaveListener;

    public void setOnDeleteListener(OnSaveListener onDeleteListener) {
        this.onDeleteListener = onDeleteListener;
    }

    public void setOnSaveListener(OnSaveListener onSaveListener) {
        this.onSaveListener = onSaveListener;
    }
}
