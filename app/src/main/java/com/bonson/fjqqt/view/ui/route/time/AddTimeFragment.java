package com.bonson.fjqqt.view.ui.route.time;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bonson.library.utils.NumberUtils;
import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.FragmentAddRouteTimeBinding;
import com.bonson.qqtapk.di.ActivityScope;
import com.bonson.resource.dialog.TimePickerDialog;
import com.bonson.resource.fragment.BaseFragment;

import javax.inject.Inject;

@ActivityScope
public class AddTimeFragment extends BaseFragment<FragmentAddRouteTimeBinding> {
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
        setBindingLayout(inflater, R.layout.fragment_add_route_time, container);
        binding.setViewModel(viewModel);
        setViewModel(viewModel);

        binding.tvTime.setOnClickListener(v -> {
            if (dialog == null) {
                dialog = new TimePickerDialog();
            }
            dialog.setValue(viewModel.routeTime.getFbeghours(), viewModel.routeTime.getFbegminutes(), viewModel.routeTime.getFendhours(), viewModel.routeTime.getFendminutes());
            dialog.setOnSaveListener((startHour, startMinute, endHour, endMinute) -> {
                String start = startHour + ":" + startMinute;
                String end = endHour + ":" + endMinute;
                int s = NumberUtils.parseInt(startHour + startMinute);
                int e = NumberUtils.parseInt(endHour + endMinute);
                if (s > e) {
                    toast("开始时间不能大于结束时间");
                    return;
                }
                if (e - s > 120) {
                    toast("设置的最长时间不能超过2小时");
                    return;
                }
                viewModel.routeTime.setFbeghours(startHour);
                viewModel.routeTime.setFbegminutes(startMinute);
                viewModel.routeTime.setFendhours(endHour);
                viewModel.routeTime.setFendminutes(endMinute);
                viewModel.time.set(start + "~" + end);
                dialog.dismiss();
            });
            dialog.show(getFragmentManager(), "time");
        });
        return binding.getRoot();
    }
}
