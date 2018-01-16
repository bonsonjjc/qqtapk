package com.bonson.qqtapk.view.ui.setting;

import com.bonson.qqtapk.app.Const;
import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.model.data.ApiServer;
import com.bonson.qqtapk.model.data.TokenServer;
import com.bonson.qqtapk.model.data.setting.SettingModel;
import com.bonson.resource.viewmodel.AndroidViewModel;
import com.google.gson.Gson;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jiangjiancheng on 17/12/31.
 */
@Module
public abstract class SettingModule {
    @Binds
    @ActivityScope
    abstract AndroidViewModel viewModel(SettingViewModel viewModel);

    @Provides
    @ActivityScope
    static TokenServer providesServer(OkHttpClient client) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Const.API_PATH)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(TokenServer.class);
    }

    @Provides
    @ActivityScope
    static SettingModel providesSetting(ApiServer apiServer, TokenServer tokenServer) {
        return new SettingModel(apiServer);
    }
}
