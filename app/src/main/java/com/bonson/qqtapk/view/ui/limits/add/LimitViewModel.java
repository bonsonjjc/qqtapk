package com.bonson.qqtapk.view.ui.limits.add;

import android.app.Application;
import android.databinding.Bindable;
import android.databinding.ObservableField;
import android.text.TextUtils;

import com.bonson.qqtapk.BR;
import com.bonson.qqtapk.model.bean.Limit;
import com.bonson.resource.fragment.OnSaveListener;
import com.bonson.qqtapk.viewmodel.UserViewModel;

import javax.inject.Inject;

public class LimitViewModel extends UserViewModel {
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
            if (TextUtils.isEmpty(limit.getFname())) {
                view.toast("请输入名称");
                return;
            }
            if (TextUtils.isEmpty(limit.getFmobile())) {
                view.toast("请输入手机号码");
                return;
            }
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
