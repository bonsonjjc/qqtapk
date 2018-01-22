package com.bonson.qqtapk.view.ui.index;

import android.content.Context;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;

import com.bonson.fjqqt.model.FApiServer;
import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.model.data.ApiServer;
import com.bonson.qqtapk.model.data.location.LocationModel;
import com.bonson.qqtapk.model.data.location.LocationModelSource;
import com.bonson.resource.viewmodel.AndroidViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by zjw on 2017/12/29.
 */
@Module
public abstract class IndexModule {

    @Binds
    @ActivityScope
    abstract AndroidViewModel viewModel(IndexViewModel viewModel);

    @ActivityScope
    @Binds
    abstract AndroidViewModel mainViewModel(MainViewModel viewModel);

    @ActivityScope
    @Binds
    abstract AndroidViewModel locViewModel(LocationViewModel viewModel);

    @Provides
    @ActivityScope
    static RecyclerView.ItemDecoration itemDecoration(Context context) {
        return new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);
    }

    @Provides
    @ActivityScope
    static LocationModelSource locationModelSource(ApiServer apiServer){
        return  new LocationModel(apiServer);
    }
//
//    @Provides
//    @ActivityScope
//    static LocationModelSource locationModelSource(FApiServer apiServer){
//        return  new com.bonson.fjqqt.model.data.LocationModel(apiServer);
//    }

}
