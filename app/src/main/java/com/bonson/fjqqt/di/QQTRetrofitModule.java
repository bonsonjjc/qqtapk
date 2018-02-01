package com.bonson.fjqqt.di;

import com.bonson.fjqqt.model.FApiServer;
import com.bonson.qqtapk.app.Const;
import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.utils.http.qqtconvert.QQTConverterFactory;
import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public abstract class QQTRetrofitModule {
    @Provides
    @ActivityScope
    static FApiServer providesFApiServer(Retrofit.Builder builder) {
        Retrofit temp = builder.baseUrl(Const.QQT_PATH)
                .addConverterFactory(QQTConverterFactory.create(new Gson()))
                .build();
        return temp.create(FApiServer.class);
    }
}
