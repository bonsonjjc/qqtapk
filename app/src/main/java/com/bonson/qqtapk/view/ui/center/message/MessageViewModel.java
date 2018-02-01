package com.bonson.qqtapk.view.ui.center.message;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.model.bean.Baby;
import com.bonson.qqtapk.model.bean.Message;
import com.bonson.qqtapk.model.data.center.MessageModel;
import com.bonson.qqtapk.viewmodel.UserViewModel;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

@ActivityScope
public class MessageViewModel extends UserViewModel {
    public final ObservableList<Message> messages = new ObservableArrayList<>();
    private MessageModel messageModel;

    @Inject
    public MessageViewModel(@NonNull Application application, MessageModel messageModel) {
        super(application);
        this.messageModel = messageModel;
    }

    public void list(String type) {
        Disposable disposable = messageModel.messageOfType(type, user().getBabyId(), messages.size(), 10)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    if (it.getCode().equals("0")) {
                        messages.addAll(it.getBody());
                    } else {
                        view.toast(it.getMsg());
                        Message message = new Message();
                        switch (type) {
                            case "18":
                                message.setFshort("监听成功");
                                break;
                            case "19":
                                message.setFshort("电量低于20%,请及时充电");
                                break;
                            case "24":
                                message.setFshort("您的宝贝已离开安全区域!");
                                break;
                            case "25":
                                message.setFshort("福建福州晋安五四北泰禾广场!");
                                message.setFx("26.1447671166");
                                message.setFy("119.3322610285");
                                message.setFid("15");
                                break;
                            case "61":
                                message.setFshort("手环已脱落");
                                break;
                            case "63":
                                message.setFshort("小明想添加你为好友!");
                                message.setFstate("0");
                                break;
                        }
                        messages.add(message);
                    }
                }, e -> {
                    view.toast("出错了");
                });
        compositeDisposable.add(disposable);
    }

    public void friend(int v, String type) {
        Message message = messages.get(v);
        view.load();
        Disposable disposable = messageModel.friend(message.getFid(),user().getBabyId(), type)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    view.dismiss();
                    view.toast(it.getMsg());
                    if (it.getCode().equals("0")) {
                        message.setFstate("1");
                        messages.set(v, message);
                    }
                }, e -> {
                    view.dismiss();
                    view.toast("出错了");
                });
        compositeDisposable.add(disposable);
    }
}
