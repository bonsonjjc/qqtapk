package com.bonson.qqtapk.view.ui.limits.add;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.FragmentLimitBinding;
import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.resource.fragment.BaseFragment;

import javax.inject.Inject;

@ActivityScope
public class LimitFragment extends BaseFragment {
    LimitViewModel viewModel;

    @Inject
    public LimitFragment() {
    }

    public LimitViewModel getViewModel() {
        return viewModel;
    }

    public void setViewModel(LimitViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentLimitBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_limit, container, false);
        binding.toolbar.getTvLeft().setOnClickListener(v -> back());
        binding.setViewModel(viewModel);
        return binding.getRoot();
    }

    public static LimitFragment newInstance() {
        Bundle args = new Bundle();
        LimitFragment fragment = new LimitFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
