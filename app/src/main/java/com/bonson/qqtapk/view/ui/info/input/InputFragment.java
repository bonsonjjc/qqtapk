package com.bonson.qqtapk.view.ui.info.input;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.FragmentInputBinding;
import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.resource.fragment.BaseFragment;

import javax.inject.Inject;

@ActivityScope
public class InputFragment extends BaseFragment {

    private InputViewModel viewModel;

    @Inject
    public InputFragment() {
    }

    public void setViewModel(InputViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentInputBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_input, container, false);
        binding.toolbar.getTvLeft().setOnClickListener(v -> back());
        binding.setViewModel(viewModel);
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        closeSoftKeyboard();
        super.onDestroyView();
    }
}
