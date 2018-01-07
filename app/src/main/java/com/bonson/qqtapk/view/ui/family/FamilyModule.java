package com.bonson.qqtapk.view.ui.family;

import com.bonson.library.convert.qqtfactory.GsonConvertFactory;
import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.model.data.family.FamilyModel;
import com.bonson.qqtapk.model.data.family.FamilyServer;
import com.bonson.qqtapk.model.db.BabyDao;
import com.bonson.qqtapk.model.db.UserDao;
import com.bonson.resource.viewmodel.AndroidViewModel;
import com.google.gson.Gson;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by jiangjiancheng on 17/12/31.
 */
@Module
public abstract class FamilyModule {
    @ActivityScope
    @Binds
    abstract AndroidViewModel viewModel(FamilyViewModel viewModel);
}

