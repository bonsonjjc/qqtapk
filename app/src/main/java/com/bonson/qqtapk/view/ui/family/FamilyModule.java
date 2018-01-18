package com.bonson.qqtapk.view.ui.family;

import android.content.Context;

import com.bonson.fjqqt.model.FApiServer;
import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.di.FragmentScope;
import com.bonson.qqtapk.model.bean.Baby;
import com.bonson.qqtapk.model.data.ApiServer;
import com.bonson.qqtapk.model.data.family.FamilyModel;
import com.bonson.qqtapk.model.data.family.FamilyModelDataSource;
import com.bonson.qqtapk.view.adapter.FamilyAdapter;
import com.bonson.qqtapk.view.ui.contacts.phone.PhoneFragment;
import com.bonson.qqtapk.view.ui.contacts.phone.PhoneViewModel;
import com.bonson.resource.viewmodel.AndroidViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by jiangjiancheng on 17/12/31.
 */
@Module
public abstract class FamilyModule {
    @ActivityScope
    @Binds
    abstract AndroidViewModel viewModel(FamilyViewModel viewModel);

    @ActivityScope
    @Binds
    abstract AndroidViewModel fragment(PhoneViewModel viewModel);

    @ContributesAndroidInjector
    @FragmentScope
    abstract PhoneFragment inputFragment();

    @Provides
    @ActivityScope
    static FamilyAdapter familyAdapter(Context context) {
        return new FamilyAdapter(context);
    }


    @Provides
    @ActivityScope
    static FamilyModelDataSource providesDataSource(ApiServer apiServer, FApiServer fApiServer) {
        if (Baby.baby.getFtag().equals("L08")) {
            return new FamilyModel(apiServer);
        }
        return new com.bonson.fjqqt.model.data.FamilyModel(fApiServer);
    }
}

