package com.bonson.qqtapk.view.ui.mode;

import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.databinding.ObservableArrayList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ActivityModeBinding;
import com.bonson.qqtapk.view.ui.info.select.Select;
import com.bonson.qqtapk.view.ui.info.select.SelectFragment;
import com.bonson.qqtapk.view.ui.info.select.SelectViewModel;
import com.bonson.resource.activity.BaseDaggerActivity;
import com.bonson.resource.adapter.OnItemClickListener;
import com.bonson.resource.dialog.ActionSheetDialog;
import com.bonson.resource.dialog.AlertDialog;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by jiangjiancheng on 17/12/31.
 */

public class ModeActivity extends BaseDaggerActivity {
    @Inject
    ModeViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityModeBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_mode);
        binding.setViewModel(viewModel);
        binding.toolbar.setTitle("设置安全模式");
        binding.toolbar.getTvLeft().setOnClickListener(v -> finish());
        binding.toolbar.setRightText("保存");
        binding.toolbar.getTvRight().setOnClickListener(v -> viewModel.update());
        viewModel.setView(this);
        viewModel.mode();
        viewModel.powerSave.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                if (viewModel.powerSave.get()) {
                    showWarn();
                }
            }
        });
    }

    public void modeClick(View view) {
        switch (view.getId()) {
            case R.id.cl_security_mode:
                viewModel.locMode("1");
                break;
            case R.id.cl_save_mode:
                showWarn();
                break;
            case R.id.cl_custom_mode:
                viewModel.locMode("3");
                break;
        }
    }

    public void locModeClick(View view) {
        switch (view.getId()) {
            case R.id.cl_blend_mode:
                viewModel.locType("1");
                break;
            case R.id.cl_normal_mode:
                viewModel.locType("2");
                break;
            case R.id.cl_accurate_mode:
                viewModel.locType("3");
                break;
        }
    }

    @Inject
    SelectFragment selectFragment;
    @Inject
    SelectViewModel selectViewModel;

    public void selectDuration(View view) {
        List<Select> selects = new ArrayList<>();
        for (int i = 1; i < 13; i++) {
            int second = i * 5 * 60;
            Select select = new Select(i * 5 + "分钟", second == viewModel.interval.get());
            select.setWht(second);
            selects.add(select);
        }
        selectViewModel.setSingle(true);
        selectViewModel.setSelects(selects);
        selectViewModel.title.set("定位时间间隔");
        selectFragment.setViewModel(selectViewModel);
        selectViewModel.setOnSaveListener(() -> {
            for (Select select : selectViewModel.selects) {
                if (select.isChecked()) {
                    viewModel.interval.set(select.getWht());
                    back();
                    return;
                }
            }
        });
        getSupportFragmentManager().beginTransaction()
                .add(android.R.id.content, selectFragment)
                .addToBackStack("select")
                .commit();
    }

    ActionSheetDialog alertDialog;

    public void showWarn() {
        if (alertDialog == null) alertDialog = new ActionSheetDialog();
        alertDialog.setTitle("选择省电模式，安全区域功能将失效，是否继续？");
        alertDialog.clear();
        alertDialog.addActionSheet("确定", Color.RED);
        alertDialog.setOnItemClickListener(position -> {
            viewModel.locMode("2");
        });
        alertDialog.setCancelListener(v -> {
            viewModel.powerSave.set(false);
        });
        alertDialog.show(getSupportFragmentManager(), "alert");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.onDestroy();
    }
}
