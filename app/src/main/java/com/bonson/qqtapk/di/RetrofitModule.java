package com.bonson.qqtapk.di;

import com.bonson.qqtapk.app.Const;
import com.bonson.qqtapk.model.data.ApiServer;
import com.bonson.resource.http.qqtconvert.QQTConverterFactory;
import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by zjw on 2018/1/4.
 */
@Module
public abstract class RetrofitModule {

    @ActivityScope
    @Provides
    static ApiServer providesApiServer(Retrofit.Builder builder) {
        Retrofit temp = builder.baseUrl(Const.API_PATH)
                .addConverterFactory(QQTConverterFactory.create(new Gson()))
                .build();
        return temp.create(ApiServer.class);
    }
}
