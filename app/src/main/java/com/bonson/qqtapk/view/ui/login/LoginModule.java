package com.bonson.qqtapk.view.ui.login;

import com.bonson.fjqqt.di.FjqqtScope;
import com.bonson.fjqqt.model.FApiServer;
import com.bonson.qqtapk.app.Const;
import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.model.data.ApiServer;
import com.bonson.resource.http.qqtconvert.QQTConverterFactory;
import com.bonson.resource.viewmodel.AndroidViewModel;
import com.google.gson.Gson;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by zjw on 2017/12/29.
 */
@Module
public abstract class LoginModule {
    @Binds
    @FjqqtScope
    abstract AndroidViewModel viewModel(LoginViewModel viewModel);


    @Provides
    @FjqqtScope
    static FApiServer providesApiServer(Retrofit.Builder builder) {
        Retrofit temp = builder.baseUrl(Const.QQT_PATH)
                .addConverterFactory(QQTConverterFactory.create(new Gson()))
                .build();
        return temp.create(FApiServer.class);
    }
}
