package com.bonson.qqtapk.view.ui.info.select;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;

import com.bonson.resource.adapter.OnItemClickListener;
import com.bonson.resource.fragment.OnSaveListener;
import com.bonson.resource.viewmodel.AndroidViewModel;

import java.util.List;

import javax.inject.Inject;


public class SelectViewModel extends AndroidViewModel {
    public ObservableField<String> title = new ObservableField<>("");
    public ObservableField<String> right = new ObservableField<>("");

    public List<Select> selects = new ObservableArrayList<>();

    private boolean isSingle = false;

    public boolean isSingle() {
        return isSingle;
    }

    public void setSingle(boolean single) {
        isSingle = single;
    }

    @Inject
    public SelectViewModel(Application application) {
        super(application);
    }

    public void setSelects(List<Select> selects) {
        this.selects.clear();
        this.selects.addAll(selects);
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

    public void select(int position) {
        if (isSingle) {
            for (int i = 0; i < selects.size(); i++) {
                if (i != position) {
                    selects.get(i).setChecked(false);
                } else {
                    selects.get(position).setChecked(true);
                }
            }
        }
        notifyChange();
    }

    private OnItemClickListener onItemClickListener;

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
