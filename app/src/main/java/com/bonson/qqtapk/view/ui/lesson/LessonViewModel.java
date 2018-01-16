package com.bonson.qqtapk.view.ui.lesson;

import android.app.Application;
import android.databinding.ObservableArrayList;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.model.bean.Baby;
import com.bonson.qqtapk.model.bean.Lesson;
import com.bonson.qqtapk.model.data.lesson.LessonModel;
import com.bonson.resource.activity.BaseView;
import com.bonson.resource.viewmodel.AndroidViewModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * Created by jiangjiancheng on 17/12/31.
 */
@ActivityScope
public class LessonViewModel extends AndroidViewModel {
    private final List<Lesson> lessons = new ObservableArrayList<>();

    private LessonModel lessonModel;

    private BaseView view;

    @Inject
    LessonViewModel(Application application, LessonModel lessonModel) {
        super(application);
        this.lessonModel = lessonModel;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }


    public BaseView getView() {
        return view;
    }

    public void setView(BaseView view) {
        this.view = view;
    }

    public void lessons() {
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        view.load();
        Disposable disposable = lessonModel.lessons(Baby.baby.getFid())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    view.dismiss();
                    view.toast(it.getMsg());
                    if (it.getCode().equals("0")) {
                        lessons.addAll(it.getBody());
                        notifyChange();
                    }
                }, e -> {
                    view.dismiss();
                    view.toast("出错了");
                });
        compositeDisposable.add(disposable);
    }

    public void update() {
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        view.load();
        Disposable disposable = lessonModel.update(Baby.baby.getFid(), lessons)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    view.toast(it.getMsg());
                    view.dismiss();
                }, e -> {
                    view.dismiss();
                    view.toast("出错了");
                });
        compositeDisposable.add(disposable);
    }
}
