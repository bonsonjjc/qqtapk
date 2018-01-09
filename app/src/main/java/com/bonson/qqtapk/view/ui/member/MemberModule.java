package com.bonson.qqtapk.view.ui.member;

import android.content.Context;

import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.di.FragmentScope;
import com.bonson.qqtapk.view.adapter.MemberAdapter;
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
public abstract class MemberModule {
    @ActivityScope
    @Binds
    abstract AndroidViewModel viewModel(MemberViewModel viewModel);

    @ActivityScope
    @Binds
    abstract AndroidViewModel fragment(PhoneViewModel viewModel);

    @ContributesAndroidInjector
    @FragmentScope
    abstract PhoneFragment inputFragment();

    @ActivityScope
    @Provides
    static MemberAdapter providesAdapter(MemberViewModel viewModel, Context context) {
        return new MemberAdapter(context, viewModel.members);
    }
}
