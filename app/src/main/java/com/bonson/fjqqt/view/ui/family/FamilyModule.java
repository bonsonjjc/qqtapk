package com.bonson.fjqqt.view.ui.family;

import android.content.Context;
import android.support.v7.widget.DividerItemDecoration;

import com.bonson.fjqqt.model.FApiServer;
import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.di.FragmentScope;
import com.bonson.qqtapk.model.bean.Family;
import com.bonson.qqtapk.model.data.family.FamilyDataSource;
import com.bonson.qqtapk.view.ui.contacts.phone.PhoneFragment;
import com.bonson.qqtapk.view.ui.contacts.phone.PhoneViewModel;
import com.bonson.qqtapk.view.ui.family.FamilyViewModel;
import com.bonson.resource.viewmodel.AndroidViewModel;

import java.util.ArrayList;
import java.util.List;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

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
    static FamilyDataSource providesDataSource(FApiServer fApiServer) {
        return new FamilyModel(fApiServer);
    }

    @Provides
    @ActivityScope
    static DividerItemDecoration itemDecoration(Context context) {
        return new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);
    }

    @Provides
    @ActivityScope
    static List<Family> families() {
        String[] icons = {"ico_sos", "ico_01", "ico_02", "ico_03", "ico_04", "ico05"};
        String[] names = {"SOS", "一号键", "二号键", "三号键", "四号键", "五号键"};

        List<Family> families = new ArrayList<>();
        for (int i = 0; i < icons.length; i++) {
            Family family = new Family();
            family.setFname(names[i]);
            family.setIcon(icons[i]);
            family.setFkey(i + "");
            families.add(family);
        }
        return families;
    }
}
