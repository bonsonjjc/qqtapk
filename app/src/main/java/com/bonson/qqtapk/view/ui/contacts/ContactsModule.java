package com.bonson.qqtapk.view.ui.contacts;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.di.FragmentScope;
import com.bonson.qqtapk.view.ui.contacts.phone.PhoneFragment;
import com.bonson.qqtapk.view.ui.contacts.phone.PhoneViewModel;
import com.bonson.qqtapk.view.ui.info.select.SelectFragment;
import com.bonson.qqtapk.view.ui.info.select.SelectViewModel;
import com.bonson.resource.viewmodel.AndroidViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by jiangjiancheng on 17/12/31.
 */
@Module
public abstract class ContactsModule {
    @ActivityScope
    @Binds
    abstract AndroidViewModel viewModel(ContactsViewModel viewModel);

    @ActivityScope
    @Binds
    abstract AndroidViewModel fragment(PhoneViewModel viewModel);

    @ActivityScope
    @Binds
    abstract AndroidViewModel selectViewModel(SelectViewModel selectViewModel);

    @ContributesAndroidInjector
    @FragmentScope
    abstract PhoneFragment inputFragment();


    @FragmentScope
    @ContributesAndroidInjector
    abstract SelectFragment selectFragment();
}