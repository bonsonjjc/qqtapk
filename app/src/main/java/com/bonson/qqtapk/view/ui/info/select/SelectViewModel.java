package com.bonson.qqtapk.view.ui.info.select;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;

import com.bonson.resource.adapter.OnItemClickListener;
import com.bonson.resource.fragment.OnSaveListener;
import com.bonson.resource.viewmodel.AndroidViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class SelectViewModel extends AndroidViewModel {
    public final ObservableField<String> title = new ObservableField<>("");

    public final ObservableList<Select> selects = new ObservableArrayList<>();

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

    void select(int position) {
        Select select = selects.get(position);
        if (isSingle) {
            for (int i = 0; i < selects.size(); i++) {
                if (i != position)
                    unSelect(i);
            }
            select.setChecked(true);
        } else {
            select.setChecked(!select.isChecked());
        }
        selects.set(position, select);
    }

    private void unSelect(int position) {
        Select select = selects.get(position);
        if (select.isChecked()) {
            select.setChecked(false);
            selects.set(position, select);
        }
    }


    public void selected(SelectFilter selectFilter) {
        for (int i = 0; i < selects.size(); i++) {
            Select select = selects.get(i);
            selectFilter.isSelect(i, select);
        }
    }


    private OnItemClickListener onItemClickListener;

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface SelectFilter {
        boolean isSelect(int index, Select select);
    }
}
