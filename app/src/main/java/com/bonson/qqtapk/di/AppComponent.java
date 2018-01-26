package com.bonson.qqtapk.di;

import android.app.Application;

import com.bonson.fjqqt.di.UIModule;
import com.bonson.qqtapk.app.App;
import com.bonson.qqtapk.model.db.UserDao;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import okhttp3.OkHttpClient;

/**
 * Created by zjw on 2017/12/29.
 */
@Singleton
@Component(modules = {AppModule.class, ActivityModule.class, UIModule.class, AndroidSupportInjectionModule.class})
public interface AppComponent extends AndroidInjector<App> {
    UserDao userDao();

    OkHttpClient client();

    @Component.Builder
    interface Builder {

        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }
}
