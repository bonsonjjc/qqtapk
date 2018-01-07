package com.bonson.qqtapk.view.ui.member;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ActivityMemberBinding;
import com.bonson.qqtapk.view.ui.contacts.input.InputFragment;
import com.bonson.qqtapk.view.ui.contacts.input.InputViewModel;
import com.bonson.resource.activity.BaseDaggerActivity;

import javax.inject.Inject;

/**
 * Created by jiangjiancheng on 17/12/31.
 */

public class MemberActivity extends BaseDaggerActivity {
    @Inject
    MemberViewModel viewModel;

    @Inject
    InputViewModel inputViewModel;

    @Inject
    InputFragment inputFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMemberBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_member);
        binding.setViewModel(viewModel);
        binding.toolbar.setTitle("家庭成员");
        binding.toolbar.getTvLeft().setOnClickListener(v -> {
            finish();
        });
        binding.toolbar.setRightText("邀请");
        binding.toolbar.getTvRight().setOnClickListener(view -> {
            inputFragment.setViewModel(inputViewModel);
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(android.R.id.content, inputFragment)
                    .addToBackStack("member")
                    .commit();
            inputViewModel.title.set("编辑家庭成员");
            inputViewModel.right.set("保存");
            inputViewModel.mobileHint.set("输入手机号码");
            inputViewModel.nameHint.set("输入名称");
        });
        binding.setViewModel(viewModel);
        viewModel.setView(this);
        viewModel.members();
    }
}
