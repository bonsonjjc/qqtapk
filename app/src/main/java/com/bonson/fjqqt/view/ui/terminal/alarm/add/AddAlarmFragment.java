package com.bonson.fjqqt.view.ui.terminal.alarm.add;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;

import com.bonson.fjqqt.view.ui.terminal.alarm.Alarm;
import com.bonson.fjqqt.view.ui.terminal.alarm.AlarmUtils;
import com.bonson.library.utils.LogUtils;
import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.FragmentAddAlarmBinding;
import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.qqtapk.view.ui.info.select.SelectFragment;
import com.bonson.qqtapk.view.ui.info.select.SelectViewModel;
import com.bonson.resource.fragment.BaseFragment;

import javax.inject.Inject;

@ActivityScope
public class AddAlarmFragment extends BaseFragment<FragmentAddAlarmBinding> {

    AddAlarmViewModel viewModel;
    @Inject
    SelectViewModel selectViewModel;
    SelectFragment selectFragment;

    @Inject
    public AddAlarmFragment() {
    }

    public AddAlarmViewModel getViewModel() {
        return viewModel;
    }

    public void setViewModel(AddAlarmViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void setSelectFragment(SelectFragment selectFragment) {
        this.selectFragment = selectFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setBindingLayout(inflater, R.layout.fragment_add_alarm, container);
        binding.toolbar.setTitle("生活提醒");
        binding.toolbar.getTvLeft().setOnClickListener(v -> back());
        binding.toolbar.getTvRight().setOnClickListener(v -> {
            if (onAlarmSaveListener != null) {
                onAlarmSaveListener.onSave(viewModel.getAlarm());
            }
        });
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
        binding.setViewModel(viewModel);

        binding.flToday.setOnClickListener(v -> {
            viewModel.setCycle(AlarmUtils.TYPE_TODAY, AlarmUtils.TODAY);
        });
        binding.flWorkDay.setOnClickListener(v -> {
            viewModel.setCycle(AlarmUtils.TYPE_WEEKDAY, AlarmUtils.WEEKDAY);
        });
        binding.flWeekend.setOnClickListener(v -> {
            viewModel.setCycle(AlarmUtils.TYPE_WEEKEND, AlarmUtils.WEEKEND);
        });
        binding.flCustom.setOnClickListener(v -> {
            selectViewModel.setSelects(viewModel.daysList());
            selectViewModel.title.set("选择周期");
            selectViewModel.setSingle(false);
            selectViewModel.setOnSaveListener(() -> {
                StringBuilder sss = new StringBuilder();
                selectViewModel.selected((index, select) -> {
                    boolean checked = select.isChecked();
                    if (checked) {
                        sss.append(select.getValue());
                        sss.append("!");
                    }
                    return checked;
                });
                viewModel.setCycle(AlarmUtils.TYPE_CUSTOM, sss.toString());
                back();
            });
            selectFragment.setViewModel(selectViewModel);
            getFragmentManager()
                    .beginTransaction()
                    .add(android.R.id.content, selectFragment)
                    .addToBackStack("custom")
                    .commit();
        });

        binding.flNotify.setOnClickListener(v -> {
            selectViewModel.setSelects(viewModel.selectNotifyTypes());
            selectViewModel.title.set("提醒事项");
            selectViewModel.setSingle(true);
            selectFragment.setViewModel(selectViewModel);
            selectViewModel.setOnSaveListener(() -> {
                selectViewModel.selected((index, select) -> {
                    boolean checked = select.isChecked();
                    if (checked) {
                        viewModel.notifyType.set(select.getName());
                    }
                    return checked;
                });
                back();
            });
            getFragmentManager()
                    .beginTransaction()
                    .add(android.R.id.content, selectFragment)
                    .addToBackStack("custom")
                    .commit();
        });
        return binding.getRoot();
    }

    private OnAlarmSaveListener onAlarmSaveListener;

    public void setOnAlarmSaveListener(OnAlarmSaveListener onAlarmSaveListener) {
        this.onAlarmSaveListener = onAlarmSaveListener;
    }

    public interface OnAlarmSaveListener {
        void onSave(Alarm alarm);
    }
}
