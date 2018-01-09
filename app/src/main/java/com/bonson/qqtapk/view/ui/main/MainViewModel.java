package com.bonson.qqtapk.view.ui.main;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.model.bean.Menu;
import com.bonson.resource.viewmodel.AndroidViewModel;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by zjw on 2018/1/2.
 */
@ActivityScope
public class MainViewModel extends AndroidViewModel {
    @Inject
    MainViewModel(Application application) {
        super(application);
    }

    public final ObservableField<String> type = new ObservableField<>();
    public final ObservableField<String> battery = new ObservableField<>();
    public final ObservableField<String> stepNumber = new ObservableField<>();
    public final ObservableField<String> sleepTime = new ObservableField<>();
    public final ObservableField<String> heart = new ObservableField<>();
    public final ObservableField<String> address = new ObservableField<>();
    public final ObservableField<String> time = new ObservableField<>();
    public final ObservableList<Menu> menus = new ObservableArrayList<>();

    public void initMenu() {
        menus.addAll(MenuHelper.createMenu());
    }
}
