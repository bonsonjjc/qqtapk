package com.bonson.qqtapk.view.ui.limits.add;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;

import com.bonson.library.utils.NumberUtils;
import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.FragmentLimitBinding;
import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.utils.TimeUtils;
import com.bonson.resource.dialog.TimePickerDialog;
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

    TimePickerDialog timePickerDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentLimitBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_limit, container, false);
        binding.toolbar.getTvLeft().setOnClickListener(v -> back());
        binding.setViewModel(viewModel);
        binding.tvTimeOne.setOnClickListener(v -> {
            if (timePickerDialog == null) {
                timePickerDialog = TimePickerDialog.builder(getActivity());
            }
            timePickerDialog.setOnSaveListener((startHour, startMinute, endHour, endMinute) -> {
                String start = startHour + startMinute;
                String end = endHour + endMinute;
                if (NumberUtils.parseInt(start) < NumberUtils.parseInt(end)) {
                    viewModel.getLimit().setFbegin(start);
                    viewModel.getLimit().setFend(end);
                    viewModel.notifyChange();
                    timePickerDialog.dismiss();
                } else {
                    toast("开始时间不能大于等于结束时间");
                }
            });
            String[] startTime = TimeUtils.split(viewModel.getLimit().getFbegin());
            String[] endTime = TimeUtils.split(viewModel.getLimit().getFend());

            timePickerDialog.setValue(startTime[0], startTime[1], endTime[0], endTime[1]);
            timePickerDialog.show(binding.getRoot(), Gravity.BOTTOM);
        });
        binding.tvTimeTwo.setOnClickListener(v -> {
            if (timePickerDialog == null) {
                timePickerDialog = TimePickerDialog.builder(getActivity());

            }
            timePickerDialog.setOnSaveListener((startHour, startMinute, endHour, endMinute) -> {
                String start = startHour + startMinute;
                String end = endHour + endMinute;
                if (NumberUtils.parseInt(start) < NumberUtils.parseInt(end)) {
                    viewModel.getLimit().setFfbegin(start);
                    viewModel.getLimit().setFfend(end);
                    viewModel.notifyChange();
                    timePickerDialog.dismiss();
                } else {
                    toast("开始时间不能大于等于结束时间");
                }
            });
            String[] startTime = TimeUtils.split(viewModel.getLimit().getFfbegin());
            String[] endTime = TimeUtils.split(viewModel.getLimit().getFfend());

            timePickerDialog.setValue(startTime[0], startTime[1], endTime[0], endTime[1]);
            timePickerDialog.show(binding.getRoot(), Gravity.BOTTOM);
        });
        binding.tvDelete.setOnClickListener(v -> {
            viewModel.delete();
        });
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (timePickerDialog != null && timePickerDialog.isShowing()) {
            timePickerDialog.dismiss();
        }
    }

    public static LimitFragment newInstance() {
        Bundle args = new Bundle();
        LimitFragment fragment = new LimitFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
