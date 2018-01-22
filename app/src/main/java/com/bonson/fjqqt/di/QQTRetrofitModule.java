package com.bonson.fjqqt.di;

import android.content.Context;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;

import com.bonson.fjqqt.model.FApiServer;
import com.bonson.qqtapk.app.Const;
import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.resource.http.qqtconvert.QQTConverterFactory;
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
