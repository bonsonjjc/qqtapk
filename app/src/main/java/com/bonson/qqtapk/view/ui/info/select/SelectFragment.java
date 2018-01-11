package com.bonson.qqtapk.view.ui.info.select;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.FragmentSelectBinding;
import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.resource.fragment.BaseFragment;

import javax.inject.Inject;


@ActivityScope
public class SelectFragment extends BaseFragment {

    @Inject
    SelectAdapter adapter;

    @Inject
    RecyclerView.ItemDecoration itemDecoration;

    private SelectViewModel viewModel;

    @Inject
    public SelectFragment() {
    }

    public void setViewModel(SelectViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentSelectBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_select, container, false);
        binding.setViewModel(viewModel);
        binding.toolbar.getTvLeft().setOnClickListener(v -> back());
        binding.recSelect.setAdapter(adapter);
        binding.recSelect.addItemDecoration(itemDecoration);
        adapter.setOnItemClickListener(viewModel.getOnItemClickListener());
        return binding.getRoot();
    }

}
