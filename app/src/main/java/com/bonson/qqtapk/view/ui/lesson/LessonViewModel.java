package com.bonson.qqtapk.view.ui.lesson;

import android.app.Application;
import android.databinding.ObservableField;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.model.bean.Baby;
import com.bonson.qqtapk.model.bean.Lesson;
import com.bonson.qqtapk.model.data.lesson.LessonModel;
import com.bonson.resource.activity.BaseView;
import com.bonson.resource.viewmodel.AndroidViewModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by jiangjiancheng on 17/12/31.
 */
@ActivityScope
public class LessonViewModel extends AndroidViewModel {
    private List<Lesson> lessons;

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

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
        notifyChange();
    }

    public BaseView getView() {
        return view;
    }

    public void setView(BaseView view) {
        this.view = view;
    }

    void lessons() {
        if (!isNetWork()) {
            view.toast("网络不可用");
            return;
        }
        lessonModel.lessons(Baby.baby.getFid())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    view.toast(it.getMsg());
                    if (it.getCode().equals("0")) {
                        setLessons(it.getBody());
                    }
                }, e -> {
                    view.toast("出错了");
                });
    }

    public void update() {

    }
}
