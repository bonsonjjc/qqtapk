package com.bonson.qqtapk.view.ui.voice;

import android.app.Application;
import android.databinding.ObservableArrayList;

import com.bonson.library.utils.DateUtils;
import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.model.bean.Baby;
import com.bonson.qqtapk.model.bean.Voice;
import com.bonson.qqtapk.model.data.voice.VoiceModel;
import com.bonson.resource.activity.BaseView;
import com.bonson.resource.viewmodel.AndroidViewModel;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * Created by jiangjiancheng on 17/12/31.
 */
@ActivityScope
public class VoiceViewModel extends AndroidViewModel {
    private VoiceModel voiceModel;
    private BaseView view;
    public List<Voice> voices = new ObservableArrayList<>();

    @Inject
    public VoiceViewModel(Application application, VoiceModel voiceModel) {
        super(application);
        this.voiceModel = voiceModel;
    }

    public void setView(BaseView view) {
        this.view = view;
    }

    public void send() {
        Voice talk = new Voice();
        talk.setFbabyname("我");
        talk.setFuser(Baby.baby.getFuser());
        talk.setFtuser(Baby.baby.getFid());
        talk.setFtype("1");
        talk.setFtime("10");
        talk.setFctime(DateUtils.format(new Date(), "yyyy-MM-dd hh:mm:ss"));
        talk.setFpath("");
        talk.setState("3");
        voices.add(talk);

        Disposable disposable = voiceModel.addVoice(talk)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {

                }, e -> {

                });
        compositeDisposable.add(disposable);
    }

    public void recorder() {

    }

    public void voices() {
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        Disposable disposable = voiceModel.voices(Baby.baby.getFuser(), Baby.baby.getFid(), voices.size(), 10)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    if ("0".equals(it.getCode())) {
                        voices.addAll(it.getBody());
                        notifyChange();
                    } else {
                        view.toast(it.getMsg());
                    }
                }, e -> {
                    view.toast("出错了");
                });
        compositeDisposable.add(disposable);
    }
}
