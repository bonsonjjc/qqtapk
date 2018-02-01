package com.bonson.qqtapk.di;

import android.app.Application;
import android.content.Context;

import com.bonson.qqtapk.app.Const;
import com.bonson.qqtapk.model.db.AppDataBase;
import com.bonson.qqtapk.model.db.CityDao;
import com.bonson.qqtapk.model.db.UserDao;
import com.bonson.qqtapk.utils.http.TokenInterceptor;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by zjw on 2017/12/29.
 */
@Module
abstract class AppModule {
    @Binds
    abstract Context bindContext(Application application);

    @Singleton
    @Provides
    static AppDataBase providesAppDataBase(Context context) {
        return AppDataBase.build(context);
    }

    @Singleton
    @Provides
    static UserDao userDao(AppDataBase dataBase) {
        return dataBase.userDao();
    }

    @Singleton
    @Provides
    static CityDao cityDao(Context context) {
        return new CityDao(context);
    }

    @Singleton
    @Provides
    static OkHttpClient client() {
        return new OkHttpClient.Builder().addInterceptor(new HttpLoggingInterceptor())
                .addInterceptor(new TokenInterceptor())
                .writeTimeout(30L, TimeUnit.SECONDS)
                .readTimeout(1L, TimeUnit.MINUTES)
                .build();
    }

    @Provides
    @Singleton
    static Retrofit.Builder providesRetrofitBuilder(OkHttpClient client) {
        return new Retrofit.Builder().baseUrl(Const.QQT_PATH)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
    }
}
