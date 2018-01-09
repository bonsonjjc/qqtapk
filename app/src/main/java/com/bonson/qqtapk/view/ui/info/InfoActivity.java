package com.bonson.qqtapk.view.ui.info;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ActivityInfoBinding;
import com.bonson.qqtapk.model.bean.Baby;
import com.bonson.resource.activity.BaseDaggerActivity;

import javax.inject.Inject;

/**
 * Created by jiangjiancheng on 17/12/31.
 */

public class InfoActivity extends BaseDaggerActivity {
    @Inject
    InfoViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityInfoBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_info);
        binding.toolbar.setTitle("宝贝信息");
        binding.toolbar.getTvLeft().setOnClickListener(v -> finish());
        binding.setViewModel(viewModel);
        binding.setItemClick(type -> {
            switch (type) {
                case Type.relative:
                    break;
                case Type.name:
                    break;
                case Type.sex:
                    toast("显示对话框");
                    break;
                case Type.birth:
                    toast("显示生日对话框");
                    break;
                case Type.area:
                    toast("显示区域对话框");
                    break;
                case Type.mobile:
                    break;
                case Type.height:
                    break;
                case Type.weight:
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

    public interface OnItemClickListener {
        void onItemClick(int type);
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
