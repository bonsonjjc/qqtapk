package com.bonson.qqtapk.view.ui.ring;

import android.app.Application;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;

import com.bonson.qqtapk.model.bean.Baby;
import com.bonson.qqtapk.model.bean.Device;
import com.bonson.qqtapk.model.data.ring.RingModel;
import com.bonson.library.utils.NumberUtils;
import com.bonson.library.utils.PlayerUtils;
import com.bonson.qqtapk.view.ui.info.select.Select;
import com.bonson.qqtapk.view.ui.info.select.SelectViewModel;
import com.bonson.resource.activity.BaseView;
import com.bonson.resource.viewmodel.AndroidViewModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * Created by jiangjiancheng on 18/1/8.
 */

public class RingViewModel extends AndroidViewModel {
    private RingModel ringModel;
    public ObservableField<String> ringTitle = new ObservableField<>();
    public ObservableField<String> ring = new ObservableField<>();
    public ObservableInt callVolume = new ObservableInt(1);
    public ObservableInt callerVolume = new ObservableInt(1);

    private SelectViewModel viewModel;

    @Inject
    List<Select> selects;
    private BaseView view;

    @Inject
    PlayerUtils playerUtils;

    @Inject
    public RingViewModel(Application application, RingModel ringModel, SelectViewModel selectViewModel) {
        super(application);
        this.ringModel = ringModel;
        this.viewModel = selectViewModel;
    }

    public void ring() {
        Device device = Device.device;
        callerVolume.set(NumberUtils.parseInt(device.getFivolume()));
        callVolume.set(NumberUtils.parseInt(device.getFcvolume()));
        ring.set(Device.device.getFcaller());
        for (Select select : selects) {
            if (ring.get().equals(select.getValue())) {
                ringTitle.set(select.getName());
                select.setChecked(true);
                notifyChange();
            }
        }
    }

    public void setView(BaseView view) {
        this.view = view;
    }

    public void setRing() {
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        view.load();
        Disposable disposable = ringModel.ring(Baby.baby.getFid(), Baby.baby.getFuser(), ring.get(), callVolume.get() + "", callerVolume.get() + "")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    view.dismiss();
                    view.toast(it.getMsg());
                    if (it.getCode().equals("0")) {
                        Device.device.setFcaller(ring.get());
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
            viewModel.select(v);
            Select select = selects.get(v);
            playerUtils.play(select.getWht());
            ringTitle.set(select.getName());
            ring.set(select.getValue());
        });
        viewModel.setSelects(selects);
        return viewModel;
    }
}
