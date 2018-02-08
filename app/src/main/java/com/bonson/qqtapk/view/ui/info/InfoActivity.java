package com.bonson.qqtapk.view.ui.info;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.bonson.library.utils.DateUtils;
import com.bonson.library.utils.LogUtils;
import com.bonson.qqtapk.R;
import com.bonson.qqtapk.app.Const;
import com.bonson.qqtapk.app.Route;
import com.bonson.qqtapk.databinding.ActivityInfoBinding;
import com.bonson.qqtapk.model.bean.Area;
import com.bonson.qqtapk.utils.PictureHelper;
import com.bonson.qqtapk.view.ui.info.input.InputFragment;
import com.bonson.qqtapk.view.ui.info.select.SelectFragment;
import com.bonson.resource.activity.BaseDaggerActivity;
import com.bonson.resource.dialog.ActionSheetDialog;
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
    @Inject
    CityAdapter cityAdapter;
    @Inject
    PictureHelper pictureHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBindingLayout(R.layout.activity_info);
        binding.setViewModel(viewModel);
        setViewModel(viewModel);

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
                    if (actionSheetDialog == null) actionSheetDialog = new ActionSheetDialog();
                    actionSheetDialog.setTitle("是否要解除绑定?");
                    actionSheetDialog.setActionSheet(new String[]{"解绑"}, Color.RED);
                    actionSheetDialog.setOnItemClickListener(position -> viewModel.unbind());
                    actionSheetDialog.show(getSupportFragmentManager(), "sex");
                    break;
                case Type.change:
                    Intent intent = new Intent();
                    intent.setAction(Const.CHANGE_IMEI);
                    intent.setClassName(this, Route.scan);
                    startActivity(intent);
                    break;
            }
        });
    }

    private void showIcon() {
        ActionSheetDialog actionSheetDialog = new ActionSheetDialog();
        actionSheetDialog.setTitle("选择头像");
        actionSheetDialog.setActionSheet(new String[]{"拍照", "相册"}, Color.RED);
        actionSheetDialog.setOnItemClickListener(position -> {
            if (position == 0) {
                pictureHelper.takePicture(this);
            } else {
                pictureHelper.photo(this);
            }
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
        if (cityPicker == null) {
            cityPicker = new CityPickerDialog();
            cityPicker.setCityAdapter(cityAdapter);
            cityPicker.setOnCitySaveListener((p, c, d) -> {
                if (p == 0 || c == 0 || d == 0) {
                    toast("请选择完整地区");
                    return;
                }
                Area province = cityAdapter.getProvinces().get(p), city = cityAdapter.getCitys().get(c), district = cityAdapter.getDistricts().get(d);
                viewModel.getBaby().setFprovince(province.getId());
                viewModel.getBaby().setFcity(city.getId());
                viewModel.getBaby().setFarea(district.getId());
                viewModel.getBaby().setFareaname(province.getName() + city.getName() + district.getName());
                viewModel.notifyChange();
                viewModel.update();
                cityPicker.dismiss();
            });
        }
        if (!cityPicker.isVisible())
            cityPicker.show(getSupportFragmentManager(), "area");

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureHelper.TAKE_PICTURE:
                    pictureHelper.cropPicture(this);
                    break;
                case PictureHelper.PHOTO:
                    pictureHelper.path(this, data.getData());
                    pictureHelper.cropPicture(this);
                    break;
                default:
                    LogUtils.e(pictureHelper.getFile().getAbsolutePath());
                    viewModel.upload(pictureHelper.getFile());
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        pictureHelper.release();
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
