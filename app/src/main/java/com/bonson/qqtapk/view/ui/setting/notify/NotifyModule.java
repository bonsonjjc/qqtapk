package com.bonson.qqtapk.view.ui.setting.notify;

import android.content.Context;

import com.bonson.library.utils.PreferencesHelper;
import com.bonson.qqtapk.app.Const;
import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.resource.viewmodel.AndroidViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by jiangjiancheng on 17/12/31.
 */

@Module
public abstract class NotifyModule {
    @ActivityScope
    @Binds
    abstract AndroidViewModel viewModel(NotifyViewModel viewModel);

    @ActivityScope
    @Provides
    static PreferencesHelper providesPreHelper(Context context) {
        return PreferencesHelper.create(context, Const.PREFERENCES_FILE);
    }
}
