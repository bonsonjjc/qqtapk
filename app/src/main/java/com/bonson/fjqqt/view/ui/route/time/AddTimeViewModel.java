package com.bonson.fjqqt.view.ui.route.time;

import android.app.Application;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;

import com.bonson.fjqqt.model.bean.Route;
import com.bonson.fjqqt.model.bean.RouteTime;
import com.bonson.library.utils.NumberUtils;
import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.resource.activity.BaseView;
import com.bonson.resource.viewmodel.AndroidViewModel;

import javax.inject.Inject;

@ActivityScope
public class AddTimeViewModel extends AndroidViewModel {
    public final ObservableField<String> title = new ObservableField<>("");
    public final ObservableField<String> time = new ObservableField<>("00:00~00:00");
    public final ObservableBoolean state = new ObservableBoolean(true);
    public final ObservableInt type = new ObservableInt(1);


    RouteTime routeTime;
    private BaseView view;

    @Inject
    public AddTimeViewModel(Application application) {
        super(application);
    }

    public BaseView getView() {
        return view;
    }

    public void setView(BaseView view) {
        this.view = view;
    }

    public void onItem(int type) {
        this.type.set(type);
    }

    public void setRouteTime(RouteTime routeTime) {
        this.routeTime = routeTime;
        state.set("1".equals(routeTime.getFstate()));
        type.set(NumberUtils.parseInt(routeTime.getFtype(), 1));
        time.set(routeTime.getFbeghours() + ":" + routeTime.getFbegminutes() + "~" + routeTime.getFendhours() + ":" + routeTime.getFendminutes());
    }

    OnAddTimeListener onAddTimeListener;

    public void setOnAddTimeListener(OnAddTimeListener onAddTimeListener) {
        this.onAddTimeListener = onAddTimeListener;
    }

    public void onSave() {
        routeTime.setFtype(type.get()+"");
        routeTime.setFstate(state.get()?"1":"2");
        if (onAddTimeListener != null) {
            onAddTimeListener.onAddTime(routeTime);
        }
    }

    public interface OnAddTimeListener {
        void onAddTime(RouteTime routeTime);
    }
}
