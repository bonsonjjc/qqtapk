package com.bonson.qqtapk.view.ui.index.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.app.Route;
import com.bonson.qqtapk.databinding.FragmentMainBinding;
import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.model.bean.Device;
import com.bonson.qqtapk.view.adapter.MenuAdapter;
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

    @Inject
    MenuAdapter menuAdapter;

    DividerItemDecoration itemDecoration;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        binding.mapView.onCreate(getContext(), savedInstanceState);
        binding.setViewModel(viewModel);
        binding.recMenus.setAdapter(menuAdapter);
        itemDecoration=new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(ActivityCompat.getDrawable(getContext(),R.drawable.ico_menu_line));
        binding.recMenus.addItemDecoration(itemDecoration);
        binding.fbLocation.setOnClickListener(v -> viewModel.viewModel.location());
        binding.fbAction1.setOnClickListener(v -> viewModel.listener());
        binding.fbAction2.setOnClickListener(v -> viewModel.lockMachine());
        binding.fbAction3.setOnClickListener(v -> {
            if (Device.device.getFtversion().equals("W001")) {
                toast("当前手环型号不支持此功能");
                return;
            }
            start(Route.voice);
        });
        binding.fbAction4.setOnClickListener(v -> {
            if (Device.device.getFtversion().equals("W001")) {
                toast("当前手环型号不支持此功能");
                return;
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.setView(this);
        viewModel.initMenu();
        viewModel.device();
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
