package com.bonson.qqtapk.view.ui.info;

import android.app.Application;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.model.bean.Baby;
import com.bonson.resource.viewmodel.AndroidViewModel;

import javax.inject.Inject;

/**
 * Created by jiangjiancheng on 17/12/31.
 */
@ActivityScope
public class InfoViewModel extends AndroidViewModel {
    private Baby baby;

    @Inject
    public InfoViewModel(Application application) {
        super(application);
    }

    public Baby getBaby() {
        return baby;
    }

    public void setBaby(Baby baby) {
        this.baby = baby;
    }

    public void change() {

    }

    public void unbind() {

    }
}
