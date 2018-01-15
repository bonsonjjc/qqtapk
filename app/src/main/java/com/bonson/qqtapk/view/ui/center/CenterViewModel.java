package com.bonson.qqtapk.view.ui.center;

import android.app.Application;
import android.databinding.Bindable;

import com.bonson.qqtapk.BR;
import com.bonson.qqtapk.model.bean.Message;
import com.bonson.qqtapk.model.data.center.MessageModel;
import com.bonson.resource.activity.BaseView;
import com.bonson.resource.viewmodel.AndroidViewModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class CenterViewModel extends AndroidViewModel {
    private List<Message> centers;

    private MessageModel messageModel;

    private BaseView view;

    @Inject
    public CenterViewModel(Application application, MessageModel messageModel) {
        super(application);
        this.messageModel = messageModel;
    }

    @Bindable
    public List<Message> getCenters() {
        return centers;
    }


    public void setCenters(List<Message> centers) {
        this.centers = centers;
        notifyPropertyChanged(BR.centers);
    }

    public void setView(BaseView view) {
        this.view = view;
    }

    public void message() {
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        Disposable disposable = messageModel.message()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    if (it.getCode().equals("0")) {
                        for (Message message : it.getBody()) {
                            for (Message center : centers) {
                                if (message.getFtype().equals(center.getFtype())) {
                                    message.setTitle(center.getTitle());
                                    message.setImg(center.getImg());
                                }
                            }
                        }
                        centers.clear();
                        setCenters(centers);
                    } else {
                        view.toast(it.getMsg());
                    }
                }, e -> {
                    view.toast("出错了");
                });
        compositeDisposable.add(disposable);

    }
}
