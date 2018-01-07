package com.bonson.qqtapk.di;

import com.bonson.library.convert.qqtfactory.GsonConvertFactory;
import com.bonson.qqtapk.model.data.area.SafeAreaServer;
import com.bonson.qqtapk.model.data.contacts.ContactsServer;
import com.bonson.qqtapk.model.data.family.FamilyServer;
import com.bonson.qqtapk.model.data.flower.FlowerServer;
import com.bonson.qqtapk.model.data.lesson.LessonServer;
import com.bonson.qqtapk.model.data.limit.LimitServer;
import com.bonson.qqtapk.model.data.member.MemberServer;
import com.bonson.qqtapk.model.data.mode.ModeServer;
import com.bonson.qqtapk.model.data.ring.RingServer;
import com.bonson.qqtapk.model.data.route.RouteServer;
import com.bonson.qqtapk.model.data.user.UserServer;
import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by zjw on 2018/1/4.
 */
@Module
public abstract class RetrofitModule {

    @Provides
    @ActivityScope
    static Retrofit providesRetrofit(OkHttpClient client) {
        return new retrofit2.Retrofit.Builder().baseUrl("http://wap.bfsafe.com:7070/api/")
                .client(client)
                .addConverterFactory(GsonConvertFactory.create(new Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @ActivityScope
    static FamilyServer familyServer(Retrofit retrofit) {
        return retrofit.create(FamilyServer.class);
    }

    @Provides
    @ActivityScope
    static SafeAreaServer areaServer(Retrofit retrofit) {
        return retrofit.create(SafeAreaServer.class);
    }

    @Provides
    @ActivityScope
    static UserServer userServer(Retrofit retrofit) {
        return retrofit.create(UserServer.class);
    }

    @Provides
    @ActivityScope
    static ContactsServer contactsServer(Retrofit retrofit) {
        return retrofit.create(ContactsServer.class);
    }

    @Provides
    @ActivityScope
    static LessonServer lessonsServer(Retrofit retrofit) {
        return retrofit.create(LessonServer.class);
    }

    @Provides
    @ActivityScope
    static LimitServer limitServer(Retrofit retrofit) {
        return retrofit.create(LimitServer.class);
    }

    @Provides
    @ActivityScope
    static MemberServer memberServer(Retrofit retrofit) {
        return retrofit.create(MemberServer.class);
    }

    @Provides
    @ActivityScope
    static ModeServer modeServer(Retrofit retrofit) {
        return retrofit.create(ModeServer.class);
    }

    @Provides
    @ActivityScope
    static RouteServer routeServer(Retrofit retrofit) {
        return retrofit.create(RouteServer.class);
    }

    @Provides
    @ActivityScope
    static FlowerServer flowerServer(Retrofit retrofit) {
        return retrofit.create(FlowerServer.class);
    }

    @Provides
    @ActivityScope
    static RingServer ringServer(Retrofit retrofit) {
        return retrofit.create(RingServer.class);
    }

}
