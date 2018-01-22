package com.bonson.qqtapk.view.ui.center;

import android.app.Application;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.bonson.qqtapk.BR;
import com.bonson.qqtapk.model.bean.Baby;
import com.bonson.qqtapk.model.bean.Message;
import com.bonson.qqtapk.model.data.center.MessageModel;
import com.bonson.resource.activity.BaseView;
import com.bonson.resource.viewmodel.AndroidViewModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class CenterViewModel extends AndroidViewModel {
    public final ObservableList<Message> centers = new ObservableArrayList<>();

    private MessageModel messageModel;

    @Inject
    public CenterViewModel(Application application, MessageModel messageModel) {
        super(application);
        this.messageModel = messageModel;
    }

    public void message() {
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        Disposable disposable = messageModel.message(Baby.baby.getFid())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    if (it.getCode().equals("0")) {
                        for (Message message : it.getBody()) {
                            for (int i = 0; i < centers.size(); i++) {
                                Message center = centers.get(i);
                                if (message.getFtype().equals(center.getFtype())) {
                                    message.setTitle(center.getTitle());
                                    message.setImg(center.getImg());
                                    centers.set(i, center);
                                }
                            }
                        }
                    } else {
                        view.toast(it.getMsg());
                    }
                }, e -> {
                    view.toast("出错了");
                });
        compositeDisposable.add(disposable);

    }
}
