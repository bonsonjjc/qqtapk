package com.bonson.qqtapk.view.ui.info;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;

import com.bonson.library.utils.DateUtils;
import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ActivityInfoBinding;
import com.bonson.qqtapk.model.bean.Baby;
import com.bonson.qqtapk.view.ui.info.input.InputFragment;
import com.bonson.qqtapk.view.ui.info.select.SelectFragment;
import com.bonson.resource.activity.BaseDaggerActivity;
import com.bonson.resource.dialog.AlertDialog;
import com.bonson.resource.dialog.CityPickerDialog;
import com.bonson.resource.dialog.DatePickerDialog;
import com.mylhyl.superdialog.SuperDialog;
import com.mylhyl.superdialog.res.values.ColorRes;

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
        AlertDialog alertDialog = new AlertDialog();
        alertDialog.setTitle("警告");
        alertDialog.setContent("是否删除该联系人?");
        alertDialog.setSure("确定");
        alertDialog.setCancel("取消");
        alertDialog.setOnClickListener((view, witch) -> {

        });
        alertDialog.show(getSupportFragmentManager(), "sex");
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
                    showSex();
                    break;
                case Type.birth:
                    showBirth();
                    break;
                case Type.area:
                    showArea();
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

    DatePickerDialog datePickerDialog;

    private void showBirth() {
        if (datePickerDialog == null) {
            datePickerDialog = DatePickerDialog.builder(this);
            datePickerDialog.setOnDateListener((dateStr, date) -> {
                viewModel.getBaby().setFbirth(dateStr);
                viewModel.notifyChange();
                viewModel.update();
                datePickerDialog.dismiss();
            });
        }
        datePickerDialog.show(getWindow().getDecorView(), Gravity.BOTTOM);
        datePickerDialog.setDate(DateUtils.parse(viewModel.getBaby().getFbirth(), "yyyy-MM-dd"));
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
        new SuperDialog.Builder(this)
                //.setAlpha(0.5f)
                //.setGravity(Gravity.CENTER)
                .setTitle("性别", ColorRes.title)
                .setCanceledOnTouchOutside(false)
                .setItems(new String[]{"男", "女"}, position -> {

                })
                .setNegativeButton("取消", null)
                .build();
    }

    CityPickerDialog cityPickerDialog;

    private void showArea() {
        cityPickerDialog = CityPickerDialog.builder(this);
        cityPickerDialog.setCityAdapter(new CityAdapter());
        cityPickerDialog.show(getWindow().getDecorView(), Gravity.BOTTOM);
    }

    static String[] province = {"北京市", "天津市", "重庆市", "福建省"};
    static String[] city = {"福州市", "宁德市", "三明市", "泉州市", "莆田市", "漳州市", "龙岩市"};
    static String[] district = {"晋安区", "苍山区", "鼓楼区", "台江区", "马尾区", "闽侯", "长乐市"};

    public class CityAdapter implements CityPickerDialog.CityAdapter {
        @Override
        public int provinceSize() {
            return province.length;
        }

        @Override
        public int citySize(int index) {
            return city.length;
        }

        @Override
        public int districtSize(int index) {
            return district.length;
        }

        @Override
        public String province(int index) {
            if (index == 0) return "--";
            return province[index - 1];
        }

        @Override
        public String city(int index) {
            if (index == 0) return "--";
            return city[index - 1];
        }

        @Override
        public String district(int index) {
            if (index == 0) return "--";
            return district[index - 1];
        }
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
