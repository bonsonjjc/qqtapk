package com.bonson.qqtapk.view.ui.info;

import com.bonson.qqtapk.app.Const;
import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.di.FragmentScope;
import com.bonson.qqtapk.model.data.UploadServer;
import com.bonson.qqtapk.utils.http.string.StringConverterFactory;
import com.bonson.qqtapk.view.ui.info.input.InputFragment;
import com.bonson.qqtapk.view.ui.info.select.SelectFragment;
import com.bonson.qqtapk.view.ui.info.select.SelectViewModel;
import com.bonson.qqtapk.utils.http.qqtconvert.QQTConverterFactory;
import com.bonson.resource.viewmodel.AndroidViewModel;
import com.google.gson.Gson;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;
import retrofit2.Retrofit;

/**
 * Created by jiangjiancheng on 17/12/31.
 */
@Module
public abstract class InfoModule {
    @ActivityScope
    @Binds
    abstract AndroidViewModel viewModel(InfoViewModel viewModel);

    @FragmentScope
    @ContributesAndroidInjector
    abstract SelectFragment selectFragment();

    @FragmentScope
    @ContributesAndroidInjector
    abstract InputFragment inputFragment();

    @ActivityScope
    @Binds
    abstract AndroidViewModel selectViewModel(SelectViewModel selectViewModel);

    @ActivityScope
    @Provides
    static UploadServer uploadServer(Retrofit.Builder builder) {
        Retrofit build = builder.baseUrl(Const.ICON_PATH)
                .addConverterFactory(StringConverterFactory.create(new Gson(), false))
                .build();
        return build.create(UploadServer.class);
    }

}
