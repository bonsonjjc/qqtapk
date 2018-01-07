package com.bonson.qqtapk.view.ui.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.FragmentMainBinding;
import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.resource.fragment.BaseFragment;

import javax.inject.Inject;

/**
 * Created by zjw on 2018/1/2.
 */
@ActivityScope
public class MainFragment extends BaseFragment {
    @Inject
    MainViewModel viewModel;

    @Inject
    public MainFragment() {
    }

    private FragmentMainBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        binding.mapView.onCreate(getContext(), savedInstanceState);
        binding.setViewModel(viewModel);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.setType("L08");
        viewModel.setBattery("20%");
        viewModel.setStepNumber("4000步");
        viewModel.setSleepTime("8小时");
        viewModel.initMenu();
    }

    @Override
    public void onResume() {
        super.onResume();
        binding.mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        binding.mapView.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding.mapView.onDestroy();
    }
}
