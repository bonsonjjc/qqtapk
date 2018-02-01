package com.bonson.qqtapk.view.ui.center;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.bonson.qqtapk.model.bean.Baby;
import com.bonson.qqtapk.model.bean.Message;
import com.bonson.qqtapk.model.data.center.MessageModel;
import com.bonson.qqtapk.viewmodel.UserViewModel;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class CenterViewModel extends UserViewModel {
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
        view.load();
        Disposable disposable = messageModel.message(user().getBabyId())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    view.dismiss();
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
                    view.dismiss();
                    view.toast("出错了");
                });
        compositeDisposable.add(disposable);
    }
}
