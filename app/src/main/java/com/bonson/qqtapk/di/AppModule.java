package com.bonson.qqtapk.di;

import android.app.Application;
import android.content.Context;

import com.bonson.qqtapk.model.db.AppDataBase;
import com.bonson.qqtapk.model.db.BabyDao;
import com.bonson.qqtapk.model.db.UserDao;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

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
    static BabyDao babyDao(AppDataBase dataBase) {
        return dataBase.babyDao();
    }

    @Singleton
    @Provides
    static OkHttpClient client() {
        return new OkHttpClient.Builder().addInterceptor(new HttpLoggingInterceptor())
                .writeTimeout(30L, TimeUnit.SECONDS)
                .readTimeout(1L, TimeUnit.MINUTES)
                .build();
    }
}
