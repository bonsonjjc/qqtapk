package com.bonson.qqtapk.view.ui.forget;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.FragmentResetBinding;
import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.resource.fragment.BaseFragment;

import javax.inject.Inject;

/**
 * Created by zjw on 2018/1/3.
 */
@ActivityScope
public class ResetFragment extends BaseFragment {
    ResetViewModel viewModel;

    @Inject
    public ResetFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentResetBinding inflate = DataBindingUtil.inflate(inflater, R.layout.fragment_reset, container, false);
        inflate.setViewModel(viewModel);
        setViewModel(viewModel);
        return inflate.getRoot();
    }
}
