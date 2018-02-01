package com.bonson.qqtapk.view.ui.voice;

import com.bonson.qqtapk.app.Const;
import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.model.data.UploadServer;
import com.bonson.qqtapk.utils.http.string.StringConverterFactory;
import com.bonson.qqtapk.viewmodel.UserViewModel;
import com.google.gson.Gson;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by jiangjiancheng on 17/12/31.
 */
@Module
public abstract class VoiceModule {
    @ActivityScope
    @Binds
    abstract UserViewModel viewModel(VoiceViewModel viewModel);

    @ActivityScope
    @Provides
    static UploadServer uploadServer(OkHttpClient client) {
        Retrofit build = new Retrofit.Builder()
                .baseUrl(Const.VOICE_PATH)
                .addConverterFactory(StringConverterFactory.create(new Gson(), false))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
        return build.create(UploadServer.class);
    }
}
