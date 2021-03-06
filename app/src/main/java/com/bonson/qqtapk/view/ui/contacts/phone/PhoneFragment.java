package com.bonson.qqtapk.view.ui.contacts.phone;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.FragmentPhoneBinding;
import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.resource.fragment.BaseFragment;

import javax.inject.Inject;

/**
 * Created by jiangjiancheng on 18/1/6.
 */
@ActivityScope
public class PhoneFragment extends BaseFragment<FragmentPhoneBinding> {
    private PhoneViewModel viewModel;

    @Inject
    public PhoneFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_phone, container, false);
        binding.setViewModel(viewModel);
        setViewModel(viewModel);
        binding.toolbar.getTvLeft().setOnClickListener(v -> back());
        return binding.getRoot();
    }

    public void setPhoneViewModel(PhoneViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        closeSoftKeyboard();
    }
}
