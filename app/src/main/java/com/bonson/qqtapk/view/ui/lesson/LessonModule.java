package com.bonson.qqtapk.view.ui.lesson;

import android.content.Context;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.view.adapter.LessonAdapter;
import com.bonson.resource.viewmodel.AndroidViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by jiangjiancheng on 17/12/31.
 */
@Module
public abstract class LessonModule {
    @Binds
    @ActivityScope
    abstract AndroidViewModel viewModel(LessonViewModel viewModel);

    @Provides
    @ActivityScope
    static LessonAdapter providesAdapter( Context context) {
        return new LessonAdapter(context);
    }
}
