package com.bonson.qqtapk.view.ui.forget;

import android.app.Application;
import android.databinding.ObservableField;
import android.text.TextUtils;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.model.data.user.UserModel;
import com.bonson.resource.activity.BaseView;
import com.bonson.resource.fragment.OnSaveListener;
import com.bonson.resource.viewmodel.AndroidViewModel;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * Created by zjw on 2018/1/3.
 */
@ActivityScope
public class ResetViewModel extends AndroidViewModel {
    public final ObservableField<String> mobile = new ObservableField<>("");
    public final ObservableField<String> password = new ObservableField<>("");
    public final ObservableField<String> newPassword = new ObservableField<>("");

    @Inject
    public ResetViewModel(Application application) {
        super(application);
    }


    public void reset() {
        if (TextUtils.isEmpty(password.get())) {
            view.toast("请输入新密码");
            return;
        }
        if (!password.get().matches("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$")) {
            view.toast("密码必须为(8到16位的数字+字母的组合)");
            return;
        }
        if (TextUtils.isEmpty(newPassword.get())) {
            view.toast("再次输入新密码");
            return;
        }
        if (!newPassword.get().matches("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$")) {
            view.toast("密码必须为(8到16位的数字+字母的组合)");
            return;
        }
        if (!password.equals(newPassword)) {
            view.toast("两次密码不一至");
            return;
        }
        if (onSaveListener != null) {
            onSaveListener.onSave();
        }
    }

    private OnSaveListener onSaveListener;

    public void setOnSaveListener(OnSaveListener onSaveListener) {
        this.onSaveListener = onSaveListener;
    }
}
