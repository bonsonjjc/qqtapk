package com.bonson.qqtapk.view.ui.info;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;

import com.bonson.library.utils.DateUtils;
import com.bonson.qqtapk.R;
import com.bonson.qqtapk.app.Route;
import com.bonson.qqtapk.databinding.ActivityInfoBinding;
import com.bonson.qqtapk.model.bean.Baby;
import com.bonson.qqtapk.view.ui.info.input.InputFragment;
import com.bonson.qqtapk.view.ui.info.select.SelectFragment;
import com.bonson.resource.activity.BaseDaggerActivity;
import com.bonson.resource.dialog.ActionSheetDialog;
import com.bonson.resource.dialog.AlertDialog;
import com.bonson.resource.dialog.CityPickerDialog;
import com.bonson.resource.dialog.DatePickerDialog;

import javax.inject.Inject;

/**
 * Created by jiangjiancheng on 17/12/31.
 */

public class InfoActivity extends BaseDaggerActivity<ActivityInfoBinding> {
    @Inject
    InfoViewModel viewModel;

    @Inject
    SelectFragment selectFragment;

    @Inject
    InputFragment inputFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBindingLayout(R.layout.activity_info);
        binding.setViewModel(viewModel);
        setViewModel(viewModel
        );

        binding.toolbar.setTitle("宝贝信息");
        binding.toolbar.getTvLeft().setOnClickListener(v -> finish());
        binding.setItemClick(type -> {
            switch (type) {
                case Type.icon:
                    showIcon();
                    break;
                case Type.relative:
                    showRelative();
                    break;
                case Type.name:
                    inputFragment.setViewModel(viewModel.inputFragment(type, "呢称", "输入呢称"));
                    getSupportFragmentManager().beginTransaction()
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
                    getSupportFragmentManager().beginTransaction()
                            .add(android.R.id.content, inputFragment)
                            .addToBackStack("name")
                            .commit();
                    break;
                case Type.height:
                    inputFragment.setViewModel(viewModel.inputFragment(type, "身高", "输入身高(cm)"));
                    getSupportFragmentManager().beginTransaction()
                            .add(android.R.id.content, inputFragment)
                            .addToBackStack("name")
                            .commit();
                    break;
                case Type.weight:
                    inputFragment.setViewModel(viewModel.inputFragment(type, "体重", "输入体重(kg)"));
                    getSupportFragmentManager().beginTransaction()
                            .add(android.R.id.content, inputFragment)
                            .addToBackStack("name")
                            .commit();
                    break;
                case Type.unbind:
                    if (actionSheetDialog == null) actionSheetDialog = new ActionSheetDialog();
                    actionSheetDialog.setTitle("是否要解除绑定?");
                    actionSheetDialog.setActionSheet(new String[]{"解绑"}, Color.RED);
                    actionSheetDialog.setOnItemClickListener(position -> viewModel.unbind());
                    actionSheetDialog.show(getSupportFragmentManager(), "sex");
                    break;
                case Type.change:
                    start(Route.scan);
                    break;
            }
        });
        viewModel.setBaby(Baby.baby);
    }

    private void showIcon() {
        ActionSheetDialog actionSheetDialog = new ActionSheetDialog();
        actionSheetDialog.setTitle("选择头像");
        actionSheetDialog.setActionSheet(new String[]{"拍照", "相册"}, Color.RED);
        actionSheetDialog.setOnItemClickListener(position -> {

        });
        actionSheetDialog.show(getSupportFragmentManager(), "sex");
    }

    DatePickerDialog datePicker;

    private void showBirth() {
        if (datePicker == null) {
            datePicker = new DatePickerDialog();
            datePicker.setOnDateListener((dateStr, date) -> {
                viewModel.getBaby().setFbirth(dateStr);
                viewModel.notifyChange();
                viewModel.update();
                datePicker.dismiss();
            });
        }
        datePicker.setMaxYear(2050);
        datePicker.setMinYear(1900);
        datePicker.setDate(DateUtils.parse(viewModel.getBaby().getFbirth(), "yyyy-MM-dd"));
        datePicker.show(getSupportFragmentManager(), "birth");
    }

    public interface OnItemClickListener {
        void onItemClick(int type);
    }

    private void showRelative() {
        selectFragment.setViewModel(viewModel.selectViewModel());
        getSupportFragmentManager().beginTransaction()
                .add(android.R.id.content, selectFragment)
                .addToBackStack("relative")
                .commit();
    }

    ActionSheetDialog actionSheetDialog;

    private void showSex() {
        if (actionSheetDialog == null) actionSheetDialog = new ActionSheetDialog();
        actionSheetDialog.setTitle("选择性别");
        actionSheetDialog.setActionSheet(new String[]{"男", "女"}, Color.RED);
        actionSheetDialog.setOnItemClickListener(position -> {
            viewModel.getBaby().setFsex(position + "");
            viewModel.notifyChange();
            viewModel.update();
        });
        actionSheetDialog.show(getSupportFragmentManager(), "sex");
    }

    CityPickerDialog cityPicker;

    private void showArea() {
        cityPicker = new CityPickerDialog();
        cityPicker.setCityAdapter(new CityAdapter());
        cityPicker.show(getSupportFragmentManager(), "area");
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
        public static final int icon = 10;
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
