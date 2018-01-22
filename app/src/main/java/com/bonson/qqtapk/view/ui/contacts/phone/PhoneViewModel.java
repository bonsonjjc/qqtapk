package com.bonson.qqtapk.view.ui.contacts.phone;

import android.app.Application;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.text.TextUtils;

import com.bonson.resource.activity.BaseView;
import com.bonson.resource.fragment.OnSaveListener;
import com.bonson.resource.viewmodel.AndroidViewModel;

import javax.inject.Inject;

/**
 * Created by jiangjiancheng on 18/1/6.
 */

public class PhoneViewModel extends AndroidViewModel {
    public final ObservableField<String> title = new ObservableField<>("");
    public final ObservableField<String> right = new ObservableField<>("");

    public final ObservableBoolean mobileEnable = new ObservableBoolean(true);

    public final ObservableField<String> name = new ObservableField<>("");
    public final ObservableField<String> mobile = new ObservableField<>("");

    @Inject
    public PhoneViewModel(Application application) {
        super(application);
    }

    public void onSave() {
        if (TextUtils.isEmpty(name.get())) {
            view.toast("请输入名称");
            return;
        }
        if (TextUtils.isEmpty(mobile.get())) {
            view.toast("请输入号码");
            return;
        }
        if (onPhoneListener != null) {
            onPhoneListener.onSave();
        }
    }

    private OnSaveListener onPhoneListener;

    public void setOnPhoneListener(OnSaveListener onPhoneListener) {
        this.onPhoneListener = onPhoneListener;
    }
}
