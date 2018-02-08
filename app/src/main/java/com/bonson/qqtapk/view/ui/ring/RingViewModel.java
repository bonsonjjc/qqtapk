package com.bonson.qqtapk.view.ui.ring;

import android.app.Application;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;

import com.bonson.qqtapk.model.bean.Baby;
import com.bonson.qqtapk.model.bean.Device;
import com.bonson.qqtapk.model.data.ring.RingModel;
import com.bonson.library.utils.NumberUtils;
import com.bonson.library.utils.media.PlayerUtils;
import com.bonson.qqtapk.view.ui.info.select.Select;
import com.bonson.qqtapk.view.ui.info.select.SelectViewModel;
import com.bonson.qqtapk.viewmodel.UserViewModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * Created by jiangjiancheng on 18/1/8.
 */

public class RingViewModel extends UserViewModel {
    private RingModel ringModel;
    public final ObservableField<String> ringTitle = new ObservableField<>();
    public final ObservableField<String> ring = new ObservableField<>();
    public final ObservableInt callVolume = new ObservableInt(1);
    public final ObservableInt callerVolume = new ObservableInt(1);

    @Inject
    SelectViewModel viewModel;

    @Inject
    List<Select> selects;

    @Inject
    PlayerUtils playerUtils;

    @Inject
    public RingViewModel(Application application, RingModel ringModel) {
        super(application);
        this.ringModel = ringModel;
    }

    public void ring() {
        Device device = Device.device;
        callerVolume.set(NumberUtils.parseInt(device.getFivolume()));
        callVolume.set(NumberUtils.parseInt(device.getFcvolume()));
        ring.set(Device.device.getFcaller());
        for (Select select : selects) {
            if (select.getValue().equals(ring.get())) {
                ringTitle.set(select.getName());
                select.setChecked(true);
                notifyChange();
            }
        }
    }

    public void setRing() {
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        view.load();
        Disposable disposable = ringModel.ring(user().getBabyId(), user().getBabyId(), ring.get(), callVolume.get() + "", callerVolume.get() + "")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    view.dismiss();
                    view.toast(it.getMsg());
                    if (it.getCode().equals("0")) {
                        Device.device.setFcaller(ring.get());
                        Device.device.setFcvolume(callVolume.get() + "");
                        Device.device.setFivolume(callerVolume.get() + "");
                    }
                }, e -> {
                    view.dismiss();
                    view.toast("出错了");
                });
        compositeDisposable.add(disposable);
    }

    public SelectViewModel viewModel() {
        viewModel.title.set("选择铃声");
        viewModel.setSingle(true);
        viewModel.setOnSaveListener(() -> view.back());
        viewModel.setOnItemClickListener((v) -> {
            Select select = selects.get(v);
            playerUtils.play(select.getWht());
            ringTitle.set(select.getName());
            ring.set(select.getValue());
        });
        viewModel.setSelects(selects);
        return viewModel;
    }
}
