package com.bonson.fjqqt.view.ui.terminal.alarm.add;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.FragmentAddAlarmBinding;
import com.bonson.resource.fragment.BaseFragment;

import javax.inject.Inject;


public class AddAlarmFragment extends BaseFragment {

    private AddAlarmViewModel viewModel;

    @Inject
    public AddAlarmFragment() {
    }

    public void setViewModel(AddAlarmViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentAddAlarmBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_alarm, container, false);
        binding.toolbar.setTitle("生活提醒");
        binding.toolbar.getTvLeft().setOnClickListener(v -> back());
        binding.npHour.setFormatter(value -> {
            if (value < 10) {
                return "0" + value;
            }
            return value + "";
        });
        binding.npHour.setDisplayedValues(null);
        binding.npHour.setMinValue(0);
        binding.npHour.setMaxValue(23);
        binding.npHour.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        binding.npHour.setOnValueChangedListener((picker, oldVal, newVal) -> {
            viewModel.hour = newVal;
        });

        binding.npMinute.setFormatter(value -> {
            if (value < 10) {
                return "0" + value;
            }
            return value + "";
        });
        binding.npMinute.setMinValue(0);
        binding.npMinute.setMaxValue(59);
        binding.npMinute.setDisplayedValues(null);
        binding.npMinute.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        binding.npHour.setOnValueChangedListener((picker, oldVal, newVal) -> {
            viewModel.minute = newVal;
        });
        binding.setViewModel(viewModel);

        binding.npHour.setValue(viewModel.hour);
        binding.npMinute.setValue(viewModel.minute);


        binding.flToday.setOnClickListener(v -> {
            viewModel.type.set(1);
        });
        binding.flWorkDay.setOnClickListener(v -> {
            viewModel.type.set(2);
        });
        binding.flToday.setOnClickListener(v -> {
            viewModel.type.set(3);
        });
        binding.flToday.setOnClickListener(v -> {
            viewModel.type.set(4);
        });
        return binding.getRoot();
    }
}
