package com.bonson.qqtapk.view.ui.ring;

import android.content.Context;
import android.databinding.ObservableArrayList;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.di.FragmentScope;
import com.bonson.library.utils.media.PlayerUtils;
import com.bonson.qqtapk.view.ui.info.select.Select;
import com.bonson.qqtapk.view.ui.info.select.SelectAdapter;
import com.bonson.qqtapk.view.ui.info.select.SelectFragment;
import com.bonson.qqtapk.view.ui.info.select.SelectViewModel;
import com.bonson.resource.viewmodel.AndroidViewModel;

import java.util.List;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by jiangjiancheng on 18/1/8.
 */
@Module
public abstract class RingModule {
    @ActivityScope
    @Binds
    abstract AndroidViewModel viewModel(RingViewModel viewModel);

    @ActivityScope
    @Binds
    abstract AndroidViewModel selectViewModel(SelectViewModel selectViewModel);

    @ActivityScope
    @Provides
    static PlayerUtils providesPlayer(Context context) {
        return new PlayerUtils(context);
    }

    @ActivityScope
    @Provides
    static List<Select> providesRings() {
        List<Select> selects = new ObservableArrayList<>();
        selects.add(new Select("铃声1", false, R.raw.r1, "1"));
        selects.add(new Select("铃声2", false, R.raw.r2, "2"));
        selects.add(new Select("铃声3", false, R.raw.r5, "5"));
        selects.add(new Select("铃声4", false, R.raw.r7, "7"));
        selects.add(new Select("铃声5", false, R.raw.r8, "8"));
        selects.add(new Select("铃声6", false, R.raw.r9, "9"));
        selects.add(new Select("铃声7", false, R.raw.r10, "10"));
        return selects;
    }

    @ActivityScope
    @Provides
    static RecyclerView.ItemDecoration itemDecoration(Context context) {
        return new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);
    }

    @FragmentScope
    @ContributesAndroidInjector
    abstract SelectFragment selectFragment();
}
