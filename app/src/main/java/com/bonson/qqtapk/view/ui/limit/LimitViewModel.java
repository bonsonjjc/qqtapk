package com.bonson.qqtapk.view.ui.limit;

import android.app.Application;
import android.databinding.ObservableBoolean;

import com.bonson.qqtapk.model.bean.Baby;
import com.bonson.qqtapk.model.bean.Limit;
import com.bonson.qqtapk.model.data.limit.LimitModel;
import com.bonson.resource.activity.BaseView;
import com.bonson.resource.viewmodel.AndroidViewModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by jiangjiancheng on 17/12/31.
 */
public class LimitViewModel extends AndroidViewModel {
    private BaseView view;

    private LimitModel limitModel;
    public ObservableBoolean open = new ObservableBoolean(false);
    private List<Limit> limits;

    @Inject
    public LimitViewModel(Application application, LimitModel limitModel) {
        super(application);
        this.limitModel = limitModel;
    }

    public void setView(BaseView view) {
        this.view = view;
    }

    public List<Limit> getLimits() {
        return limits;
    }

    public void setLimits(List<Limit> limits) {
        this.limits = limits;
        notifyChange();
    }

    public void limits() {
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        limitModel.limits(Baby.baby.getFid())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    view.toast(it.getMsg());
                    if (it.getCode().equals("0")) {
                        setLimits(it.getBody());
                    }
                }, e -> {
                    view.toast("出错了");
                });
    }
}
