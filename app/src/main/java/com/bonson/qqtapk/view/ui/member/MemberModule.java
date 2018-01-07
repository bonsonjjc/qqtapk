package com.bonson.qqtapk.view.ui.member;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.di.FragmentScope;
import com.bonson.qqtapk.view.ui.contacts.input.InputFragment;
import com.bonson.qqtapk.view.ui.contacts.input.InputViewModel;
import com.bonson.resource.viewmodel.AndroidViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by jiangjiancheng on 17/12/31.
 */
@Module
public abstract class MemberModule {
    @ActivityScope
    @Binds
    abstract AndroidViewModel viewModel(MemberViewModel viewModel);

    @ActivityScope
    @Binds
    abstract AndroidViewModel fragment(InputViewModel viewModel);

    @ContributesAndroidInjector
    @FragmentScope
    abstract InputFragment inputFragment();
}
