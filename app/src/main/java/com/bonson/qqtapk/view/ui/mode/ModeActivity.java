package com.bonson.qqtapk.view.ui.mode;

import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.os.Bundle;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ActivityModeBinding;
import com.bonson.resource.activity.BaseDaggerActivity;

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
        binding.toolbar.getTvLeft().setOnClickListener(v -> {
            finish();
        });
        binding.toolbar.setRightText("保存");
        binding.toolbar.getTvRight().setOnClickListener(v -> {
            viewModel.update();
        });
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
        viewModel.powerMode.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                showWarn();
            }
        });
    }

    public void showWarn() {
        toast("是否切换到省电模式");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.onDestroy();
    }
}
