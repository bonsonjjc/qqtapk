package com.bonson.qqtapk.view.ui.info.select;

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
import com.bonson.qqtapk.utils.binding.AdapterDataChangeFactory;
import com.bonson.resource.fragment.BaseFragment;

import javax.inject.Inject;


@ActivityScope
public class SelectFragment extends BaseFragment<FragmentSelectBinding> {
    @Inject
    SelectAdapter adapter;

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
        setBindingLayout(inflater, R.layout.fragment_select, container);
        binding.setViewModel(viewModel);
        setViewModel(viewModel);

        binding.toolbar.getTvLeft().setOnClickListener(v -> back());
        binding.recSelect.setAdapter(adapter);
        adapter.setOnItemClickListener(v -> {
            viewModel.select(v);
            if (viewModel.getOnItemClickListener() != null) {
                viewModel.getOnItemClickListener().itemClick(v);
            }
        });
        AdapterDataChangeFactory.create(adapter)
                .attach(viewModel.selects);
        return binding.getRoot();
    }

}
