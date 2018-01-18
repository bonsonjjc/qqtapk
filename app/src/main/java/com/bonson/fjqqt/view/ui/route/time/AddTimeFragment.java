package com.bonson.fjqqt.view.ui.route.time;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.FragmentAddRouteTimeBinding;
import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.resource.dialog.TimePickerDialog;
import com.bonson.resource.fragment.BaseFragment;

import javax.inject.Inject;

@ActivityScope
public class AddTimeFragment extends BaseFragment {
    private AddTimeViewModel viewModel;
    private TimePickerDialog dialog;

    @Inject
    public AddTimeFragment() {
    }

    public void setViewModel(AddTimeViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentAddRouteTimeBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_route_time, container, false);
        binding.setViewModel(viewModel);
        binding.tvTime.setOnClickListener(v -> {
            if (dialog == null) {
                dialog = new TimePickerDialog();
            }
            dialog.setValue(0, 0, 0, 0);
            dialog.setOnSaveListener((startHour, startMinute, endHour, endMinute) -> {
                String start = startHour + ":" + startMinute;
                String end = endHour + ":" + endMinute;
                viewModel.time.set(start + "~" + end);
                dialog.dismiss();
            });
            dialog.show(getFragmentManager(), "time");
        });
        viewModel.setView(this);
        return binding.getRoot();
    }
}
