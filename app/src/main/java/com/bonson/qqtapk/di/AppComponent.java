package com.bonson.qqtapk.di;

import android.app.Application;

import com.bonson.qqtapk.app.App;
import com.bonson.qqtapk.model.db.BabyDao;
import com.bonson.qqtapk.model.db.UserDao;
import com.bonson.qqtapk.test.TestActivityModule;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

import javax.inject.Singleton;

import okhttp3.OkHttpClient;

/**
 * Created by zjw on 2017/12/29.
 */
@Singleton
@Component(modules = {AppModule.class, ActivityModule.class,TestActivityModule.class, AndroidSupportInjectionModule.class})
public interface AppComponent extends AndroidInjector<App> {

    UserDao userDao();

    BabyDao babyDao();

    OkHttpClient client();

    @Component.Builder
    interface Builder {

        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }
}
