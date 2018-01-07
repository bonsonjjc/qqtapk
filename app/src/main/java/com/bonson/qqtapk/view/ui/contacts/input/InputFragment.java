package com.bonson.qqtapk.view.ui.contacts.input;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.FragmentContactsBinding;
import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.resource.fragment.BaseFragment;

import javax.inject.Inject;

/**
 * Created by jiangjiancheng on 18/1/6.
 */
@ActivityScope
public class InputFragment extends BaseFragment {
    InputViewModel viewModel;

    FragmentContactsBinding binding;

    @Inject
    public InputFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_contacts, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.setViewModel(viewModel);
        binding.toolbar.getTvLeft().setOnClickListener(v -> {
            getActivity().onBackPressed();
        });
    }

    public InputViewModel getViewModel() {
        return viewModel;
    }

    public void setViewModel(InputViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public static InputFragment newInstance() {
        Bundle args = new Bundle();
        InputFragment fragment = new InputFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
