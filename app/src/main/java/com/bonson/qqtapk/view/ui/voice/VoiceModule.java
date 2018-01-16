package com.bonson.qqtapk.view.ui.voice;

import android.content.Context;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.model.data.UploadServer;
import com.bonson.qqtapk.view.adapter.VoiceAdapter;
import com.bonson.resource.http.qqtfactory.StringConvertFactory;
import com.bonson.resource.viewmodel.AndroidViewModel;
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
    abstract AndroidViewModel viewModel(VoiceViewModel viewModel);

    @ActivityScope
    @Provides
    static VoiceAdapter providesAdapter(Context context) {
        return new VoiceAdapter(context);
    }

    @ActivityScope
    @Provides
    static UploadServer uploadServer(OkHttpClient client, String url) {
        Retrofit build = new Retrofit.Builder().baseUrl(url)
                .client(client)
                .addConverterFactory(StringConvertFactory.create(new Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return build.create(UploadServer.class);
    }

    @ActivityScope
    @Provides
    static String filePath() {
        return "http://amr.bfsafe.com";
    }
}
