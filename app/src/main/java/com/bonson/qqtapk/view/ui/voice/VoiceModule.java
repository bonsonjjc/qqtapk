package com.bonson.qqtapk.view.ui.voice;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.model.data.UploadServer;
import com.bonson.qqtapk.utils.http.qqtconvert.QQTConverterFactory;
import com.bonson.qqtapk.utils.http.string.StringConverterFactory;
import com.bonson.resource.viewmodel.AndroidViewModel;
import com.google.gson.Gson;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by jiangjiancheng on 17/12/31.
 */
@Module
public abstract class VoiceModule {
    @ActivityScope
    @Binds
    abstract AndroidViewModel viewModel(VoiceViewModel viewModel);

    @ActivityScope
    @Provides
    static UploadServer uploadServer(Retrofit.Builder builder, String url) {
        Retrofit build = builder.baseUrl(url)
                .addConverterFactory(StringConverterFactory.create(new Gson(), false))
                .build();
        return build.create(UploadServer.class);
    }

    @ActivityScope
    @Provides
    static String filePath() {
        return "http://amr.bfsafe.com";
    }
}
