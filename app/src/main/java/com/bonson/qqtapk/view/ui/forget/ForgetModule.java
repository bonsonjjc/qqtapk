package com.bonson.qqtapk.view.ui.forget;

import com.bonson.library.convert.qqtfactory.GsonConvertFactory;
import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.di.FragmentScope;
import com.bonson.qqtapk.model.data.user.UserModel;
import com.bonson.qqtapk.model.data.user.UserServer;
import com.bonson.qqtapk.model.db.BabyDao;
import com.bonson.qqtapk.model.db.UserDao;
import com.bonson.qqtapk.view.ui.register.VerifyViewModel;
import com.bonson.resource.viewmodel.AndroidViewModel;
import com.google.gson.Gson;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by zjw on 2017/12/29.
 */

@Module
public abstract class ForgetModule {
    @Binds
    @ActivityScope
    abstract AndroidViewModel viewModel(ForgetViewModel viewModel);

    @Binds
    @ActivityScope
    abstract AndroidViewModel verifyModel(VerifyViewModel viewModel);

    @Binds
    @ActivityScope
    abstract AndroidViewModel resetModel(ResetViewModel viewModel);

    @ContributesAndroidInjector
    @FragmentScope
    abstract ResetFragment resetFragment();
}
