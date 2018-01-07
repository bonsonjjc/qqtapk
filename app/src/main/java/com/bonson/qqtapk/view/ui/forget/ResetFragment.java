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
    @Inject
    ResetViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentResetBinding inflate = DataBindingUtil.inflate(inflater, R.layout.fragment_reset, container, false);
        inflate.setViewModel(viewModel);
        return inflate.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String mobile = getArguments().getString("mobile");
        viewModel.setView(this);
        viewModel.setMobile(mobile);
    }

    public static ResetFragment newInstance(String mobile) {
        ResetFragment fragment = new ResetFragment();
        Bundle bundle = new Bundle();
        bundle.putString("mobile", mobile);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void start(String url) {
        getActivity().finish();
    }
}
