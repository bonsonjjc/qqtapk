package com.bonson.qqtapk.di;

import android.content.Context;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;

import com.bonson.qqtapk.app.Const;
import com.bonson.resource.http.qqtfactory.GsonConvertFactory;
import com.bonson.qqtapk.model.data.ApiServer;
import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by zjw on 2018/1/4.
 */
@Module
public abstract class RetrofitModule {

    @Provides
    @ActivityScope
    static Retrofit providesRetrofit(OkHttpClient client) {
        return new retrofit2.Retrofit.Builder().baseUrl(Const.API_PATH)
                .client(client)
                .addConverterFactory(GsonConvertFactory.create(new Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @ActivityScope
    static ApiServer providesApiServer(Retrofit retrofit) {
        return retrofit.create(ApiServer.class);
    }

    @Provides
    @ActivityScope
    static RecyclerView.ItemDecoration itemDecoration(Context context) {
        return new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);
    }
}
