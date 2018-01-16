package com.bonson.qqtapk.test;

import com.bonson.qqtapk.di.ActivityScope;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class TestActivityModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = {TestModule.class, TestRetrofitModule.class})
    abstract TestActivity testActivity();
}
