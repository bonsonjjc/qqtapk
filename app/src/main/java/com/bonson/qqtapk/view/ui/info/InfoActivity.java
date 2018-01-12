package com.bonson.qqtapk.view.ui.info;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.Gravity;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ActivityInfoBinding;
import com.bonson.qqtapk.model.bean.Baby;
import com.bonson.qqtapk.view.ui.info.input.InputFragment;
import com.bonson.qqtapk.view.ui.info.select.Select;
import com.bonson.qqtapk.view.ui.info.select.SelectFragment;
import com.bonson.resource.activity.BaseDaggerActivity;
import com.bonson.resource.dialog.DatePicker;

import javax.inject.Inject;

/**
 * Created by jiangjiancheng on 17/12/31.
 */

public class InfoActivity extends BaseDaggerActivity {
    @Inject
    InfoViewModel viewModel;

    @Inject
    SelectFragment selectFragment;

    @Inject
    InputFragment inputFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityInfoBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_info);
        binding.toolbar.setTitle("宝贝信息");
        binding.toolbar.getTvLeft().setOnClickListener(v -> finish());
        binding.setViewModel(viewModel);
        viewModel.setView(this);
        binding.setItemClick(type -> {
            switch (type) {
                case Type.relative:
                    showRelative();
                    break;
                case Type.name:
                    inputFragment.setViewModel(viewModel.inputFragment(type, "呢称", "输入呢称"));
                    getSupportFragmentManager()
                            .beginTransaction()
                            .add(android.R.id.content, inputFragment)
                            .addToBackStack("name")
                            .commit();
                    break;
                case Type.sex:
                    toast("显示对话框");
                    break;
                case Type.birth:
                    toast("显示生日对话框");
                    showBirth();
                    break;
                case Type.area:
                    toast("显示区域对话框");
                    break;
                case Type.mobile:
                    inputFragment.setViewModel(viewModel.inputFragment(type, "宝贝手机", "输入宝贝手机号码"));
                    getSupportFragmentManager()
                            .beginTransaction()
                            .add(android.R.id.content, inputFragment)
                            .addToBackStack("name")
                            .commit();
                    break;
                case Type.height:
                    inputFragment.setViewModel(viewModel.inputFragment(type, "身高", "输入身高(cm)"));
                    getSupportFragmentManager()
                            .beginTransaction()
                            .add(android.R.id.content, inputFragment)
                            .addToBackStack("name")
                            .commit();
                    break;
                case Type.weight:
                    inputFragment.setViewModel(viewModel.inputFragment(type, "体重", "输入体重(kg)"));
                    getSupportFragmentManager()
                            .beginTransaction()
                            .add(android.R.id.content, inputFragment)
                            .addToBackStack("name")
                            .commit();
                    break;
                case Type.unbind:
                    toast("显示解绑对话框");
                    break;
                case Type.change:
                    break;
            }
        });
        viewModel.setBaby(Baby.baby);
    }

    DatePicker datePicker;

    private void showBirth() {
        if (datePicker == null) {
            datePicker = DatePicker.builder(this);
        }
        datePicker.show(getWindow().getDecorView(), Gravity.BOTTOM);
    }

    public interface OnItemClickListener {
        void onItemClick(int type);
    }

    private void showRelative() {
        selectFragment.setViewModel(viewModel.selectViewModel());
        getSupportFragmentManager()
                .beginTransaction()
                .add(android.R.id.content, selectFragment)
                .addToBackStack("relative")
                .commit();
    }

    private void showSex() {

    }

    private void showArea() {

    }

    public static class Type {
        public static final int relative = 0;
        public static final int name = 1;
        public static final int sex = 2;
        public static final int birth = 3;
        public static final int area = 4;
        public static final int mobile = 5;
        public static final int weight = 6;
        public static final int height = 7;
        public static final int unbind = 8;
        public static final int change = 9;
    }
}
